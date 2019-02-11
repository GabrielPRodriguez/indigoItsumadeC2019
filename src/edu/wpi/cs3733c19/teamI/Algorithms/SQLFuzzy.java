package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;

import java.util.ArrayList;
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

}
