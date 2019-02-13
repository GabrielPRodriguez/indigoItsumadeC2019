package edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities;
import java.util.*;


public class DBValue<ValType> {
    public ValType value;
    public DBValue(ValType _the_val){
        value = _the_val;
    }
    public int to_int(){

        return Integer.parseInt(value.toString());

    }
    public double to_double(){
        return Double.parseDouble(value.toString());
    }
    public String to_string(){
        return value.toString();
    }

    public String statement(){
        HashMap<String, String> db_type = new HashMap<>();
        db_type.put("Integer", "setInt");
        db_type.put("String", "setString");
        db_type.put("Double", "setDouble");
        return db_type.get(((Object) value).getClass().getSimpleName());
    }
}
