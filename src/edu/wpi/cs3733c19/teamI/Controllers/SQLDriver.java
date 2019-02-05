
package edu.wpi.cs3733c19.teamI.Controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.*;
import java.util.*;
import java.lang.reflect.Method;

 import edu.wpi.cs3733c19.teamI.Entities.DataField;

import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.*;
import edu.wpi.cs3733c19.teamI.Entities.DataField;

public class SQLDriver{


    public Connection connect_file(String filename) throws Exception{
        if (!filename.endsWith(".db")){
            throw new Exception("filename must end with '.db'");
        }
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+filename);
        return connection;
    }
    public Connection connect_about_file(String filename) throws Exception{
        if (!filename.endsWith(".db")){
            throw new Exception("filename must end with '.db'");
        }
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:_about_"+filename);
        return connection;
    }
    public DBTypes create_type(String type){
        return new DBTypes(type);

    }
    private void about_database(String tablename, String filename, String [] columns, DBTypes [] _types) throws Exception{
        Connection _connector = connect_file("_about_"+filename);
        Statement statement = _connector.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate("CREATE TABLE "+tablename+" (rowname text, rowtype text)");

        for (int i = 0; i < columns.length; i++){
            PreparedStatement pstmt = _connector.prepareStatement("INSERT INTO "+tablename+" VALUES (?, ?)");
            pstmt.setString(1, columns[i]);
            pstmt.setString(2, _types[i].type);
            pstmt.executeUpdate();
        }
        _connector.close();


    }
    public Connection create_table(String tablename, String filename, String [] columns, DBTypes [] _types) throws Exception{

        if (!filename.endsWith(".db")){
            throw new Exception("filename must end with '.db'");
        }
        Connection _connector = connect_file(filename);
        Statement statement = _connector.createStatement();
        statement.setQueryTimeout(30);


        ArrayList<String> col_type = new ArrayList<String>();
        for (int i = 0; i < columns.length; i++){
            col_type.add(columns[i]+" "+_types[i].type);

        }
        String _query = String.join(", ", col_type);

        statement.executeUpdate("CREATE TABLE "+tablename+" ("+_query+")");
        about_database(tablename, filename, columns, _types);

        return _connector;



    }
    public void insert_vals(String tablename, String filename, DBValue [] vals) throws Exception{
        if (!filename.endsWith(".db")){
            throw new Exception("filename must end with '.db'");
        }
        Connection _connector = connect_file(filename);


        ArrayList<String> _marks = new ArrayList<String>();
        for (int i = 0; i < vals.length; i++){
            _marks.add("?");
        }
        String _query = "INSERT INTO "+tablename+" VALUES ("+String.join(", ", _marks)+")";
        PreparedStatement pstmt = _connector.prepareStatement(_query);

        for (int i = 0; i < vals.length; i++){


            if (vals[i].statement() == "setString"){
                pstmt.setString(i+1, vals[i].to_string());
            }
            else if (vals[i].statement() == "setInt"){
                pstmt.setInt(i+1, vals[i].to_int());
            }
            else{
                pstmt.setDouble(i+1, vals[i].to_double());
            }


        }


        pstmt.executeUpdate();

        //return _connector;

    }
    //public ArrayList<HashMap<String, ReturnedValue>>select_all(String filename, String tablename) throws Exception{
    public ArrayList<HashMap<String, ReturnedValue>> select_all(String filename, String tablename) throws Exception{
        if (!filename.endsWith(".db")){
            throw new Exception("filename must end with '.db'");
        }
        Connection _about_db = connect_about_file(filename);
        ArrayList<String>columns = new ArrayList<String>();
        Statement stmt = _about_db.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowname FROM "+tablename);
        while (rs.next()) {
           columns.add(rs.getString("rowname"));
        }

        return select_custom(filename, tablename, columns);
    }
    public ArrayList<HashMap<String, ReturnedValue>>select_custom(String filename, String tablename, ArrayList<String>columns) throws Exception{
        HashMap<String, String>row_types = new HashMap<String, String>();
        Connection _about_db = connect_about_file(filename);
        Statement stmt = _about_db.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT rowname, rowtype FROM "+tablename);
        while (rs.next()) {
            row_types.put(rs.getString("rowname"), rs.getString("rowtype"));

        }

        Connection _connector = connect_file(filename);
        Statement conn = _connector.createStatement();
        ResultSet _rs_conn = conn.executeQuery("SELECT "+String.join(", ", columns)+" FROM "+tablename);
        ArrayList<HashMap<String, ReturnedValue>>full_payload = new ArrayList<HashMap<String, ReturnedValue>>();

        while (_rs_conn.next()){

            HashMap<String, ReturnedValue>_temp = new HashMap<String, ReturnedValue>();
            for (String col:columns){
                _temp.put(col, new ReturnedValue(_rs_conn.getString(col).toString(), row_types.get(col)));
            }
            full_payload.add(_temp);
        }
        return full_payload;

    }
    public HashMap<String, ReturnedValue>get_data_by_value(String tablename, String filename, String column, DBValue value) throws Exception{
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            //if (result.get(column).)
            boolean flag = false;
            if (value.statement() == "setString"){
                flag = (result.get(column).to_string() == value.to_string());
            }
            else{
                flag = (result.get(column).to_double() == value.to_double());
            }

            if (flag){
                return result;
            }
        }
        throw new Exception("Cannot find row");
    }
    public void update(String tablename, String filename, ArrayList<String>targetcols, ArrayList<DBValue>values, int formid) throws Exception{
        Connection _connector = connect_file(filename);
        ArrayList<String>temp = new ArrayList<String>();
        for (String col:targetcols){
            temp.add(col+" = ?");
        }
        String statement = "UPDATE "+tablename+" SET "+String.join(", ", temp)+" WHERE formID = ?";
        PreparedStatement pstmt = _connector.prepareStatement(statement);
        int _count = 1;
        for (DBValue val:values){
            if (val.statement().equals("setInt")){
                pstmt.setInt(_count, val.to_int());
            }
            else if (val.statement().equals("setDouble")){
                pstmt.setDouble(_count, val.to_double());

            }
            else{
                pstmt.setString(_count, val.to_string());
            }
            _count ++;


        }
        pstmt.setInt(_count, formid);

    }
    public ArrayList<HashMap<String, ReturnedValue>>get_data_by_value(String tablename, String filename, LinkedList<String>search_fields, HashMap<String, DataField>targets) throws Exception{
        System.out.println("in search method");

        ArrayList<HashMap<String, ReturnedValue>> final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            double _new_flag = result.get("status").to_double();
            if (_new_flag == 0){
                boolean _flag = false;
                for (String field:search_fields){
                    System.out.println("search field: "+field);
                    ReturnedValue type1 = result.get(field);
                    DataField type2 = targets.get(field);

                    System.out.println("type in final search");


                    if (type1.type.equals("text")){
                        if (type1.to_string().equals(type2.getValue().toString())){
                            System.out.println("successfull comparison!!!");

                            _flag = true;
                        }
                        else{
                            //System.out.println("comparision failed");
                            _flag = false;
                            break;
                        }
                    }
                    else{
                        //System.out.println("should not see this");
                        System.out.println(type1.type);
                        if (type1.to_double() == Double.parseDouble(type2.getValue().toString())){
                            _flag = true;
                        }
                        else{
                            _flag = false;
                            break;
                        }

                    }


                }
                if (_flag){
                    final_results.add(result);
                }
            }



        }
        return final_results;
    }

    public static void setApprovalStatus(int formID, String approvalStatus) throws IOException, Exception {
        SQLDriver driver = new SQLDriver();
        HashMap<String, ReturnedValue>result = driver.get_data_by_value("form_data", "form_data.db", "formID", new DBValue<Integer>(formID));

        //result.get("formStatus");
        //result.entrySet(driver, approvalStatus);

    }
}
