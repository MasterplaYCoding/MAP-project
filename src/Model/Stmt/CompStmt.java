package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Type.Type;

public class CompStmt implements IStmt {
    private final IStmt first;
    private final IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first.toString() + ", " + second.toString();
    }

    @Override
    public PrgState execute (PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new CompStmt(this.first.deepcopy(), this.second.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = second.typecheck(typEnv1);
        //return typEnv2;
        return second.typecheck(first.typecheck(typeEnv));
    }

    public IStmt getFirst() {
        return first;
    }

    public IStmt getSecond() {
        return second;
    }
}
