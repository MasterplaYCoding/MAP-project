package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.KeyDoesntExist;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;

public class HeapWriting implements IStmt {
    String id;
    Exp exp;

    public HeapWriting(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();


        if(symTbl.isDefined(id)) {
            Value val = symTbl.get(id);
            if(val.getType().equals(new RefType())) {
                RefValue ref = (RefValue)val;
                Value val2 = exp.eval(symTbl, heap).deepCopy();
                if (val2.getType().equals(ref.getLocationType())) {
                    heap.update(ref.getAddr(), val2);
                }
                else {
                    throw new ValueTypeError("The expression must have the same type as the memory location");
                }
            }
            else {
                throw new ValueTypeError("The variable is not of RefType");
            }
        }
        else {
            throw new KeyDoesntExist("There is no such variable name");
        }

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new HeapWriting(this.id, this.exp.deepcopy());
    }

    @Override
    public String toString() {
        return "wH("+id+", "+exp.toString()+")";
    }
}
