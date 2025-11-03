package Model.Exception.ExpressionsEvaluation;

import Model.Exception.MyException;

public class VariableNotDefinedException extends MyException {

    public VariableNotDefinedException(String message) {
        super(message);
    }
    public VariableNotDefinedException() {
        super("The variable is not defined in symbol table");
    }
}
