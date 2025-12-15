package Model.Stmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.KeyDoesntExist;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Expression.Exp;
import Model.Heap.MyIHeap;
import Model.Other.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class HeapAllocation implements IStmt {
    String id;
    Exp exp;

    public HeapAllocation(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();


        if(symTbl.isDefined(id) && symTbl.get(id).getType().equals(new RefType()))
        {
            Value val = exp.eval(symTbl, heap).deepCopy();
            RefValue ref = (RefValue) symTbl.get(id);

            if (val.getType().equals(ref.getLocationType())) {
                int key = heap.add(val);
                ref.setAddress(key);
                symTbl.update(id, ref);
            }
            else {
                throw new ValueTypeError("The expression's type doesn't match with the reference type");
            }
        }
        else {
            throw new KeyDoesntExist("The id " + id + " does not exist or the type is not a RefType");
        }

        return null;
    }

    @Override
    public IStmt deepcopy() {
        return new HeapAllocation(this.id, this.exp.deepcopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.get(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new ValueTypeError("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new("+id+","+exp.toString()+")";
    }
}
