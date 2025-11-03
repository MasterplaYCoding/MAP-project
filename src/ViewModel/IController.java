package ViewModel;

import Model.Exception.MyException;
import Model.Other.PrgState;

public interface IController {
    void allStep() throws MyException;
    PrgState oneStep(PrgState state) throws MyException;
    void displayState() throws MyException;
}
