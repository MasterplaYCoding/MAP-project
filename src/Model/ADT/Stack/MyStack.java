package Model.ADT.Stack;


import Model.Exception.ADTExceptions.EmptyStackException;
import Model.Exception.MyException;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stack;

    public MyStack() {
        stack = new Stack<>();
    }

    MyStack(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public void push(T value) {
        stack.push(value);
    }

    @Override
    public T pop() throws MyException {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.pop();
    }

    @Override
    public T peek() throws MyException {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
