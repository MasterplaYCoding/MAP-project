package Model.Exception.ExpressionsEvaluation;

import Model.Exception.MyException;

public class VariableAlreadyDeclared extends MyException {
    public VariableAlreadyDeclared(String message) {
        super(message);
    }
    public VariableAlreadyDeclared() {
        super("variable is already declared");
    }

}
