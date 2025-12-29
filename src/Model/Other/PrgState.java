package Model.Other;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Exception.MyException;
import Model.Exception.StatementsExecution.ExecutionStackEmpty;
import Model.Heap.MyHeap;
import Model.Heap.MyIHeap;
import Model.Stmt.IStmt;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private MyIDictionary<StringValue, BufferedReader> FileTable;
    private MyIHeap<Integer, Value> heap;
    private static int nextId = 0;
    private int id;

    private static synchronized int getNextId() {
        return ++nextId;
    }

    public int getIdOfState() {
        return id;
    }

    public void updateId(){
        id = getNextId();
    }

    IStmt originalProgram;

    public PrgState() {
        exeStack = new MyStack<>();
        symTable = new MyDictionary<>();
        out = new MyList<>();
        FileTable = new MyDictionary<>();
        heap = new MyHeap<>();
        id = getNextId();
    }

    public PrgState(PrgState state) {
        this.symTable = state.symTable;
        this.originalProgram = state.originalProgram;
        this.exeStack = state.exeStack;
        this.out = state.out;
        this.FileTable = state.FileTable;
        this.heap = state.heap;
        this.id = state.id;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> FileTable) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg.deepcopy();
        this.FileTable = FileTable;
        stk.push(originalProgram);
        id = getNextId();
    }

    public PrgState(IStmt prg) {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, Value>();
        out = new MyList<Value>();
        MyIDictionary<String, Type> typeEnv = new MyDictionary<>();
        prg.typecheck(typeEnv);
        originalProgram = prg.deepcopy();
        exeStack.push(originalProgram);
        FileTable = new MyDictionary<StringValue, BufferedReader>();
        heap = new MyHeap<>();
        id = getNextId();
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
        String str = "Program id = " + id + "\n";
        str += "Exe stack : " + exeStack.toString() + "\n";
        str += "Sym table : " + symTable.toString() + "\n";
        str += "Out : " + out.toString() + "\n";
        return str;
    }

    public String fileToString() {
        StringBuilder str = new StringBuilder("Program id = " + id + "\n");
        str.append(exeStack.fileToString()).append('\n').append(symTable.fileToString()).append(out.fileToString()).append("\nFileTable:\n");
        for (StringValue key : FileTable.keySet()) {
            str.append(key.getValue()).append("\n");
        }
        str.append(heap.toString()).append("\n");
        return  str.toString();
    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new ExecutionStackEmpty("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}
