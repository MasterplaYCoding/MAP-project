package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.DivisionByZeroException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.ExpressionsEvaluation.VariableNotDefinedException;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.Type;
import Model.Value.Value;



public class AssignStmt implements IStmt {
    private final String id;
    private final Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap =  state.getHeap();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl, heap);
            Type typId = (symTbl.get(id)).getType();
            if ((val.getType()).equals(typId))
                symTbl.update(id, val);
            else throw new ValueTypeError("declared type of variable" + id + " and type of " +
                    "the assigned expression do not match");
        } else throw new VariableNotDefinedException("the used variable" + id + " was not declared before");
        return state;
    }


}