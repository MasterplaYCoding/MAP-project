package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Type.Type;

public interface IStmt {
    PrgState execute (PrgState prgState) throws MyException;
    IStmt deepcopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
