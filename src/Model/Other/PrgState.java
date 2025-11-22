package Model.Other;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Heap.MyHeap;
import Model.Heap.MyIHeap;
import Model.Stmt.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> FileTable;
    private MyIHeap<Integer, Value> heap;

    IStmt originalProgram;

    public PrgState() {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        FileTable = new MyDictionary<>();
        heap = new MyHeap<>();
    }

    public PrgState(PrgState state) {
        this.symTable = state.symTable;
        this.originalProgram = state.originalProgram;
        this.exeStack = state.exeStack;
        this.out = state.out;
        this.FileTable = state.FileTable;
        this.heap = state.heap;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> FileTable) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg;
        this.FileTable = FileTable;
        stk.push(prg);
    }

    public PrgState(IStmt prg) {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, Value>();
        out = new MyList<Value>();
        originalProgram = prg;
        exeStack.push(prg);
        FileTable = new MyDictionary<StringValue, BufferedReader>();
        heap = new MyHeap<>();
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

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return FileTable;
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

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> FileTable) {
        this.FileTable = FileTable;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer, Value> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Exe stack : " + exeStack.toString() + "\n";
        str += "Sym table : " + symTable.toString() + "\n";
        str += "Out : " + out.toString() + "\n";
        return str;
    }

    public String fileToString() {
        StringBuilder str = new StringBuilder(exeStack.fileToString() + '\n' + symTable.fileToString() + out.fileToString() + "\nFileTable:\n");
        for (StringValue key : FileTable.keySet()) {
            str.append(key.getValue()).append("\n");
        }
        str.append(heap.toString()).append("\n");
        return  str.toString();
    }
}
