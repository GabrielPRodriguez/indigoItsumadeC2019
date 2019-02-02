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
    protected type getValue()
    {
        return(this.Value);
    }

    protected String getField()
    {
        return(this.field);
    }

    DataField(type val){
        this.Value = val;
    }
}
