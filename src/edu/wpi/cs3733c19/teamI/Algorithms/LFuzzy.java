package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;


public class LFuzzy implements IStrategyFuzzy {

    public void run(String searchString) throws Exception{
        //Todo: The second to last parameter is impending a change
        //this function (search_for_dl_multiple) can be found in the SQLDriver
        ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_for_l_multiple("form_data", "stringified_ids_db.db",searchParameters, searchString, 10);
        matches.addAll(mylist);
        System.out.println(searchString);
        //System.out.println(mylist.get(0).get("beverageType").to_string());
        System.out.println("here 1");


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


}
