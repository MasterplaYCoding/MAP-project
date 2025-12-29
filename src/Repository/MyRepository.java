package Repository;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.*;
import java.util.List;

public class MyRepository implements IRepository {

    MyList<PrgState> states = new MyList<>();
    String logFilePath;

    public MyRepository(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public MyRepository() {}

    public MyRepository(MyList<PrgState> states) {
        this.states = states;
    }

    public MyRepository(PrgState state, String logFilePath) {
        this.states = new MyList<>();
        this.states.add(state);
        this.logFilePath = logFilePath;
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(state.fileToString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (logFile != null)
                logFile.close();
        }
    }

    @Override
    public List<PrgState> getPrgList() {
        return states.getList();
    }

    @Override
    public void setPrgList(List<PrgState> prgList) {
        states.setList(prgList);
    }
}
