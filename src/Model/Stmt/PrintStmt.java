package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt{
    private final Exp exp;

    public PrintStmt(Exp exp){
        this.exp=exp;
    }

    @Override
    public String toString(){
        return "print(" +exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = state.getOut();
        MyIDictionary<String, Value> symTable =  state.getSymTable();
        MyIHeap<Integer, Value> heap =  state.getHeap();
        out.add(exp.eval(symTable, heap));
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new PrintStmt(this.exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
