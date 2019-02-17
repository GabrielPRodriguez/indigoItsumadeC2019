package edu.wpi.cs3733c19.teamI.Entities;

public interface Observable {

    void register(Observer o);

    void notifyObservers();
}