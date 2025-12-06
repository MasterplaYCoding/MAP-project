package Model.ADT.List;

import java.util.List;
import java.util.Vector;

import Model.Exception.ADTExceptions.EmptyListException;
import Model.Exception.ADTExceptions.IndexOutOfBoundsException;
import Model.Exception.MyException;


public class MyList<T> implements MyIList<T> {

    private final Vector<T> vector;

    public MyList() {
        vector = new Vector<>();
    }

    MyList(Vector<T> vector) {
        this.vector = vector;
    }

    @Override
    public void add(T element) {
        vector.add(element);
    }

    @Override
    public void add(int index, T element) {
        vector.add(index, element);
    }

    @Override
    public T set(int index, T element) throws MyException {
        if (index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException();
        return vector.set(index, element);
    }

    @Override
    public T remove(int index) throws MyException {
        if (index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException();
        return vector.remove(index);
    }

    @Override
    public int size() {
        return vector.size();
    }

    @Override
    public T get(int index) throws MyException {
        if (index < 0 || index >= vector.size())
            throw new IndexOutOfBoundsException();
        return vector.get(index);
    }

    @Override
    public String toString() {
        return vector.toString();
    }

    @Override
    public T getFirst() throws MyException {
        if (vector.isEmpty())
            throw new EmptyListException();
        return vector.firstElement();
    }

    @Override
    public T getLast() throws MyException {
        if (vector.isEmpty())
            throw new EmptyListException();
        return vector.lastElement();
    }

    @Override
    public String fileToString() {
        StringBuilder sb = new StringBuilder("Out:\n");
        for (T t : vector) {
            sb.append(t.toString()).append('\n');
        }
        return sb.toString();
    }

    @Override
    public List<T> getList() throws MyException {
        return  vector;
    }

    @Override
    public void setList(List<T> list) throws MyException {
        vector.clear();
        vector.addAll(list);
    }
}
