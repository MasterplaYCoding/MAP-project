package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.ExpressionsEvaluation.VariableAlreadyDeclared;
import Model.Exception.MyException;
import Model.Other.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

public class VarDeclStmt implements  IStmt {
    private final String name;
    private final Type typ;

    public VarDeclStmt(String name, Type typ) {
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return typ.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if(symTbl.isDefined(name)) throw new VariableAlreadyDeclared();
        if(typ ==  null) throw new ValueTypeError("Type is null");
        symTbl.put(name, typ.defaultValue());
        return null;
    }

    @Override
    public IStmt deepcopy() {
        return  new VarDeclStmt(name, typ);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(name,typ);
        return typeEnv;
    }

}
