package edu.wpi.cs3733c19.teamI.Algorithms;

public class DLFuzzy implements IStrategyFuzzy {
    public void run(String searchString) {
    }

    /**
     * algorithm OSA-distance
     * @param source the user given string
     * @param target string provided by Data base search.
     * @return int the diffrecne between source and target
     * @throws IllegalArgumentException If either source or target is null.
     */
    public int OSA_distance(String source, String target) {


        // throw if parameter is a null
        if (source == null || target == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }


        // original string Lengths
        int sourceLength = source.length();
        int targetLength = target.length();

        //end with a
        if (sourceLength == 0) return  sourceLength;
        if (targetLength == 0) return  targetLength;

        int[][] dist = new int[sourceLength+1][targetLength+1];

//        input: strings a[1..length(a)],b[1..length(b)]
//        output:distance,integer

        //let d[0..length(a), 0..length(b)] be a 2-d array of integers, dimensions length(a)+1, length(b)+1
        // note that d is zero-indexed, while a and b are one-indexed.

//        for (i :=0 to length(a) inclusive do
//            d[i,0]:=i

        for (int i =0; i < sourceLength +1; i++){
            dist[i][0] =i;
        }

//        for j :=0 to length(b) inclusive do
//            d[0,j]:=j
        for (int j =0; j < targetLength +1; j++){
            dist[0][j] =j;
        }


        // for i :=1 to length(a) inclusive do
        //      for j :=1 to length(b) inclusive do

        for (int i = 1; i < sourceLength +1; i++){
            for (int j = 1; j < targetLength +1; j++){
                int cost = source.charAt(i-1) == target.charAt(j-1) ? 0:1;
                dist[i][j] = Math.min(
                        Math.min(dist[i-1][j] + 1, dist[i][j-1]+1), dist[i-1][j-1]+cost);

                if (i > 1 && j >1 && source.charAt(i-1) == target.charAt(j-2) && source.charAt(i-2) == target.charAt(j-1)){
                    dist[i][j] = Math.min(dist[i][j],dist[i-2][j-2] + cost);
                }
            }
        }


        //  return d[length(a),length(b)]

        return dist[sourceLength][targetLength];
    }


    private int yearDistance(String source){
        int yearIn1000s = OSA_distance(source, "1000");

        int yearIn1500s = OSA_distance(source, "1500");

        int yearIn1600s = OSA_distance(source, "1600");

        int yearIn1700s = OSA_distance(source, "1700");

        int yearIn1800s = OSA_distance(source, "1800");

        int yearIn1900s = OSA_distance(source, "1900");

        return 0;
    }
}




