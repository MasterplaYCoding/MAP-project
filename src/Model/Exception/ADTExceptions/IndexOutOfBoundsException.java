package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class IndexOutOfBoundsException extends MyException {
    public IndexOutOfBoundsException(String message) {
        super(message);
    }
    public IndexOutOfBoundsException() {
        super("Index out of bounds");
    }
}
