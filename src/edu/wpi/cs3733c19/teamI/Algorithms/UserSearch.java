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
            //System.out.println("This is DL!");
            //System.out.println(searchString);
            removeDuplicates();
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
        void removeDuplicates(){
            ArrayList<Double> formIDs = new ArrayList<>();
            boolean killFlag = true;
            HashMap<String, ReturnedValue> e;
            for(int i = 0; i < matches.size(); i++){
                e = matches.get(i);
                Double form_id=e.get("RepIDnum").to_double();
                if(formIDs.size() == 0){ //need to add the first one manually
                    formIDs.add(form_id);
                }
                else {
                    for (Double x : formIDs) {
                        if (x.equals(form_id)) {
                            matches.remove(e);//this is actually where things are being removed
                            killFlag = true;
                            i--;
                            break;
                        } else {
                            killFlag = false;
                            //break;
                        }
                    }
                    if(killFlag == false){
                        formIDs.add(form_id);
                    }

                }
            }
        }

}
