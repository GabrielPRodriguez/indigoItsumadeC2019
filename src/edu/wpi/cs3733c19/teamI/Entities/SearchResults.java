package edu.wpi.cs3733c19.teamI.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.HashMap;

//TODO re-evaluate the different varaiable properties (protected, private, whatever im goin to bed)
public class SearchResults {

    private LinkedList<String> Parameters;
    private HashMap<String, DataField> searchMap;
    private ArrayList<HashMap<String, DataField>> listOfForms;

    public void gatherSearchParam(LinkedList<DataField> userInput)
    {
        //clear old search param
        this.Parameters = new LinkedList<>();
        this.searchMap = new HashMap<>();
        for (DataField dataField : userInput) {
            Parameters.add(dataField.getField());
            searchMap.put(dataField.getField(), dataField);
        }
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

//    public void setlistOfForm(ArrayList<Form> forms)
//    {
//        this.listOfForms = forms;
//    }



    //getters
    public LinkedList<String> getParameters()
    {
        return(this.Parameters);
    }

    public HashMap<String, DataField> getSearchMap()
    {
        return(this.searchMap);
    }

//    public ArrayList<Form> getListOfForms()
//    {
//        return(this.listOfForms);
//    }




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
