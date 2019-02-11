package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;


public class LFuzzy implements IStrategyFuzzy {
    public void run(String searchString){

        //Todo: The second to last parameter is impending a change
        try{
            ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_for_l("form_data.db", "form_data.db", searchString, "field", 10);
        }catch (Exception e){
            System.out.println("Unsuccessful query.");
        }
    }
}
