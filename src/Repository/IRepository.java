package Repository;

import Model.Exception.MyException;
import Model.Other.PrgState;

import java.util.List;

public interface IRepository {
    void logPrgStateExec(PrgState state) throws MyException;
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgList);
}
