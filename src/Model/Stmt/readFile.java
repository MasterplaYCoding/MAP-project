package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.KeyDoesntExist;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.FilesExepctions.FileException;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    Exp exp;
    String var_name;

    public readFile(Exp exp,  String var_name) {
        this.exp = exp;
        this.var_name = var_name;
    }

    public readFile() {}

    @Override
    public PrgState execute(PrgState prgState) throws MyException {

        MyIDictionary<String, Value> symTable = prgState.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = prgState.getFileTable();
        MyIHeap<Integer, Value> heap =  prgState.getHeap();

        if(!symTable.isDefined(var_name))
            throw new KeyDoesntExist("Variable "+var_name+" is not defined");

        if(!symTable.get(var_name).getType().equals(new IntType()))
            throw new ValueTypeError("Type of variable "+var_name+" is not int");

        StringValue val = (StringValue) new StringType().defaultValue();
        try {
            val = (StringValue) exp.eval(symTable, heap);
        }
        catch (Exception e) {
            throw new ValueTypeError("Exp should evaluate to StringValue");
        }
        if(!fileTable.isDefined(val))
            throw new KeyDoesntExist("File name " + val + " is not found");
        BufferedReader br = fileTable.get(val);
        try {
            String line = br.readLine();
            int x;
            if (line == null)
                x = 0;
            else
                x = Integer.parseInt(line);
            symTable.update(var_name, new IntValue(x));
        }
        catch (IOException e) {
            throw new FileException("Something went wrong");
        }
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new readFile(this.exp.deepcopy(),  this.var_name);
    }

    @Override
    public String toString(){
        return "readFile("+exp.toString()+", "+var_name+")";
    }
}
