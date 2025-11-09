package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue() {}

    public BoolValue(boolean val){
        this.val = val;
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public String toString() {
        return Boolean.toString(val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object o) {
        return  o instanceof BoolValue && ((BoolValue) o).getVal() == val;
    }

}
