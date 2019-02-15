package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.*;

public class SQLFuzzy implements IStrategyFuzzy {

    public void run(String searchString){
        try{
            //querying for each form parameter
            System.out.println("here 3");
            for(String myparam: searchParameters){
                //this function (search_for_sql_wildcard) can be found in the SQLDriver
                ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_sql_wildcard("form_data", "new_csv_from_spreadsheet.db", searchString, myparam);
                matches.addAll(mylist);
            }
            removeDuplicates();

        }catch(Exception e){
            System.out.println("Unable to query the results.");
        }
    }

    public void setSearchParam(ArrayList<String> searchParam){
        if(searchParam.isEmpty()){
            searchParam.add("fancifulName");
            searchParam.add("beverageType");
            searchParam.add("extraInfo");
        }
        searchParameters.clear();
        for(String param: searchParam) {
            System.out.println(param);
            searchParameters.add(param);
        }
    }
    //function to remove duplicate search results (from the multiple sql queries)
    void removeDuplicates(){
        ArrayList<Double> formIDs = new ArrayList<>();
        for(HashMap<String,ReturnedValue> e:matches){
            Double form_id=e.get("formID").to_double();
            for(Double x:formIDs){
                if(x==form_id){
                    matches.remove(e);//this is actually where things are being removed
                }else{
                    formIDs.add(form_id);
                }
            }
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
