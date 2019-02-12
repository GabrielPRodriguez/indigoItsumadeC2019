package edu.wpi.cs3733c19.teamI.Algorithms;

import edu.wpi.cs3733c19.teamI.Controllers2.dbUtilities.ReturnedValue;

import java.util.ArrayList;
import java.util.HashMap;

public class DLFuzzy implements IStrategyFuzzy {

    public void run(String searchString) {
        try{
            ArrayList<HashMap<String, ReturnedValue>> mylist=querydata.search_for_dl_multiple("form_data", "form_data.db",searchParameters,searchString, 10);
        }catch (Exception e){
            System.out.println("Unsuccessful query.");
        }
    }



    /**
     * algorithm OSA-distance
     * @param source the user given string
     * @param target string provided by Data base search.
     * @return int the diffrecne between source and target
     * @throws IllegalArgumentException If either source or target is null.
     */

    //the code below was moved to the SQL in order to be consistent with the other 2 algorithms
//    public static int OSA_distance(String source, String target) {
//
//        // throw if parameter is a null
//        if (source == null || target == null) {
//            throw new IllegalArgumel_ntException("Parameter must not be null");
//        }
//
//        int sourceLength = source.length();
//        int targetLength = target.length();
//
//        //end with a
//        if (sourceLength == 0) return  sourceLength;
//        if (targetLength == 0) return  targetLength;
//
//        int[][] dist = new int[sourceLength+1][targetLength+1];
//
//        for (int i =0; i < sourceLength +1; i++){
//            dist[i][0] =i;
//        }
//
//        for (int j =0; j < targetLength +1; j++){
//            dist[0][j] =j;
//        }
//
//        for (int i = 1; i < sourceLength +1; i++){
//            for (int j = 1; j < targetLength +1; j++){
//                int cost = source.charAt(i-1) == target.charAt(j-1) ? 0:1;
//                dist[i][j] = Math.min(
//                        Math.min(dist[i-1][j] + 1, dist[i][j-1]+1), dist[i-1][j-1]+cost);
//
//                if (i > 1 && j >1 && source.charAt(i-1) == target.charAt(j-2) && source.charAt(i-2) == target.charAt(j-1)){
//                    dist[i][j] = Math.min(dist[i][j],dist[i-2][j-2] + cost);
//                }
//            }
//        }
//        return dist[sourceLength][targetLength];
//    }

}




