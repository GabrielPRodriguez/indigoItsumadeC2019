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

    // Step 5: Create the context class methods to set the strategy and call the interface methods
    public void setContext(IStrategyFuzzy myfuzzy) {
        this.fuzzyAlgorithm = myfuzzy;
    }


    public ArrayList<HashMap<String, ReturnedValue>> run(String searchString) {
        fuzzyAlgorithm.run(searchString);
        return fuzzyAlgorithm.matches;
    }



}
