package Service;

import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Value.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IController {
    void allStep() throws Exception;
    //void displayState() throws MyException;
    void oneStepForAllPrg(List<PrgState> prgList) throws Exception;
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues);
    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap);
    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList);
}
