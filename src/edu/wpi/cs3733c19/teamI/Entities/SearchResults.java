package edu.wpi.cs3733c19.teamI.Entities;

import java.util.ArrayList;

public class SearchResults {
    private ArrayList<String> searchParams;
    //private ArrayList<Form> listOfForms; //TODO get form class

    //setters
    protected void setSearchParams(ArrayList<String> newParams) //TODO controller compiles list from search boundry
    {
        searchParams = newParams;
    }

    //getters
    protected ArrayList<String> getSearchParams()
    {
        return(this.searchParams);
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

}
