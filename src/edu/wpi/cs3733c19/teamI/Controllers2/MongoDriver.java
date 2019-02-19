package edu.wpi.cs3733c19.teamI.Controllers2;

import java.util.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import edu.wpi.cs3733c19.teamI.Entities.DataField;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.*;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.*;


public class MongoDriver {
    private String _url;
    private String [] default_rows;
    public MongoDriver(){
        this._url = "mongodb://localhost:27017";

    }
    public MongoDriver(String url){
        this._url = url;
    }

    public MongoDriver(String [] default_rows){
        this.default_rows = default_rows;
        this._url = "mongodb://localhost:27017";
    }
    public MongoDriver(String url, String [] default_rows){
        this._url = url;
        this.default_rows = default_rows;
    }
    //----------------------START OF SEARCH METHODS-------------------
    public int l_distance(String a, String b){
        // System.out.println("in l_distance");
        a = a.toLowerCase();
        b = b.toLowerCase();

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
        source = source.toLowerCase();
        target = target.toLowerCase();
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

    //----------------------END OF SEARCH METHODS---------------------

    public void insert_vals(String tablename, String filename, DBValue [] vals) throws Exception{

        if (default_rows == null){
            throw new Exception("row names not specified");
        }
        if (default_rows.length != vals.length){
            throw new Exception("row name length does not match value length");
        }
        MongoClient mongo = new MongoClient(new MongoClientURI(_url));
        if (filename.endsWith(".db")){
            filename = filename.substring(0, filename.length()-3);
        }

        MongoDatabase database = mongo.getDatabase(filename);
        boolean flag = true;
        try{
            database.createCollection(tablename);
        }
        catch(Exception e){
            //collection with tablename already exists in filename
            flag = false;
        }
        System.out.println("flag for insert_val");
        System.out.println(flag);

        MongoDatabase about_database = mongo.getDatabase("_about_"+filename);
        try{
            about_database.createCollection(tablename);
            MongoCollection<Document> about_collection = about_database.getCollection(tablename);

            for (int i = 0; i < default_rows.length; i++){
                Document about_document;
                about_document = new Document("rowname", default_rows[i]);
                about_document.append("rowtype", vals[i].statement());
                about_collection.insertOne(about_document);
                System.out.println(about_collection);
            }

        }
        catch(Exception e){
            //pass
        }


        MongoCollection<Document> collection = database.getCollection(tablename);

        Document document;
        if (vals[0].statement().equals("setInt")){
            document = new Document(default_rows[0], vals[0].to_int());
        }
        else if (vals[0].statement().equals("setDouble")){
            document = new Document(default_rows[0], vals[0].to_double());
        }
        else{
            document = new Document(default_rows[0], vals[0].to_string());
        }
        for (int i = 1; i < vals.length; i++){
            if (vals[i].statement().equals("setInt")){
                document.append(default_rows[i], vals[i].to_int());
            }
            else if (vals[i].statement().equals("setDouble")){
                document.append(default_rows[i], vals[i].to_double());
            }
            else{
                document.append(default_rows[i], vals[i].to_string());
            }
        }
        collection.insertOne(document);

    }
    public ArrayList<HashMap<String, ReturnedValue>> select_all(String filename, String tablename) throws Exception{
        if (filename.endsWith(".db")){
            filename = filename.substring(0, filename.length()-3);
        }
        MongoClient mongo = new MongoClient(new MongoClientURI(_url));
        MongoDatabase database = mongo.getDatabase(filename);
        try{
            database.createCollection(tablename);
        }
        catch(Exception e){
            //collection with tablename already exists in filename
        }
        MongoCollection<Document> collection = database.getCollection(tablename);
        List<Document> entries = (List<Document>) collection.find().into(
                new ArrayList<Document>());
        MongoDatabase about_database = mongo.getDatabase("_about_"+filename);
        MongoCollection<Document> about_collection = about_database.getCollection(tablename);
        List<Document> about_entries = (List<Document>) about_collection.find().into(
                new ArrayList<Document>());
        System.out.println(entries);
        System.out.println(about_entries);
        ArrayList<HashMap<String, ReturnedValue>>results = new ArrayList<HashMap<String, ReturnedValue>>();
        HashMap<String, String>types = new HashMap<String, String>();
        ArrayList<String>_keys = new ArrayList<String>();
        for (Document doc:about_entries){
            types.put(doc.getString("rowname"), doc.getString("rowtype"));
            _keys.add(doc.getString("rowname"));
        }
        for (Document doc:entries){
            HashMap<String, ReturnedValue>_r = new HashMap<String, ReturnedValue>();
            for (String key:_keys){
                if (types.get(key).equals("setInt")){
                    _r.put(key, new ReturnedValue(Integer.toString(doc.getInteger(key)), "real"));
                }
                else if (types.get(key).equals("setDouble")){
                    _r.put(key, new ReturnedValue(doc.getDouble(key).toString(), "real"));
                }
                else{
                    _r.put(key, new ReturnedValue(doc.getString(key), "text"));
                }
            }
            results.add(_r);
        }
        //System.out.println("test  below.....");
        //System.out.println(results);
        return results;

    }
    public void generic_update(String tablename, String filename, ArrayList<String>targetcols, ArrayList<DBValue>values, String anchor_col, DBValue anchor_val){
        if (filename.endsWith(".db")){
            filename = filename.substring(0, filename.length()-3);
        }
        MongoClient mongo = new MongoClient(new MongoClientURI(_url));
        MongoDatabase database = mongo.getDatabase(filename);
        MongoCollection<Document> collection = database.getCollection(tablename);
        for (int i = 0; i < targetcols.size(); i++){
            if (anchor_val.statement().equals("setInt")){
                if (values.get(i).statement().equals("setInt")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_int()), Updates.set(targetcols.get(i), values.get(i).to_int()));
                }
                else if (values.get(i).statement().equals("setDouble")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_int()), Updates.set(targetcols.get(i), values.get(i).to_double()));
                }
                else{
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_int()), Updates.set(targetcols.get(i), values.get(i).to_string()));
                }
            }
            else if (anchor_val.statement().equals("setDouble")){
                if (values.get(i).statement().equals("setInt")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_double()), Updates.set(targetcols.get(i), values.get(i).to_int()));
                }
                else if (values.get(i).statement().equals("setDouble")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_double()), Updates.set(targetcols.get(i), values.get(i).to_double()));
                }
                else{
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_double()), Updates.set(targetcols.get(i), values.get(i).to_string()));
                }
            }
            else{
                if (values.get(i).statement().equals("setInt")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_string()), Updates.set(targetcols.get(i), values.get(i).to_int()));
                }
                else if (values.get(i).statement().equals("setDouble")){
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_string()), Updates.set(targetcols.get(i), values.get(i).to_double()));
                }
                else{
                    collection.updateOne(Filters.eq(anchor_col, anchor_val.to_string()), Updates.set(targetcols.get(i), values.get(i).to_string()));
                }
            }


        }

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
    public static void main(String [] args) throws Exception{
        String [] arr = {"name", "position", "team", "salary"};
        MongoDriver driver = new MongoDriver("mongodb+srv://firstuser1:newTestCred@cs3733-hgmot.mongodb.net/test?retryWrites=true", arr);
        DBValue [] ar = {new DBValue<String>("Joe"), new DBValue<String>("CF"), new DBValue<String>("NY Yankees"), new DBValue<Integer>(500000)};
        driver.insert_vals("players", "players.db", ar);
        /*
        ArrayList<String>cls = new ArrayList<String>();
        cls.add("salary");
        ArrayList<DBValue>vls = new ArrayList<DBValue>();
        vls.add(new DBValue<Integer>(600000000));
        driver.generic_update("players", "players.db", cls, vls, "name", new DBValue<String>("Zack Greinke"));
           */

        //this is a test here
        for (HashMap<String, ReturnedValue>r:driver.select_all("players.db", "players")){
            System.out.println(r.get("name").to_string());
            System.out.println(r.get("team").to_string());
            System.out.println(r.get("salary").to_int());
            System.out.println("-----------------");

        }

        /*
        ArrayList<String>cols = new ArrayList<String>();
        cols.add("team");

        for (HashMap<String, ReturnedValue>r:driver.search_for_dl_multiple("players", "players.db", cols, "Boston", 4)){
            System.out.println("============================");
            System.out.println(r.get("name").to_string());
            System.out.println(r.get("team").to_string());
            System.out.println(r.get("salary").to_int());
            System.out.println("Found successfull result");
            System.out.println("-----------------");
            //34e374c8-66e2-4d1d-8d0b-ff3dde06c8f7
            //Brookdale10!
            //FareyaTestCluster1
        }
        */
        //username:firstuser1, password:newTestCred

    }
}
