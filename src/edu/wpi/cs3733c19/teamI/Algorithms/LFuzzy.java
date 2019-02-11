package edu.wpi.cs3733c19.teamI.Algorithms;
import edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;


public class LFuzzy implements IStrategyFuzzy {
    public void run(String searchString){

        //Todo: This function returns a hashmap, and the other a complete Array List (i
        try{
            this.matches.add(querydata.search_for_l("form_data.db", "form_data.db", searchString,"fanciful_name"));
        }catch (Exception e){
            System.out.println("Unsuccessful query.");
        }
    }
    // public HashMap<String, ReturnedValue>search_for_l(String tablename, String filename, String target, String _key)
    //name of the table, original form, filename=> form_data.db, target is the string that you garnered form anyone of the form input field,
    //column name( what string to specifically look for)- an entire form

}
