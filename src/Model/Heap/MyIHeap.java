package Model.Heap;

import Model.Value.Value;

import java.util.Map;

public interface MyIHeap<T1, T2> {
    T1 add(T2 value);
    T2 get(T1 key);
    void update(T1 key, T2 value);
    void setContent(Map<T1, T2> map);
    Map<T1, T2> getContent();
}
