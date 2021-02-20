package Organisms;
import Gameplay.Position;

import java.util.ArrayList;

public class Grass extends Plant {
    public Grass(Grass grass, Position position){
        super(grass, position);
    }

    public void initParams(){
        this.setPower(3);
        this.setSign('G');
        this.setInitiative(0);
    }

    public void move(int bounds, ArrayList<Position> blocked, Position pref){}

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        return null;
    }
}
