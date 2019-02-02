package edu.wpi.cs3733c19.teamI.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashMap;

public class SearchResults {

    private LinkedList<String> Parameters;
    private HashMap<String, DataField> searchMap;
    private ArrayList<Form> listOfForms;

    void gatherSearchParam(LinkedList<DataField> userInput)
    {
        for (DataField dataField : userInput) {
            Parameters.add(dataField.getField());
            searchMap.put(dataField.getField(), dataField);
        }
    }





  //setters
    protected void setParameters(LinkedList<String> param)
    {
        this.Parameters = param;
    }

    protected void setSearchMap(HashMap<String, DataField> map)
    {
        this.searchMap = map;
    }

    protected void setlistOfForm(ArrayList<Form> forms)
    {
        this.listOfForms = forms;
    }



    //getters
    protected LinkedList<String> getParameters()
    {
        return(this.Parameters);
    }

    protected HashMap<String, DataField> getSearchMap()
    {
        return(this.searchMap);
    }

    protected ArrayList<Form> getListOfForms()
    {
        return(this.listOfForms);
    }




    protected void FilterEntries(String field) //TODO filter by Double/ int, or string
    {

    }

    protected void UpdateList()// call DB function to query new forms
    {

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
