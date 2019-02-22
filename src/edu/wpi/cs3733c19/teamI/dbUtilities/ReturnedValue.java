package edu.wpi.cs3733c19.teamI.dbUtilities;

public class ReturnedValue {
    public String from_db;
    public String type;

    public ReturnedValue(String _from_db, String _type){
        from_db = _from_db;
        type = _type;
    }
    public int to_int(){
        return Integer.parseInt(from_db);
    }
    public String to_string(){
        return from_db;
    }
    public Double to_double(){
        return Double.parseDouble(from_db);
    }
}
