package Model.Stmt;

import Model.Exception.MyException;
import Model.Other.PrgState;

public interface IStmt {
    PrgState execute (PrgState prgState) throws MyException;
}
