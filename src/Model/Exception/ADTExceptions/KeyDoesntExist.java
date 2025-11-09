package Model.Exception.ADTExceptions;

import Model.Exception.MyException;

public class KeyDoesntExist extends MyException {
    public KeyDoesntExist(String message) {
        super(message);
    }
}
