package Organisms;

import Gameplay.Position;

import java.util.ArrayList;

public class Lion extends Animal {
    public Lion(Lion lion, Position position){
        super(lion, position);
    }

    public void initParams(){
        this.setPower(120);
        this.setSign('L');
        this.setInitiative(10);
    }

    public Organism interact(Organism x, int bounds, ArrayList<Position> blocked, Position pref){
        if(x.getClass().getSuperclass().getName().equals("Organisms.Animal") && x.getPower()!=0 && x.getSign()!='L'){
            this.setPower(this.getPower()+x.getPower()/2);
            x.setPower(0);
        }
        if(x.getClass().getName().equals("Organisms.Lion")){
            if(x.getPower()>60 && this.getPower()>60){
                this.setPower(this.getPower() - 30);
                x.setPower(x.getPower() - 30);
                Lion lion = new Lion(null, this.getPosition());
                lion.setPower(60);
                lion.move(bounds, blocked, pref);
                lion.move(bounds, blocked, pref);
                lion.move(bounds, blocked, pref);
                return lion;
            }
        }
        return null;
    }
}
