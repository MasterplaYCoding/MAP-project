package Repository;

import Model.ADT.List.MyList;
import Model.Exception.MyException;
import Model.Other.PrgState;

public class MemoryRepository implements IRepository {

    MyList<PrgState> states = new MyList<>();

    public MemoryRepository() {}

    public MemoryRepository(MyList<PrgState> states) {
        this.states = states;
    }

    @Override
    public PrgState getCrtPrg() throws MyException {
        return states.getLast();
    }
}
