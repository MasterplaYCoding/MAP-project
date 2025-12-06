package Model.ADT.List;

import Model.Exception.ADTExceptions.IndexOutOfBoundsException;
import Model.Exception.MyException;

import java.util.List;

public interface MyIList<T> {
    void add(T value);
    void add(int index, T value);
    T set(int index, T value) throws MyException;
    T remove(int index) throws MyException;
    int size();
    T get(int index) throws MyException;
    T getFirst() throws MyException;
    T getLast() throws MyException;
    String fileToString();
    List<T> getList() throws MyException;
    void setList(List<T> list) throws MyException;
}
