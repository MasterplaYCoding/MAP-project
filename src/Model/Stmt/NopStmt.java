package Model.Stmt;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.MyException;
import Model.Other.PrgState;

public class NopStmt implements IStmt{

    public NopStmt() {}

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new NopStmt();
    }
}
