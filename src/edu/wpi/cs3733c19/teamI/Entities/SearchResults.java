package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.SQLDriver;
import edu.wpi.cs3733c19.teamI.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//TODO re-evaluate the different varaiable properties (protected, private, whatever im goin to bed)


/* This class serves as the main interface between the data base and the search component of this application
* In main, a "global" instance is created that is referenced by the search input and display. The class contains both
* methods for extracting data as well as attributes for storing search parameters and results*/
public class SearchResults {

    private LinkedList<String> Parameters; //list of strings that define WHAT parameters were used for search
    private HashMap<String, DataField> searchMap; //hash map that links chosen parameters to their chosen values
    //Results are stored in a list of hashmaps. One hashmap represents a form. In order to get field data,
    //use listOfForms.get("name of field"); where name of field is the name of attribute in form class
    private ArrayList<HashMap<String, ReturnedValue>> listOfForms; //holds the results of a query.

    //function used to update parameters that we are searching by, it then calls the search function
    //input is list of DataField, which is a class that contains the string name of the parameter and the value of
    //the parameter itself.

    /*TODO DataField was originally created due to speific needs of DB, these needs may have changed, and this class may be able to be be eliminated */
    public void gatherSearchParam(LinkedList<DataField> userInput) throws Exception
    {
        //clear old search param
        this.Parameters = new LinkedList<>();
        this.searchMap = new HashMap<>();

        //iterate through list, update parameters and thier values
        for (DataField dataField : userInput) {
            Parameters.add(dataField.getField());
            searchMap.put(dataField.getField(), dataField);
        }
        //update the list of search results
        UpdateList();
    }

    //function to find specific values of search parameters
    public String getSearchParamEntry(String key)
    {
        return(this.searchMap.get(key).getStringVal());
    }





  //setters
    public void setParameters(LinkedList<String> param)
    {
        this.Parameters = param;
    }

    public void setSearchMap(HashMap<String, DataField> map)
    {
        this.searchMap = map;
    }

    public void setListOfForms(ArrayList<HashMap<String, ReturnedValue>> listOfForms) {
        this.listOfForms = listOfForms;
    }

    //getters
    public LinkedList<String> getParameters()
    {
        return(this.Parameters);
    }

    public HashMap<String, DataField> getSearchMap()
    {
        return(this.searchMap);
    }

    public ArrayList<HashMap<String, ReturnedValue>> getListOfForms() {
        return listOfForms;
    }

//    protected void FilterEntries(String field) //TODO filter by Double/ int, or string
//    {

    //call DB function to query. Be sure to update search parameters first
    protected void UpdateList() throws Exception
    {
        //get_data_by_value()
        System.out.println("in UpdateList");
        SQLDriver driver = new SQLDriver();
        ArrayList<HashMap<String, ReturnedValue>>results = driver.get_data_by_value("form_data", "stringified_ids_db.db", this.Parameters, this.searchMap);
        this.listOfForms = results;
        //System.out.println(results);
        //System.out.println("Error, is that you?");
    }

    //Constructor/////////////////////////////////////////////////

    public SearchResults() {
        this.listOfForms = new ArrayList<>();
        this.Parameters = new LinkedList<>();
        this.searchMap = new HashMap<>();
    }

}
