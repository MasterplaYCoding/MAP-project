package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException;
    Exp deepcopy();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}
