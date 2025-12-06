package Service;

import Model.ADT.Stack.MyIStack;
import Model.Exception.MyException;
import Model.Exception.StatementsExecution.ExecutionStackEmpty;
import Model.Other.PrgState;
import Model.Stmt.IStmt;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class Controller implements IController {
    private IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void allStep() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repository.getPrgList());
        while(!prgList.isEmpty()){

            List<Integer> combinedSymTable = new ArrayList<>();
            for (PrgState prgState : prgList) {
                combinedSymTable.addAll(getAddrFromSymTable(prgState.getSymTable().values()));
            }

            prgList.get(0).getHeap().setContent(safeGarbageCollector(
                    combinedSymTable,
                    prgList.get(0).getHeap().getContent()));

            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repository.setPrgList(prgList);
    }

    /*
    @Override
    public void displayState() throws MyException{
        PrgState prg = repository.getCrtPrg();
        System.out.println(prg.toString());
    }*/

    @Override
    public void oneStepForAllPrg(List<PrgState> prgList) throws Exception {
        //before the execution, print the PrgState List into the log file
        prgList.forEach(prg ->repository.logPrgStateExec(prg));
        //RUN concurrently one step for each of the existing PrgStates
        //-----------------------------------------------------------------------
        //prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future->{
                    try { return future.get(); }
                    catch (Exception e) { System.out.println(e.getMessage()); }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        prgList.addAll(newPrgList);
        prgList.forEach(prg->repository.logPrgStateExec(prg));
        repository.setPrgList(prgList);
    }

    public IRepository getRepository() {
        return repository;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value>
            heap){

        Set<Integer> reachable = new HashSet<>();

        for(Integer addr: symTableAddr){
            if(!reachable.contains(addr)){
                Integer addr2 = addr;
                while(heap.containsKey(addr2)){
                    reachable.add(addr2);
                    Value val =  heap.get(addr2);
                    if (val.getType().equals(new RefType())) {
                        RefValue ref = (RefValue) val;
                        addr2 = ref.getAddr();
                    }
                    else
                        addr2 = 0;
                }
            }
        }

        return heap.entrySet().stream()
                .filter(e->reachable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
