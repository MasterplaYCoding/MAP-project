package Model.ADT.Stack;


import Model.Exception.ADTExceptions.EmptyStackException;
import Model.Exception.MyException;
import Model.Stmt.CompStmt;
import Model.Stmt.IStmt;

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

    @Override
    public String fileToString() {
        Stack<IStmt> newStack = (Stack<IStmt>) stack.clone();
        MyStack<IStmt> copie = new MyStack<>(newStack);

        StringBuilder result = new StringBuilder("Exe stack:\n");
        while (!copie.isEmpty()) {
            IStmt st = copie.pop();
            if(st instanceof CompStmt) {
                IStmt first = ((CompStmt) st).getFirst();
                IStmt second = ((CompStmt) st).getSecond();
                copie.push(second);
                copie.push(first);
            }
            else {
                result.append(st.toString()).append("\n");
            }
        }
        return result.toString();
    }
}
