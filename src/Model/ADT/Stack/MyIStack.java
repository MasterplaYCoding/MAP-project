package Model.ADT.Stack;

import Model.Exception.MyException;

public interface MyIStack<T> {
    T peek() throws MyException;
    T pop() throws MyException;
    void push(T value);
    boolean isEmpty();
    int size();
    String fileToString();
}
