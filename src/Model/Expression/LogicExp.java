package Model.Expression;


import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.DivisionByZeroException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

public class LogicExp implements Exp {
    private final Exp e1,e2;
    private final int op; // 1 - and, 2 - or

    public LogicExp(Exp e1, Exp e2, int op) {
        this.e1 = e1;
        this.e2 = e2;
        this.op = op;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1,v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new BoolType())){
            v2 =  e2.eval(tbl, heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new BoolValue(n1 & n2);
                else if(op == 2) return new BoolValue(n1 | n2);
            }
            throw new ValueTypeError("second operand is not boolean");
        }
        throw new ValueTypeError("first operand is not boolean");
    }

    @Override
    public Exp deepcopy() {
        return new LogicExp(e1.deepcopy(), e2.deepcopy(), op);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            } else throw new MyException("second operand is not a boolean");
        }else throw new MyException("first operand is not a boolean");
    }


    @Override
    public String toString() {
        return switch(op) {
            case 1 -> e1.toString() + " AND " + e2.toString();
            case 2 -> e1.toString() + " OR " + e2.toString();
            default ->  e1.toString() + " " + e2.toString();
        };
    }
}
