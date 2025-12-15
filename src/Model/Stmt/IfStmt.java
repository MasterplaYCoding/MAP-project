package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.util.Objects;

public class IfStmt implements IStmt {
    private final Exp exp;
    private final IStmt thenS;
    private final IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public String toString() {
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString()
                +")ELSE("+elseS.toString()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap =  state.getHeap();
        Value val = exp.eval(symTbl, heap);
        if(!Objects.equals(val.getType(), new BoolType()))
            throw new ValueTypeError("Conditional expression is not a boolean");
        BoolValue Cond = (BoolValue)val;
        if(Cond.getVal())
            stk.push(thenS);
        else
            stk.push(elseS);
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new IfStmt(exp.deepcopy(), thenS.deepcopy(), elseS.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.copy());
            elseS.typecheck(typeEnv.copy());
            return typeEnv;
        }
        else throw new ValueTypeError("The condition of IF has not the type bool");
    }
}
