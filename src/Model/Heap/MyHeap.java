package Model.Heap;

import Model.Exception.ADTExceptions.KeyDoesntExist;
import Model.Value.Value;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<Integer,T> {
    HashMap<Integer, T> heap;
    int freeLocation;

    public MyHeap() {
        heap = new HashMap<>();
        freeLocation = 1;
    }

    public MyHeap(HashMap<Integer, T> heap, int freeLocation) {
        this.heap = heap;
        this.freeLocation = freeLocation;
    }

    @Override
    public Integer add(T val){
        heap.put(freeLocation, val);
        int key = freeLocation;
        do {
            freeLocation++;
        } while (heap.containsKey(freeLocation));
        return key;
    }

    @Override
    public T get(Integer key) {
        if (!heap.containsKey(key)){
            throw new KeyDoesntExist("Key doesn't exist");
        }
        return heap.get(key);
    }

    @Override
    public void update(Integer key, T value) {
        if(!heap.containsKey(key)){
            throw new KeyDoesntExist("Key doesn't exist");
        }
        heap.put(key, value);
    }

    @Override
    public void setContent(Map<Integer, T> map) {
        heap = new HashMap<>(map);
    }

    @Override
    public Map<Integer, T> getContent() {
        return heap;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Heap : \n");
        for(Map.Entry<Integer, T> entry : heap.entrySet()){
            str.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");
        }
        return str.toString();
    }
}
