package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Value.Value;

public class ValueExp implements Exp {
    private final Value e;

    public ValueExp(Value e) {
        this.e = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
