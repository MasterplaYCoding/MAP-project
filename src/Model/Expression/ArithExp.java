package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ADTExceptions.NullKeyException;
import Model.Exception.ExpressionsEvaluation.DivisionByZeroException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.IntType;
import Model.Value.IntValue;
import Model.Value.Value;

public class ArithExp implements Exp {
    private final Exp e1, e2;
    private final int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char sign, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        switch (sign) {
            case '+' -> op = 1;
            case '-' -> op = 2;
            case '*' -> op = 3;
            case '/' -> op = 4;
            default -> op = 0;
        }
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1,v2;
        v1 = e1.eval(tbl, heap);
        if(v1.getType().equals(new IntType())){
            v2 =  e2.eval(tbl, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op == 1) return new IntValue(n1+n2);
                else if(op == 2) return new IntValue(n1-n2);
                else if(op == 3) return new IntValue(n1*n2);
                else if(op == 4) {
                    if (n2==0) throw new DivisionByZeroException();
                    return new IntValue(n1/n2);
                }
            }
            throw new ValueTypeError("second operand is not an integer");
        }
        throw new ValueTypeError("first operand is not an integer");
    }

    @Override
    public Exp deepcopy() {

        char sign;
        switch (op) {
            case 1 -> sign = '+';
            case 2 -> sign = '-';
            case 3 -> sign = '*';
            case 4 -> sign = '/';
            default -> sign = '?';
        }

        return new ArithExp(sign, this.e1.deepcopy(), this.e2.deepcopy());
    }

    @Override
    public String toString() {
        return switch (op) {
            case 1 -> e1.toString() + " + " + e2.toString();
            case 2 -> e1.toString() + " - " + e2.toString();
            case 3 -> e1.toString() + " * " + e2.toString();
            case 4 -> e1.toString() + " / " + e2.toString();
            default -> throw new DivisionByZeroException();
        };
    }
}
