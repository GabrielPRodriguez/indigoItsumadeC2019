package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SQLFuzzy implements IStrategyFuzzy {

    public void run(String searchString){
        try{
            for(String myparam: searchParameters){
                ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_sql_wildcard("form_data", "form_data.db", searchString, myparam);
                matches.addAll(mylist);
            }
            //remove duplicates for mathcing
            //TODO: remove the duplicates from here

        }catch(Exception e){
            System.out.println("Unable to query the results.");
        }
    }

    void removeDuplicates(){
        ArrayList<String> formIDs = new ArrayList<>();
        //Collections.sort(matches,  )
        for(HashMap<String,ReturnedValue> e:matches){
            //add the form id here
            //check if the form is being repeated, if it is, then remove the repeated form
        }
    }
/*
    public int compare(HashMap<int, ReturnedValue> one, HashMap<int, ReturnedValue> two){
        return one.get("serialNumber").compareTo(two.get("serialNumber"));
    }

    Collections.sort(l, new Comparator<HashMap<String, ReturnedValue>>(){
        public int compare(HashMap<String, ReturnedValue> one, HashMap<String, ReturnedValue> two) {
            return one.get("site_name").compareTo(two.get("site_name"));
        }
    });
*/

}
