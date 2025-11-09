package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class KeyAlreadyExists extends MyException {
    public KeyAlreadyExists(String message) {
        super(message);
    }
    public KeyAlreadyExists() { super("The key already exists in the dictionary"); }
}
