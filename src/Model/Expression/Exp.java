package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
