package Model.Expression;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Exception.ExpressionsEvaluation.DivisionByZeroException;
import Model.Exception.ExpressionsEvaluation.ValueTypeError;
import Model.Exception.MyException;
import Model.Heap.MyIHeap;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;


public class RelationExp implements Exp {
    private final Exp ex1, ex2;
    private final int relation;

    // 1 - <
    // 2 - <=
    // 3 - ==
    // 4 - !=
    // 5 - >
    // 6 - >=

    public RelationExp(Exp ex1, Exp ex2, String relation) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        switch (relation) {
            case "<" -> this.relation = 1;
            case "<=" -> this.relation = 2;
            case "==" -> this.relation = 3;
            case "!=" -> this.relation = 4;
            case ">" -> this.relation = 5;
            case ">=" -> this.relation = 6;
            default -> this.relation = 0;
        }
    }

    public Exp getEx1() {
        return ex1;
    }
    public Exp getEx2() {
        return ex2;
    }
    public int getRelation() {
        return relation;
    }

    @Override
    public String toString() {
        return switch (relation) {
            case 1 -> ex1.toString() + " < " + ex2.toString();
            case 2 -> ex1.toString() + " <= " + ex2.toString();
            case 3 -> ex1.toString() + " == " + ex2.toString();
            case 4 -> ex1.toString() + " != " + ex2.toString();
            case 5 -> ex1.toString() + " > " + ex2.toString();
            case 6 ->ex1.toString() + " >= " + ex2.toString();
            default -> "";
        };
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> heap) throws MyException {
        Value v1,v2;
        v1 = ex1.eval(tbl, heap);
        if(v1.getType().equals(new IntType())){
            v2 =  ex2.eval(tbl, heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(relation == 1) return new BoolValue(n1<n2);
                else if(relation == 2) return new BoolValue(n1<=n2);
                else if(relation == 3) return new BoolValue(n1==n2);
                else if(relation == 4) return new BoolValue(n1!=n2);
                else if(relation == 5) return new BoolValue(n1>n2);
                else if(relation == 6) return new BoolValue(n1>=n2);
            }
            throw new ValueTypeError("second operand is not an integer");
        }
        throw new ValueTypeError("first operand is not an integer");
    }

    @Override
    public Exp deepcopy() {

        String sign;
        switch (relation) {
            case 1 -> sign = "<";
            case 2 -> sign = "<=";
            case 3 -> sign = "==";
            case 4 -> sign = "!=";
            case 5 -> sign = ">";
            case 6 -> sign = ">=";
            default -> sign = "<";
        }

        return new RelationExp(this.ex1.deepcopy(), this.ex2.deepcopy(), sign);
    }
}
