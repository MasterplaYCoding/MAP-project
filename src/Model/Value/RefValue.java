package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value{
    int address;
    Type locationType;
    public int getAddr() {return address;}
    public Type getLocationType() {return locationType;}
    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public void setAddress(int address) {this.address = address;}

    @Override
    public Type getType() { return new RefType();}

    @Override
    public Value deepCopy() {
        return new RefValue(address, locationType);
    }

    @Override
    public String toString() {
        return "("+address+","+locationType+")";
    }
}