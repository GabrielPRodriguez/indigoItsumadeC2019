package edu.wpi.cs3733c19.teamI.Algorithms;

public class fuzzyContext implements IStrategyFuzzy {
    private IStrategyFuzzy fuzzyAlgorithm;

    public fuzzyContext() {}
    public fuzzyContext(IStrategyFuzzy myfuzzy) {
        this.fuzzyAlgorithm = myfuzzy;
    }

    // Step 5: Create the context class methods to set the strategy and call the interface methods
    public void setContext(IStrategyFuzzy myfuzzy) {
        this.fuzzyAlgorithm = myfuzzy;
    }

    public double run(String searchString) {
        return fuzzyAlgorithm.run(searchString);
    }
}
