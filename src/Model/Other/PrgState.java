package Model.Other;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Exception.MyException;
import Model.Stmt.IStmt;
import Model.Value.Value;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;

    IStmt originalProgram;

    public PrgState() {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, Value>();
        out = new MyList<Value>();
    }

    public PrgState(PrgState state) {
        this.symTable = state.symTable;
        this.originalProgram = state.originalProgram;
        this.exeStack = state.exeStack;
        this.out = state.out;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg;
        stk.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Exe stack : " + exeStack.toString() + "\n";
        str += "Sym table : " + symTable.toString() + "\n";
        str += "Out : " + out.toString() + "\n";
        return str;
    }
}
