package Model.Exception.ExpressionsEvaluation;

import Model.Exception.MyException;

public class DivisionByZeroException extends MyException {

    public DivisionByZeroException(String message) {
        super(message);
    }
    public DivisionByZeroException() {
        super("Division by zero");
    }
}
