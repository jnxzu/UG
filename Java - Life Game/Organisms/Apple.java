package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public class Apple extends Plant {
    public Apple(Apple apple, Position position){
        super(apple, position);
    }

    public void initParams(){
        this.setPower(12);
        this.setSign('A');
        this.setInitiative(0);
    }

    public void move(int bounds, ArrayList<Position> blocked, Position pref){}

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        return null;
    }
}
