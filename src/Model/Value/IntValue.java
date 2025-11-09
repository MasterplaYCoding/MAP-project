package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value {
    int val;

    public IntValue() {}

    public IntValue(int val){
        this.val = val;
    }
    public int getVal(){
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public boolean equals(Object o) {
        return  o instanceof IntValue && ((IntValue) o).getVal() == val;
    }

}
