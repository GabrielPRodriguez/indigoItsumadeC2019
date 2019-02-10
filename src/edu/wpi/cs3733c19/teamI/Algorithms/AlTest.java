package edu.wpi.cs3733c19.teamI.Algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class AlTest {

    DLFuzzy dl;

    AlTest(){

    }

    @Test (expected = IllegalArgumentException.class)
    public void numbersNulSorce()throws IllegalArgumentException{
        dl.OSA_distance(null,"u");
    }


}
