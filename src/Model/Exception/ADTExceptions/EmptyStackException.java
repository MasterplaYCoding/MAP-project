package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class EmptyStackException extends MyException {
    public EmptyStackException(String message) {
        super(message);
    }
    public EmptyStackException() {
        super("Stack is empty");
    }
}
