package edu.wpi.cs3733c19.teamI.Controllers2;

import edu.wpi.cs3733c19.teamI.Algorithms.LFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.SQLFuzzy;
import edu.wpi.cs3733c19.teamI.Algorithms.fuzzyContext;
import javafx.event.ActionEvent;

public class AboutController{

    private ToolBarController toolBarController = ToolBarController.getInstance();
    private fuzzyContext searchAlgorithmSelection = new fuzzyContext();

    public void goWine(ActionEvent event) throws Exception {
        searchAlgorithmSelection.setContext(new LFuzzy());
        toolBarController.setResultsMap(searchAlgorithmSelection.run("Franzia"));
        toolBarController.goSearch();
    }
}
