package Model.ADT.Dictionary;

import Model.Exception.MyException;
import Model.Value.StringValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    T2 put (T1 key, T2 value) throws MyException;
    T2 get(T1 key) throws MyException;
    int size();
    T2 remove(T1 key) throws MyException;
    void clear();
    boolean isDefined(T1 id) throws MyException;
    void update(T1 id, T2 val) throws MyException;
    String fileToString();
    String fileToString2();
    Map<T1,T2> getContent();
    Set<T1> keySet();
    Collection<T2> values();
    MyIDictionary<T1,T2> copy();
}
