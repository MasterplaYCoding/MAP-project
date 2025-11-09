package ViewModel;

import Model.ADT.Stack.MyIStack;
import Model.Exception.ADTExceptions.EmptyStackException;
import Model.Exception.ADTExceptions.IndexOutOfBoundsException;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.ExpressionsEvaluation.VariableAlreadyDeclared;
import Model.Exception.MyException;
import Model.Exception.StatementsExecution.ExecutionStackEmpty;
import Model.Other.PrgState;
import Model.Stmt.IStmt;
import Repository.IRepository;

public class Controller implements IController {
    private IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty())
            throw new ExecutionStackEmpty();
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    @Override
    public void allStep() throws MyException {
        PrgState prg = repository.getCrtPrg();
        // displayState();
        repository.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            // displayState();
            repository.logPrgStateExec();
        }
    }

    @Override
    public void displayState() throws MyException{
        PrgState prg = repository.getCrtPrg();
        System.out.println(prg.toString());
    }

    public IRepository getRepository() {
        return repository;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
