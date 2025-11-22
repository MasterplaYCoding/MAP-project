package Service;

import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Value.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IController {
    void allStep() throws MyException;
    PrgState oneStep(PrgState state) throws MyException;
    void displayState() throws MyException;
    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues);
    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap);
}
