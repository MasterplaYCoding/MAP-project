package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.RefType;
import Model.Value.RefValue;
import Model.Value.Value;


public class HeapReading implements Exp {
    Exp exp;

    public HeapReading(Exp exp) {
        this.exp = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value val = exp.eval(tbl, heap);
        if (val.getType().equals(new RefType())) {
            RefValue ref = (RefValue) val;
            return heap.get(ref.getAddr());
        }
        else {
            throw new ValueTypeError("The expression to read must be a reference");
        }
    }

    @Override
    public Exp deepcopy() {
        return new HeapReading(exp.deepcopy());
    }

    @Override
    public String toString(){
        return "rH("+exp.toString()+")";
    }
}
