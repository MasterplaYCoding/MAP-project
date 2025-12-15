package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public class ValueExp implements Exp {
    private final Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        return e;
    }

    @Override
    public Exp deepcopy() {
        return new ValueExp(e.deepCopy());
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
