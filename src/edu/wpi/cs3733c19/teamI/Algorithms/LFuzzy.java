package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;


public class LFuzzy implements IStrategyFuzzy {

    public void run(String searchString) throws Exception{

        //Todo: The second to last parameter is impending a change

            ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_for_l_multiple("form_db", "form_db_from_spreadsheet.db",searchParameters, searchString, 10);
            matches.addAll(mylist);

    }


}
