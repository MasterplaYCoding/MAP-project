package Repository;

import Model.Exception.MyException;
import Model.Other.PrgState;

public interface IRepository {
    PrgState getCrtPrg()throws MyException;
    void logPrgStateExec() throws MyException;
}
