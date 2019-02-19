package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;

public class DLFuzzy implements IStrategyFuzzy {

    public void run(String searchString) throws Exception{
        //"form_data", "stringified_ids_db.db"
        //this function (search_for_dl_multiple) can be found in the SQLDriver
        ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_for_dl_multiple("form_data", "stringified_ids_db.db",searchParameters,searchString, 10);
        matches.addAll(mylist);
        //System.out.println(mylist.get(100).get("brandName").to_string());
        System.out.println("This is DL!");
        System.out.println(searchString);
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



    /**
     * algorithm OSA-distance
     * @param source the user given string
     * @param target string provided by Data base search.
     * @return int the diffrecne between source and target
     * @throws IllegalArgumentException If either source or target is null.
     */

}




