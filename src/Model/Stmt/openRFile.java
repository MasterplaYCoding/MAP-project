package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.KeyAlreadyExists;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.FilesExepctions.FileException;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.StringType;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements IStmt {

    Exp exp;

    public openRFile(Exp exp) {
        this.exp = exp;
    }

    public openRFile() {}

    @Override
    public PrgState execute(PrgState prgState) throws MyException {
        MyIDictionary<String, Value> tbl = prgState.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = prgState.getFileTable();
        MyIHeap<Integer, Value> heap =  prgState.getHeap();
        if(!exp.eval(tbl, heap).getType().equals(new StringType()))
            throw new ValueTypeError("Wrong expression type");
        StringValue fileName = (StringValue) exp.eval(tbl, heap);
        if(fileTable.isDefined(fileName))
            throw new KeyAlreadyExists();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName.getValue()));
            fileTable.put(fileName, br);
        } catch (IOException e) {
            throw new FileException("OpenRFile: cannot open file '" + fileName + "': " + e.getMessage());
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new openRFile(this.exp.deepcopy());
    }

    @Override
    public String toString() {
        return "openRFile(" +  exp.toString() + ")";
    }
}
