package edu.wpi.cs3733c19.teamI.Entities;

public class DataField<type> {

    private type Value;
    private String field;

    //setter
    protected void setValue(type Val)
    {
        this.Value = Val;
    }

    protected void setField(String Val)
    {
        this.field = Val;
    }

    //getter
    public type getValue()
    {
        return(this.Value);
    }

    String getField()
    {
        return(this.field);
    }

    public DataField (type val, String fieldName)
    {
        this.Value = val;
        this.field = fieldName;
    }
}
