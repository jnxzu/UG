package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public class Wolf extends Animal {
    public Wolf(Wolf wolf, Position position){
        super(wolf, position);
    }

    public void initParams(){
        this.setPower(96);
        this.setSign('W');
        this.setInitiative(5);
    }

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        if(x.getClass().getName().equals("Organisms.Sheep") && x.getPower()!=0){
            this.setPower(this.getPower()+x.getPower()/2);
            x.setPower(0);
        }
        if(x.getClass().getName().equals("Organisms.Wolf")){
            if(x.getPower()>48 && this.getPower()>48){
                this.setPower(this.getPower() - 24);
                x.setPower(x.getPower() - 24);
                Wolf wolf = new Wolf(null, this.getPosition());
                wolf.setPower(48);
                wolf.move(bounds, blocked, pref);
                wolf.move(bounds, blocked, pref);
                wolf.move(bounds, blocked, pref);
                return wolf;
            }
        }
        return null;
    }
}
