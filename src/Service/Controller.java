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
import java.util.stream.Collectors;

public class Controller implements IController {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty())
            throw new ExecutionStackEmpty();
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    @Override
    public void allStep() throws MyException {
        PrgState prg = repository.getCrtPrg();
        // displayState();
        repository.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            // displayState();
            repository.logPrgStateExec();
            prg.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(prg.getSymTable().values()),
                    prg.getHeap().getContent()));
            repository.logPrgStateExec();
        }
    }

    @Override
    public void displayState() throws MyException{
        PrgState prg = repository.getCrtPrg();
        System.out.println(prg.toString());
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
}
