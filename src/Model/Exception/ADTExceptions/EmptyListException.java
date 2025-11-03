package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class EmptyListException extends MyException {
    public EmptyListException(String message) {
        super(message);
    }
    public EmptyListException() {
        super("Empty List");
    }
}
