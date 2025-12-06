package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Value.Value;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt stmt){
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState prgState) throws MyException {

        MyIStack<IStmt> exeStack2 = new MyStack<>();
        exeStack2.push(stmt);
        MyIDictionary<String, Value> symTable2 = prgState.getSymTable().copy();
        PrgState newPrgState = new PrgState(prgState);
        newPrgState.setSymTable(symTable2);
        newPrgState.setExeStack(exeStack2);
        newPrgState.updateId();

        return newPrgState;
    }

    @Override
    public IStmt deepcopy() {
        return new ForkStmt(this.stmt.deepcopy());
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}
