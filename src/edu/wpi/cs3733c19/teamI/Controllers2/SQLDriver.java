
package edu.wpi.cs3733c19.teamI.Controllers2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.*;
import java.util.*;

import edu.wpi.cs3733c19.teamI.Entities.DataField;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.*;

public class SQLDriver{
    //TODO: URGENT!!!!! converte ALL user DB values to be inserted as strings!!!

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
    public int l_distance(String a, String b){
       // System.out.println("in l_distance");
        a.toLowerCase();
        b.toLowerCase();

        int counter = Math.abs(a.length()-b.length());
        if (a.length() >= b.length()){
            for (int i = 0; i < b.length(); i++){
                if (b.charAt(i) != a.charAt(i)){
                    counter++;
                }
            }
        }
        else{
            for (int i = 0; i < a.length(); i++){
                if (b.charAt(i) != a.charAt(i)){
                    counter++;
                }
            }
        }
        return counter;
    }
    public int dl_distance(String source, String target){
        // throw if parameter is a null
        source.toLowerCase();
        target.toLowerCase();
        if (source == null || target == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }

        int sourceLength = source.length();
        int targetLength = target.length();

        //end with a
        if (sourceLength == 0) return  sourceLength;
        if (targetLength == 0) return  targetLength;

        int[][] dist = new int[sourceLength+1][targetLength+1];

        for (int i =0; i < sourceLength +1; i++){
            dist[i][0] =i;
        }

        for (int j =0; j < targetLength +1; j++){
            dist[0][j] =j;
        }

        for (int i = 1; i < sourceLength +1; i++){
            for (int j = 1; j < targetLength +1; j++){
                int cost = source.charAt(i-1) == target.charAt(j-1) ? 0:1;
                dist[i][j] = Math.min(
                        Math.min(dist[i-1][j] + 1, dist[i][j-1]+1), dist[i-1][j-1]+cost);

                if (i > 1 && j >1 && source.charAt(i-1) == target.charAt(j-2) && source.charAt(i-2) == target.charAt(j-1)){
                    dist[i][j] = Math.min(dist[i][j],dist[i-2][j-2] + cost);
                }
            }
        }
        return dist[sourceLength][targetLength];

    }
    public void update_user_rfid(int id, String rfid) throws Exception{
        ArrayList<String>_target_cols = new ArrayList<String>();
        _target_cols.add("rfid");
        ArrayList<DBValue>_value_cols = new ArrayList<DBValue>();
        _value_cols.add(new DBValue<String>(rfid));

        generic_update("user_credentials", "user_credentials.db", _target_cols, _value_cols, "id", new DBValue<Integer>(id));
    }
    public void create_user_account(String email, String password, String role) throws Exception{
        String [] columns = {"RepIDnum", "firstName", "lastName", "phoneNumber", "streetAdress",  "city", "zipCode", "state", "deliminator", "email", "password", "role", "rfid"};
        DBTypes [] full_types = {new DBTypes("real"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"), new DBTypes("text"),
                new DBTypes("text"), new DBTypes("text"), new DBTypes("text")};
        /*
        role is a in integer 0 or 1: 0 => Agent, 1 => Manufacturer
         */
        try{
            create_table("credentials", "user_database.db", columns, full_types);
        }
        catch (Exception e){
            //pass
            //db already created
        }
        double _id = 1;
        for (HashMap<String, ReturnedValue>result:select_all("user_database.db", "credentials")){
            double _temp_id = result.get("RepIDnum").to_double();
            if (_temp_id > _id){
                _id = _temp_id;
            }

        }
        DBValue [] all_vals = {new DBValue<Integer>((int)_id), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(""), new DBValue<String>(email),  new DBValue<String>(password), new DBValue<String>(role), new DBValue<String>("")};
        insert_vals("credentials", "user_database.db", all_vals);


    }

    public ArrayList<HashMap<String, ReturnedValue>>search_for_dl_multiple(String tablename, String filename, ArrayList<String>keys, String _user_input, int top_results) throws Exception{
        if (top_results < 1){
            throw new Exception("'top_results' must be a value greater than zero");
        }
        ArrayList<Integer>all_distances = new ArrayList<Integer>();
        HashMap<Integer, HashMap<String, ReturnedValue>>results = new HashMap<Integer, HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:select_all(filename, tablename)){


            int _count = 0;
            boolean seen_result = false;
            for (String key:keys){
                String _val = result.get(key).to_string();
                System.out.println(_val);
                System.out.println(_user_input);
                System.out.println("---------------------");

                if (_val.length() > 0 && _user_input.length() > 0 && filter_immediate(_val, _user_input)){
                    System.out.println("Got in here");
                    _count += dl_distance(_val, _user_input);
                    if (!seen_result){
                        seen_result = true;
                    }

                }


            }
            if (seen_result){
                all_distances.add(_count);
                results.put(_count, result);
                System.out.println("Beverage type here "+result.get("beverageType").to_string());
            }


        }
        ArrayList<HashMap<String, ReturnedValue>>final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        if (all_distances.size() > 0){
            ArrayList<Integer>_distances = new ArrayList<Integer>();
            for (int val:all_distances){
                boolean _flag = true;
                for (int _val:_distances){
                    if (val == _val){
                        _flag = false;
                        break;
                    }

                }
                if (_flag){
                    _distances.add(val);
                }
            }
            all_distances = _distances;
            Collections.sort(all_distances);

            int _final_count = 0;
            int index_counter = 1;
            int _size = all_distances.size();
            int last_seen_distance = all_distances.get(0);
            final_results.add(results.get(last_seen_distance));
            while (_final_count < top_results && index_counter < _size){
                int new_val = all_distances.get(index_counter);
                final_results.add(results.get(new_val));
                if (new_val != last_seen_distance){
                    _final_count++;
                }
                index_counter++;
                last_seen_distance = new_val;

            }
        }


        return final_results;

    }


