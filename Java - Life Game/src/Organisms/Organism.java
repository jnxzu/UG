package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public abstract class Organism {
    private int power;
    private int initiative;
    private Position position;
    private char sign;

    public Organism(Organism organism, Position position) {
        if(organism!=null){
            this.setPower(organism.getPower());
            this.setPosition(organism.getPosition());
            this.setSign(organism.getSign());
            this.setInitiative(organism.getInitiative());
        }

        else{
            if(position!=null){
                this.setPosition(position);
            }

            this.initParams();
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public abstract void initParams();

    public abstract void move(int bounds, ArrayList<Position> blocked, Position pref);

    public abstract Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref);
}