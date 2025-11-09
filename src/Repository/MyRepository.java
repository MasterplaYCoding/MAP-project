package Repository;

import Model.ADT.List.MyList;
import Model.Exception.MyException;
import Model.Other.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    public PrgState getCrtPrg() throws MyException {
        return states.getLast();
    }

    @Override
    public void logPrgStateExec() throws MyException {
        PrintWriter logFile = null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(states.getLast().fileToString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (logFile != null)
                logFile.close();
        }
    }
}
