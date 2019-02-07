package edu.wpi.cs3733c19.teamI.Entities;

/*
This class is used to store search parameters. The DB can have types of String, int or double. In order to be able
to group all search parameters into a single structure, we needed a way of representing multiple data types in one class.
Data field has 2 attributes, one of type string to hold what parameter we are storing. Another of "type" that contains
the value of the parameter. Typ can be ust about anything, and allows us to reference string, double and in in our
search hash map
 */
public class DataField<type> {

    private type Value; // name of parameter
    private String field; //value of parameter

    //setters
    protected void setValue(type Val)
    {
        this.Value = Val;
    }

    protected void setField(String Val)
    {
        this.field = Val;
    }

    //getters
    public type getValue()
    {
        return(this.Value);
    }

    String getField()
    {
        return(this.field);
    }

    //this "getter" will return a string rep of whatever data type was stored. This is useful for displaying results
    //TODO does this need a try-catch in case we cannot convert to string?
    String getStringVal(){
     return(Value.toString());
    }

    public DataField (type val, String fieldName)
    {
        this.Value = val;
        this.field = fieldName;
    }
}
