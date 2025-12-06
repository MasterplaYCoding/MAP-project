package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.KeyDoesntExist;
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

public class closeRFile implements IStmt{

    Exp exp;
    public closeRFile(Exp exp){
        this.exp = exp;
    }

    public closeRFile(){}

    @Override
    public PrgState execute(PrgState prgState) throws MyException {

        MyIDictionary<String, Value> symTbl = prgState.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = prgState.getFileTable();
        MyIHeap<Integer, Value> heap =  prgState.getHeap();

        StringValue val = (StringValue) new StringType().defaultValue();
        try {
            val = (StringValue) exp.eval(symTbl, heap);
        }
        catch (Exception e) {
            throw new ValueTypeError("Expression is not of type String");
        }
        if (!fileTable.isDefined(val))
            throw new KeyDoesntExist("File name " + val + " is not found");
        BufferedReader br = fileTable.get(val);
        try {
            br.close();
        }
        catch (Exception e) {
            throw new FileException("Something went wrong while closing file");
        }
        fileTable.remove(val);
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return  new closeRFile(this.exp.deepcopy());
    }

    @Override
    public String toString(){
        return "closeRFile("+exp.toString()+")";
    }
}
