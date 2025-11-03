package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class NullKeyException extends MyException {
    public NullKeyException(String message) {
        super(message);
    }
    public NullKeyException() {
        super("Can't create a null key");
    }
}