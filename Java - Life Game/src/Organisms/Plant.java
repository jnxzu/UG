package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public abstract class Plant extends Organism {
    public Plant(Plant plant, Position position) {
        super(plant, position);
    }

    public abstract void move(int bounds, ArrayList<Position> blocked, Position pref);

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        return null;
    }
}
