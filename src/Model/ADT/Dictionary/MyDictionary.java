package Model.ADT.Dictionary;

import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.MyException;
import Model.Value.StringValue;

import java.util.*;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2> {

    private final Map<T1, T2> map;

    public MyDictionary() {
        this.map = new HashMap<T1, T2>();
    }
    MyDictionary(Map<T1, T2> map) {
        this.map = map;
    }

    @Override
    public T2 put (T1 key, T2 value) throws MyException {
        if (key == null)
            throw new NullKeyException();
        return map.put(key, value);
    }

    @Override
    public T2 get(T1 key) throws MyException {
        if (key == null)
            throw new NullKeyException();
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public T2 remove(T1 key) throws MyException {
        if (key == null)
            throw new NullKeyException();
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isDefined(T1 key) throws MyException {
        if (key == null)
            throw new NullKeyException();
        return map.containsKey(key);
    }

    @Override
    public void update(T1 id, T2 val) throws MyException {
        if (id == null)
            throw new NullKeyException();
        map.put(id, val);
    }

    @Override
    public String toString(){
        return map.toString();
    }

    @Override
    public String fileToString(){
        StringBuilder sb = new StringBuilder("SymTable:\n");
        for (Map.Entry<T1, T2> entry : map.entrySet()){
            sb.append(entry.getKey()).append(" --> ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Map<T1, T2> getContent() {
        return map;
    }

    @Override
    public Set<T1> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<T2> values() {
        return map.values();
    }

}
