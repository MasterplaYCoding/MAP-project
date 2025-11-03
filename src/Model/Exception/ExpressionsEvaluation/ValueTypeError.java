package Model.Exception.ExpressionsEvaluation;

import Model.Exception.MyException;

public class ValueTypeError extends MyException {
    public ValueTypeError(String message) {
        super(message);
    }
}
