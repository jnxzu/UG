package Organisms;

import Gameplay.Position;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Organism {

    public Animal(Animal animal, Position position) {
        super(animal, position);
    }


    public void move(int bounds, ArrayList<Position> blocked, Position pref){
        Position poz = new Position(this.getPosition().getX(),this.getPosition().getY());
        Position current = poz;

        int x = poz.getX();
        int y = poz.getY();

        if(x==0){
            x += ThreadLocalRandom.current().nextInt(0,2);
        }
        else if(x==bounds-1){
            x += ThreadLocalRandom.current().nextInt(-1,1);
        }
        else{
            x += ThreadLocalRandom.current().nextInt(-1,2);
        }

        if(y==0){
            y += ThreadLocalRandom.current().nextInt(0, 2);
        }
        else if(y==bounds-1){
            y += ThreadLocalRandom.current().nextInt(-1, 1);
        }
        else {
            y += ThreadLocalRandom.current().nextInt(-1, 2);
        }

        poz.setX(x);
        poz.setY(y);

        if(pref!=null){
            poz = pref;
        }
        for(int i=0;i<blocked.size();i++){
            if(poz.equals(blocked.get(i))){
                poz = current;
                break;
            }
        }
        this.setPosition(poz);
    }

    public abstract Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref);

}
