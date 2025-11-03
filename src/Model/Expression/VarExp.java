package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.MyException;
import Model.Value.Value;

public class VarExp implements Exp {
    private final String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        return tbl.get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
