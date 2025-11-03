package Model.Exception.StatementsExecution;

import Model.Exception.MyException;

public class ExecutionStackEmpty extends MyException {

    public ExecutionStackEmpty(String message) {
        super(message);
    }
    public ExecutionStackEmpty() {
        super("Execution stack is empty");
    }
}
