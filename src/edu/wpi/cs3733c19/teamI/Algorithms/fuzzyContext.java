package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;

public class fuzzyContext {
    private IStrategyFuzzy fuzzyAlgorithm;

    public fuzzyContext() {}
    public fuzzyContext(IStrategyFuzzy myfuzzy) {
        this.fuzzyAlgorithm = myfuzzy;
    }

    //the setContext method allows you to  choose a given algorithm
    public void setContext(IStrategyFuzzy myfuzzy) {
        this.fuzzyAlgorithm = myfuzzy;
    }

    //this allows users to  get a list of forms back
    public ArrayList<HashMap<String, ReturnedValue>> run(String searchString) throws Exception {
        fuzzyAlgorithm.matches.clear();
        fuzzyAlgorithm.run(searchString);
        return fuzzyAlgorithm.matches;
    }

}