    public boolean filter_immediate(String a, String b){
        a.toLowerCase();
        b.toLowerCase();
        if (a.length() == b.length()){
            for (int i = 0; i < b.length(); i++) {
                if (Character.toLowerCase(b.charAt(i)) != Character.toLowerCase(a.charAt(i))) {
                    return false;
                }

            }
            return true;
        }
        if (a.length() >= b.length()){
            boolean flag = false;
            for (int d = 0; d <a.length()-b.length(); d++){
                for (int i = 0; i < b.length(); i++) {
                    if (Character.toLowerCase(b.charAt(i)) != Character.toLowerCase(a.charAt(d + i))) {
                        flag = false;
                        break;
                    } else {
                        System.out.println("got true result");
                        flag = true;
                    }

                }
                if (flag){
                    break;
                }

            }
            //System.out.println(flag);
            return flag;

        }
        else{
            boolean flag = false;
            for (int d = 0; d <b.length()-a.length(); d++){
                for (int i = 0; i < a.length(); i++) {
                    if (Character.toLowerCase(a.charAt(i)) != Character.toLowerCase(b.charAt(d + i))) {
                        flag = false;
                        break;
                    } else {
                        System.out.println("got true result");
                        flag = true;
                    }

                }
                if (flag){
                    break;
                }

            }

            return flag;
        }


    }
    public ArrayList<HashMap<String, ReturnedValue>>search_for_l_multiple(String tablename, String filename, ArrayList<String>keys, String _user_input, int top_results) throws Exception{
        System.out.println("In search_for_l_multiple");
        if (top_results < 1){
            throw new Exception("'top_results' must be a value greater than zero");
        }
        ArrayList<Integer>all_distances = new ArrayList<Integer>();
        HashMap<Integer, HashMap<String, ReturnedValue>>results = new HashMap<Integer, HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:select_all(filename, tablename)){


            int _count = 0;
            boolean seen_result = false;
            for (String key:keys){
                String _val = result.get(key).to_string();
                System.out.println(_val);
                System.out.println(_user_input);
                System.out.println("---------------------");

                if (_val.length() > 0 && _user_input.length() > 0 && filter_immediate(_val, _user_input)){
                    System.out.println("Got in here");
                    _count += l_distance(_val, _user_input);
                    if (!seen_result){
                        seen_result = true;
                    }

                }


            }
            if (seen_result){
                all_distances.add(_count);
                results.put(_count, result);
                System.out.println("Beverage type here "+result.get("beverageType").to_string());
            }


        }
        ArrayList<HashMap<String, ReturnedValue>>final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        if (all_distances.size() > 0){
            ArrayList<Integer>_distances = new ArrayList<Integer>();
            for (int val:all_distances){
                boolean _flag = true;
                for (int _val:_distances){
                    if (val == _val){
                        _flag = false;
                        break;
                    }

                }
                if (_flag){
                    _distances.add(val);
                }
            }
            all_distances = _distances;
            Collections.sort(all_distances);

            int _final_count = 0;
            int index_counter = 1;
            int _size = all_distances.size();
            int last_seen_distance = all_distances.get(0);
            final_results.add(results.get(last_seen_distance));
            while (_final_count < top_results && index_counter < _size){
                int new_val = all_distances.get(index_counter);
                final_results.add(results.get(new_val));
                if (new_val != last_seen_distance){
                    _final_count++;
                }
                index_counter++;
                last_seen_distance = new_val;

            }
        }


