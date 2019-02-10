package edu.wpi.cs3733c19.teamI.Algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmTests {

    DLFuzzy dl;

//    AlTest(){
//
//    }

        @Test (expected = IllegalArgumentException.class)
        public void numbersNulSorce()throws IllegalArgumentException{
            dl.OSA_distance(null,"u");
        }

        @Test (expected = IllegalArgumentException.class)
        public void numbersNulTarget()throws IllegalArgumentException{
            dl.OSA_distance("v",null);
        }

        @Test
        public void DistanceYear1()throws IllegalArgumentException{
            assertEquals(0, dl.OSA_distance("1000","1000"));
        }

        @Test
        public void DistanceYear2()throws IllegalArgumentException{
            assertEquals(1, dl.OSA_distance("1000","10000"));
        }

        @Test
        public void DistanceYear3()throws IllegalArgumentException{
            assertEquals(2, dl.OSA_distance("10140","1000"));
        }

        @Test
        public void DistanceYear4()throws IllegalArgumentException{
            assertEquals(3, dl.OSA_distance("50140","1000"));
        }

        @Test
        public void DistanceYear5()throws IllegalArgumentException{
            assertEquals(2, dl.OSA_distance("111950","1950"));
        }

        @Test
        public void DistanceYear6()throws IllegalArgumentException{
            assertEquals(1, dl.OSA_distance("Ta man","Taman"));
        }

        @Test
        public void DistanceYear7()throws IllegalArgumentException{
            assertEquals(2, dl.OSA_distance("Ta men","Taman"));
        }

        @Test
        public void DistanceYear8()throws IllegalArgumentException{
            assertEquals(2, dl.OSA_distance("They","their"));
        }

        @Test
        public void DistanceYear9()throws IllegalArgumentException{
            assertEquals(2, dl.OSA_distance("They","Their"));
        }

    }
