package edu.wpi.cs3733c19.teamI.Entities;

import java.util.LinkedList;

public class Message implements Observable {

    LinkedList<Observer> observers = new LinkedList<>();
    String messege = new String();

    @Override
    public void register(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers){
            o.notify(this);
        }
    }

    public void setText(String text){
        this.messege = text;
        notifyObservers();
    }

    public String getText(){
        return this.messege;
    }
}