        return final_results;

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
    public HashMap<String, ReturnedValue>get_data_by_form_id(String tablename, String filename, String column, DBValue value) throws Exception{
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            //if (result.get(column).)

            boolean flag = false;
            if (value.statement().equals("setString")){
                flag = (result.get(column).to_string().equals(value.to_string()));
                //System.out.println(result.get(column).to_string());
            }
            else{
                flag = (result.get(column).to_double() == value.to_double());
                //System.out.println(result.get(column).to_string());
            }

            if (flag){
              //  System.out.println("in the main result find");
              //  System.out.println(result.get("formID").to_double());
                return result;
            }
        }
        throw new Exception("Cannot find row");
    }
    public HashMap<String, ReturnedValue>get_data_by_value(String tablename, String filename, String column, DBValue value) throws Exception{
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            //if (result.get(column).)
            boolean flag = false;
            if (value.statement().equals("setString")){
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
    public void generic_update(String tablename, String filename, ArrayList<String>targetcols, ArrayList<DBValue>values, String anchor_col, DBValue anchor_val) throws Exception{
        Connection _connector = connect_file(filename);
        ArrayList<String>temp = new ArrayList<String>();
        for (String col:targetcols){
            temp.add(col+" = ?");
        }
        String statement = "UPDATE "+tablename+" SET "+String.join(", ", temp)+" WHERE "+anchor_col+" = ?";
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
               // System.out.println("sets a string");
                pstmt.setString(_count, val.to_string());
            }
            _count ++;
        }
        if (anchor_val.statement().equals("setInt")){
            pstmt.setInt(_count, anchor_val.to_int());
        }
        else if (anchor_val.statement().equals("setDouble")){
            pstmt.setDouble(_count, anchor_val.to_double());
        }
        else{
            pstmt.setString(_count, anchor_val.to_string());
        }


        pstmt.executeUpdate();
    }
    public void update(String tablename, String filename, ArrayList<String>targetcols, ArrayList<DBValue>values, String formid) throws Exception {

        //System.out.println("actually makes into update method");
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
              //  System.out.println("sets a string");
                pstmt.setString(_count, val.to_string());
            }
            _count ++;
        }
        pstmt.setString(_count, formid);

        pstmt.executeUpdate();

    }

    public ArrayList<HashMap<String, ReturnedValue>>search_for_l(String tablename, String filename, String target, String _key, int top_results) throws Exception{
        //required: @target must be a string
        //simple Levenshtein distance: https://en.wikipedia.org/wiki/Levenshtein_distance
        //sample method call: search_for_l("form_data", "form_data.db", "MyFancyTitle", "fancifulName");
        if (top_results < 1){
            throw new Exception("'top_results' must be a value greater than zero");
        }
        ArrayList<Integer>all_distances = new ArrayList<Integer>();
        HashMap<Integer, HashMap<String, ReturnedValue>>results = new HashMap<Integer, HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:select_all(filename, tablename)){


            int _count = l_distance(result.get(_key).to_string(), target);
            all_distances.add(_count);
            results.put(_count, result);



        }
        Collections.sort(all_distances);
        ArrayList<HashMap<String, ReturnedValue>>final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        int _final_count = 0;
        int index_counter = 1;
        int last_seen_distance = all_distances.get(0);
        final_results.add(results.get(last_seen_distance));
        while (_final_count < top_results){
            int new_val = all_distances.get(index_counter);
            final_results.add(results.get(new_val));
            if (new_val != last_seen_distance){
                _final_count++;
            }
            index_counter++;
            last_seen_distance = new_val;

        }

        return final_results;
    }
    public ArrayList<HashMap<String, ReturnedValue>>search_for_dl(String tablename, String filename, String target, String _key, int top_results) throws Exception{
        //required: @target must be a string
        //simple Levenshtein distance: https://en.wikipedia.org/wiki/Levenshtein_distance
        //sample method call: search_for_l("form_data", "form_data.db", "MyFancyTitle", "fancifulName");
        if (top_results < 1){
            throw new Exception("'top_results' must be a value greater than zero");
        }
        ArrayList<Integer>all_distances = new ArrayList<Integer>();
        HashMap<Integer, HashMap<String, ReturnedValue>>results = new HashMap<Integer, HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result:select_all(filename, tablename)){

            int _count = dl_distance(result.get(_key).to_string(), target);
            all_distances.add(_count);
            results.put(_count, result);

        }
        Collections.sort(all_distances);
        ArrayList<HashMap<String, ReturnedValue>>final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        int _final_count = 0;
        int index_counter = 1;
        int last_seen_distance = all_distances.get(0);
        final_results.add(results.get(last_seen_distance));
        while (_final_count < top_results){
            int new_val = all_distances.get(index_counter);
            final_results.add(results.get(new_val));
            if (new_val != last_seen_distance){
                _final_count++;
            }
            index_counter++;
            last_seen_distance = new_val;

        }

        return final_results;
    }

    public ArrayList<HashMap<String, ReturnedValue>>search_sql_wildcard(String tablename, String filename, String target, String _key) throws Exception{
        //sample method call: search_for_l("form_data", "form_data.db", "MyFancyTitle", "fancifulName");
        String _query = "SELECT * FROM "+tablename+" WHERE "+_key+" LIKE "+"'%"+target+"%'";
      //  System.out.println(_query);
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
        HashMap<String, String>row_types = new HashMap<String, String>();
        Connection _about_db1 = connect_about_file(filename);
        Statement stmt1 = _about_db1.createStatement();
        ResultSet rs1 = stmt1.executeQuery("SELECT rowname, rowtype FROM "+tablename);
        while (rs1.next()) {
            row_types.put(rs1.getString("rowname"), rs1.getString("rowtype"));

        }

        Connection _connector = connect_file(filename);
        Statement conn = _connector.createStatement();
        ResultSet _rs_conn = conn.executeQuery(_query);
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

    public ArrayList<HashMap<String, ReturnedValue>>get_data_by_value(String tablename, String filename, LinkedList<String>search_fields, HashMap<String, DataField>targets) throws Exception{
        //System.out.println("in search method");

        ArrayList<HashMap<String, ReturnedValue>> final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            //System.out.println(result.get("status"));
            String _new_flag = result.get("status").to_string();
            if (_new_flag.equals("approved")){
                boolean _flag = false;
                for (String field:search_fields){
                   // System.out.println("search field: "+field);
                    ReturnedValue type1 = result.get(field);
                    DataField type2 = targets.get(field);


                    if (type1.type.equals("text")){
                        if (type1.to_string().equals(type2.getValue().toString())){
                          //  System.out.println("successfull comparison!!!");

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
                      //  System.out.println(type1.type);
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


    public ArrayList<HashMap<String, ReturnedValue>>get_user_data_by_value(String tablename, String filename, LinkedList<String>search_fields, HashMap<String, DataField>targets) throws Exception{
        System.out.println("in search method");

        ArrayList<HashMap<String, ReturnedValue>> final_results = new ArrayList<HashMap<String, ReturnedValue>>();
        for (HashMap<String, ReturnedValue>result: select_all(filename, tablename)){
            System.out.println("In first loop");
                boolean _flag = false;
                for (String field:search_fields){
                    System.out.println("search field: "+field);
                    ReturnedValue type1 = result.get(field);
                    DataField type2 = targets.get(field);

                    System.out.println("At end");
                    System.out.println("Type1: " + type1);


                    if (type1.type.equals("text")|| type1.type.equals("TEXT")){
                        System.out.println("Type accepted");
                        if (type1.to_string().equals(type2.getValue().toString())){
                            //  System.out.println("successfull comparison!!!");

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
                        //  System.out.println(type1.type);
                        if (type1.to_double() == Double.parseDouble(type2.getValue().toString())){
                            _flag = true;
                        }
                        else{
                            _flag = false;
                            break;
                        }

                    }


                }
                if (_flag) {
                    final_results.add(result);
                }



        }
        return final_results;
    }



    public static void setApprovalStatus(String formID, String approvalStatus) throws IOException, Exception {
        setField(formID, approvalStatus, "status");
    }

    public static void setApprovingUser(String formID, String approvalStatus) throws IOException, Exception {
        setField(formID, approvalStatus, "approvingUser");
    }

    public static void setApprovalDate(String formID, String approvalStatus) throws IOException, Exception {
        setField(formID, approvalStatus, "approvalDate");
    }

    public static void setExpirationDate(String formID, String approvalStatus) throws IOException, Exception {
        setField(formID, approvalStatus, "expirationDate");
    }

    public static void setField(String formID, String approvalStatus, String field) throws IOException, Exception{
        SQLDriver driver = new SQLDriver();
        HashMap<String, ReturnedValue> result = driver.get_data_by_value("form_data", "form_data.db", "formID", new DBValue<String>(formID));

        //result.get("formStatus");
        ReturnedValue value = new ReturnedValue(field, approvalStatus);
        result.replace(field, value);

        ArrayList<String> appStatType = new ArrayList<>();
        appStatType.add(field);

        ArrayList<DBValue> appStatus = new ArrayList<>();

        DBValue value1 = new DBValue<String>(approvalStatus);
        appStatus.add(value1);

        driver.update("form_data", "form_data.db", appStatType, appStatus, formID);
        //result.put("status", value);
    }


}
