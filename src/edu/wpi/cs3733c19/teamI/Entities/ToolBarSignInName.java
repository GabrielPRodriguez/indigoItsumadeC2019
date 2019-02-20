package edu.wpi.cs3733c19.teamI.Entities;

public class ToolBarSignInName implements Observer {

    String signInName = new String();

    public void setText(String text){

        String newString = text;

        this.signInName = newString;
    }

    public String getText(){
        return this.signInName;
    }


    @Override
    public void notify(Object object) {
        if (object instanceof Message){
            Message msg = (Message) object;
            setText(msg.getText());
        }
    }
}
