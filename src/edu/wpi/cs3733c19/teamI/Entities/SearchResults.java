package edu.wpi.cs3733c19.teamI.Entities;

import edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;
import edu.wpi.cs3733c19.teamI.Controllers.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashMap;

//TODO re-evaluate the different varaiable properties (protected, private, whatever im goin to bed)
public class SearchResults {

    private LinkedList<String> Parameters;
    private HashMap<String, DataField> searchMap;
    //Results are stored in a list of hashmaps. One hashmap represemts a form. In order to get field data,
    //use HASHMAP.get("name of field"); where name of field is the name of attribute in form class
    private ArrayList<HashMap<String, ReturnedValue>> listOfForms; //holds the results of a query.


    public void gatherSearchParam(LinkedList<DataField> userInput) throws Exception
    {
        //clear old search param
        this.Parameters = new LinkedList<>();
        this.searchMap = new HashMap<>();
        for (DataField dataField : userInput) {
            Parameters.add(dataField.getField());
            searchMap.put(dataField.getField(), dataField);
        }
        System.out.println("before UpdateList call");
        System.out.println(searchMap);
        UpdateList();
    }

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

    protected void FilterEntries(String field) //TODO filter by Double/ int, or string
    {

    }

    protected void UpdateList() throws Exception// call DB function to query new forms
    {
        //get_data_by_value()
        System.out.println("in UpdateList");
        SQLDriver driver = new SQLDriver();
        ArrayList<HashMap<String, ReturnedValue>>results = driver.get_data_by_value("form_data", "form_data.db", this.Parameters, this.searchMap);
        this.listOfForms = results;
        System.out.println(results);
        System.out.println("Error, is that you?");
    }

    protected void MakeCSV()    //TODO how make csv? what is return
    {

    }


    //Constructor/////////////////////////////////////////////////

    public SearchResults() {
        this.listOfForms = new ArrayList<>();
        this.Parameters = new LinkedList<>();
        this.searchMap = new HashMap<>();
    }

}
