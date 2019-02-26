package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;
import edu.wpi.cs3733c19.teamI.Controllers2.SQLDriver;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSearch implements IStrategyFuzzy {


        public void run(String searchString) throws Exception{
            //"form_data", "stringified_ids_db.db"
            //this function (search_for_dl_multiple) can be found in the SQLDriver
            setSearchParam(new ArrayList<String>());
            ArrayList<HashMap<String, ReturnedValue>> mylist=querydataUser.search_for_l_user("credentials", "user_database.db",searchParameters,searchString, 10);
            matches.addAll(mylist);
            //System.out.println(mylist.get(100).get("brandName").to_string());
            System.out.println("This is DL!");
            System.out.println(searchString);
        }

        public void setSearchParam(ArrayList<String> searchParam){
            //if(searchParam.isEmpty()){
            searchParam.add("RepIDnum");
            searchParam.add("firstName");
            searchParam.add("lastName");
            searchParam.add("email");
            //}
            searchParameters.clear();
            for(String param: searchParam) {
                //System.out.println(param);
                searchParameters.add(param);
            }
        }

}
