package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public class Sheep extends Animal {

    public Sheep(Sheep sheep, Position position){
        super(sheep, position);
    }

    public void initParams(){
        this.setPower(120);
        this.setSign('S');
        this.setInitiative(3);
    }

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        if(x.getClass().getSuperclass().getName().equals("Organisms.Plant") && x.getPower()!=0){
            this.setPower(this.getPower()+x.getPower());
            x.setPower(0);
        }
        if(x.getClass().getName().equals("Organisms.Sheep")){
            if(x.getPower()>60 && this.getPower()>60){
                this.setPower(this.getPower() - 30);
                x.setPower(x.getPower() - 30);
                Sheep sheep = new Sheep(null, this.getPosition());
                sheep.setPower(60);
                sheep.move(bounds, blocked, pref);
                sheep.move(bounds, blocked, pref);
                sheep.move(bounds, blocked, pref);
                return sheep;
            }
        }
        return null;
    }
}
