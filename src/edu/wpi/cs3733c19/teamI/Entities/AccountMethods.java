package edu.wpi.cs3733c19.teamI.Entities;

import java.util.regex.*;

public class AccountMethods {

    public AccountMethods() {
    }

    public int passwordStrength(String currPass){
        int strength = 0;
        int strengthBarValue = 0;

        if(currPass.matches("[a-zA-Z0-9][a-zA-Z0-9]+")){
            strength ++;
        }
        if(currPass.matches("[~<>?]+")){
            strength++;
        }
        if(currPass.matches("[!@#$%^&*()]+")){
            strength++;
        }
        switch (strength){
            case 0:
                strengthBarValue = 20;
                break;
            case 1:
                strengthBarValue = 40;
                break;
            case 2:
                strengthBarValue = 60;
                break;
            case 3:
                strengthBarValue = 80;
                break;
            case 4:
                strengthBarValue = 100;
                break;


        }
        return strengthBarValue;
    }
}
