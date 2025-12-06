package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

public class WhileStmt implements IStmt{

    Exp exp;
    IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        MyIStack<IStmt> stk = state.getExeStack();

        Value val = exp.eval(symTbl, heap);
        if(val.getType().equals(new BoolType())){
            BoolValue  boolVal = (BoolValue)val;
            if (boolVal.getVal()){
                stk.push(this);
                stk.push(stmt);
            }
        }
        else throw new ValueTypeError("The while condition should be a boolean expression");

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return  new WhileStmt(exp.deepcopy(), stmt.deepcopy());
    }

    @Override
    public String toString(){
        return "while(" +  exp.toString() + ")" + stmt.toString();
    }
}
