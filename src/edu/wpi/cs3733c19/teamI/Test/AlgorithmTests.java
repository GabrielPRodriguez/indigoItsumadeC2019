package edu.wpi.cs3733c19.teamI.Test;

//import edu.wpi.cs3733c19.teamI.Algorithms.DLFuzzy;
//import edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;
import  edu.wpi.cs3733c19.teamI.Controllers.SQLDriver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmTests {

    SQLDriver drive;

//    AlTest(){
//
//    }

    @Test (expected = IllegalArgumentException.class)
    public void numbersNulSorce()throws IllegalArgumentException{
        drive.dl_distance (null,"u");
    }

    @Test (expected = IllegalArgumentException.class)
    public void numbersNulTarget()throws IllegalArgumentException{
        drive.dl_distance("v",null);
    }

    @Test
    public void DistanceYear1()throws IllegalArgumentException{
        assertEquals(0, drive.dl_distance("1000","1000"));
    }

    @Test
    public void DistanceYear2()throws IllegalArgumentException{
        assertEquals(1, drive.dl_distance("1000","10000"));
    }

    @Test
    public void DistanceYear3()throws IllegalArgumentException{
        assertEquals(2, drive.dl_distance("10140","1000"));
    }

    @Test
    public void DistanceYear4()throws IllegalArgumentException{
        assertEquals(3, drive.dl_distance("50140","1000"));
    }

    @Test
    public void DistanceYear5()throws IllegalArgumentException{
        assertEquals(2, drive.dl_distance("111950","1950"));
    }

    @Test
    public void DistanceYear6()throws IllegalArgumentException{
        assertEquals(1, drive.dl_distance("Ta man","Taman"));
    }

    @Test
    public void DistanceYear7()throws IllegalArgumentException{
        assertEquals(2, drive.dl_distance("Ta men","Taman"));
    }

    @Test
    public void DistanceYear8()throws IllegalArgumentException{
        assertEquals(2, drive.dl_distance("They","their"));
    }

//        @Test
//        public void LDtstanceError()throws IllegalArgumentException{
//            assertEquals(2, drive.l_distance("They","their"));
//        }

    @Test
    public void lDistance()throws IllegalArgumentException{
        assertEquals(2, drive.l_distance("They","their"));
    }

    @Test
    public void lDistance1()throws IllegalArgumentException{
        assertEquals(1, drive.l_distance("2001","3001"));
    }

}
