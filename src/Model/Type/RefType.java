package Model.Type;

import Model.Value.RefValue;
import Model.Value.Value;

public class RefType implements Type{
    Type inner;

    public RefType() {
        inner = this;
    }
    public RefType(Type inner) {this.inner=inner;}
    public Type getInner() {return inner;}

    @Override
    public boolean equals(Object another){
        return another instanceof RefType;
    }

    @Override
    public String toString() { return "Ref(" +inner.toString()+")";}

    @Override
    public Value defaultValue() { return new RefValue(0,inner);}
}