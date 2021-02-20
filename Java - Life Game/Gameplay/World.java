package Gameplay;

import GFX.*;
import Organisms.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class World {
    // rozmiar jednego pola
    private static int screen = 15;

    private int size;
    private ArrayList<Organism> organisms;

    private Draw display;
    private Graphics g;
    private BufferStrategy buff;
    private SpriteS spr;

    public static void main(String args[]) {
        play(50);
    }

    // funkcja odpowiadajaca za rozgrywke
    private static void play(int s) {
        World world = init(s);
        world.show();
        while (true) {
            world.sortInitiative();
            world.spawnPlants();
            world.action();
            world.rmDead();
            world.show();
        }
    }

    // losowanie pozycji
    public static Position randPos(int s) {
        int x, y;
        x = ThreadLocalRandom.current().nextInt(0, s + 1);
        y = ThreadLocalRandom.current().nextInt(0, s + 1);
        Position poz = new Position(x, y);
        return poz;
    }

    // wstepna generacja swiata
    private static World init(int size) {
        World world = new World(size);
        // inicjalizacja wyswietlania

        world.setSpr(new SpriteS(new imgLoader().loadimg("/res/img/sprity.png")));
        world.setDisplay(new Draw("World", world.getSize() * screen, world.getSize() * screen));
        Position poz = randPos(world.getSize());
        world.getOrganisms().add(new Lion(null, poz));
        for (int i = 0; i < ((size * size) / 20) - 2; i++) {
            // wybor pozycji
            poz = randPos(world.getSize());
            // losowanie organizmow
            int rand = ThreadLocalRandom.current().nextInt(0, 5);
            switch (rand) {
            // szansa na zwierze 40%
            case 0:
            case 1:
                // losowanie gatunku
                int animrand = ThreadLocalRandom.current().nextInt(0, 10);
                switch (animrand) {
                // szansa na wilka 20%
                case 0:
                case 1:
                    world.getOrganisms().add(new Wolf(null, poz));
                    break;
                // szanse na owce 80%
                default:
                    world.getOrganisms().add(new Sheep(null, poz));
                    break;

                }
                // szansa na rosline 60%
            default:
                // losowanie rodzaju rosliny
                int plantrand = ThreadLocalRandom.current().nextInt(0, 5);
                switch (plantrand) {
                // szanse na jablko 20%
                case 0:
                    world.getOrganisms().add(new Apple(null, poz));
                    break;
                // szanse na trawe 80%
                default:
                    world.getOrganisms().add(new Grass(null, poz));
                    break;
                }
            }
        }
        poz = randPos(world.getSize());
        world.getOrganisms().add(new Lion(null, poz));

        return world;
    }

    // wyswietlanie
    private void show() {
        // tworzenie buforow
        if (this.getBuff() == null) {
            this.getDisplay().getCanvas().createBufferStrategy(2);
        }
        this.setBuff(this.getDisplay().getCanvas().getBufferStrategy());
        this.setG(this.getBuff().getDrawGraphics());

        // rysowanie pol
        for (int i = 0; i < this.getSize() * screen; i = i + screen) {
            for (int j = 0; j < this.getSize() * screen; j = j + screen) {
                this.getG().drawImage(this.getSpr().crop(0, 0, 15, 15), j, i, null);
                Collections.sort(this.getOrganisms(), new SortByInitiativeAlt());
                for (int k = 0; k < this.getOrganisms().size(); k++) {
                    if (this.getOrganisms().get(k) != null) {
                        if (this.getOrganisms().get(k).getPosition().equals(new Position(j / screen, i / screen))) {
                            switch (this.getOrganisms().get(k).getSign()) {
                            case 'G':
                                this.getG().drawImage(this.getSpr().crop(15, 0, 15, 15), j, i, null);
                                break;

                            case 'A':
                                this.getG().drawImage(this.getSpr().crop(0, 15, 15, 15), j, i, null);
                                break;

                            case 'S':
                                this.getG().drawImage(this.getSpr().crop(15, 15, 15, 15), j, i, null);
                                break;

                            case 'W':
                                this.getG().drawImage(this.getSpr().crop(0, 30, 15, 15), j, i, null);
                                break;

                            case 'L':
                                this.getG().drawImage(this.getSpr().crop(15, 30, 15, 15), j, i, null);
                                break;
                            }
                        }
                    }
                }
                Collections.sort(this.getOrganisms(), new SortByInitiative());
            }
        }

        this.getBuff().show();
        this.getG().dispose();

        try {
            TimeUnit.MILLISECONDS.sleep(666);
        } catch (Exception e) {
        }
    }

    // sortuje wdg inicjatywy(malejaco)
    private void sortInitiative() {
        Collections.sort(this.getOrganisms(), new SortByInitiative());
    }

    // zwraca tablice pol otaczajacych organizm
    private static Position[] surround(Organism o) {
        Position[] surroundings = new Position[8];

        for (int i = 0; i < 8; i++) {
            surroundings[i] = o.getPosition();
        }
        surroundings[0] = new Position(surroundings[0].getX() - 1, surroundings[0].getY() - 1);
        surroundings[1] = new Position(surroundings[1].getX(), surroundings[1].getY() - 1);
        surroundings[2] = new Position(surroundings[2].getX() + 1, surroundings[2].getY() - 1);
        surroundings[3] = new Position(surroundings[3].getX() + 1, surroundings[3].getY());
        surroundings[4] = new Position(surroundings[4].getX() + 1, surroundings[4].getY() + 1);
        surroundings[5] = new Position(surroundings[5].getX(), surroundings[5].getY() + 1);
        surroundings[6] = new Position(surroundings[6].getX() - 1, surroundings[6].getY() + 1);
        surroundings[7] = new Position(surroundings[7].getX() - 1, surroundings[7].getY());

        return surroundings;
    }

    // sprawdza zablokowane pola
    private ArrayList<Position> getBlock(Organism o) {
        Position[] surroundings = surround(o);
        ArrayList<Position> blocked = new ArrayList<>();

        if (o.getSign() == 'S') {
            for (int i = 0; i < 8; i++) {
                boolean crash = false;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getSign() == 'L'
                            && this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getSign() == 'W'
                            && this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
                int counter = 0;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        counter++;
                    }
                    if (counter == 2) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
            }
        }
        if (o.getSign() == 'W') {
            for (int i = 0; i < 8; i++) {
                boolean crash = false;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getSign() == 'W'
                            && this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getSign() == 'L'
                            && this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
                int counter = 0;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        counter++;
                    }
                    if (counter == 2) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
            }
        }
        if (o.getSign() == 'L') {
            for (int i = 0; i < 8; i++) {
                boolean crash = false;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getSign() == 'L'
                            && this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
                int counter = 0;
                for (int j = 0; j < this.getOrganisms().size(); j++) {
                    if (this.getOrganisms().get(j).getPosition().equals(surroundings[i])) {
                        counter++;
                    }
                    if (counter == 2) {
                        crash = true;
                        break;
                    }
                }
                if (crash) {
                    blocked.add(surroundings[i]);
                    continue;
                }
            }
        }
        return blocked;
    }

    // znajdowanie 'jedzenia' w otoczeniu
    private Position hungrMove(Organism o) {
        Position pref = null;
        Position[] surroundings = surround(o);

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < this.getOrganisms().size(); i++) {
                if (this.getOrganisms().get(i).getPosition().equals(surroundings[j])
                        && this.getOrganisms().get(i).getPower() > 0) {
                    if (o.getSign() == 'S') {
                        if (this.getOrganisms().get(i).getClass().getSuperclass().getName().equals("Organisms.Plant")) {
                            pref = surroundings[j];
                        }
                    }
                    if (o.getSign() == 'W') {
                        if (this.getOrganisms().get(i).getSign() == 'S') {
                            pref = surroundings[j];
                        }
                    }
                    if (o.getSign() == 'L') {
                        if (this.getOrganisms().get(i).getClass().getSuperclass().getName().equals("Organisms.Animal")
                                && this.getOrganisms().get(i).getSign() != 'L') {
                            pref = surroundings[j];
                        }
                    }
                }
            }
        }
        return pref;
    }

    // funkcja odpowiedzialna z ruch i interackje
    private void action() {
        for (int i = 0; i < this.getOrganisms().size(); i++) {
            Organism current = this.getOrganisms().get(i);
            // ruch
            if (current.getInitiative() != 0 || current.getPower() != 0) {
                current.move(this.getSize(), this.getBlock(current), this.hungrMove(current));
            }
            // interakcje
            for (int j = 0; j < this.getOrganisms().size(); j++) {
                if (i == j) {
                    continue;
                }
                if (this.getOrganisms().get(j).getPosition().equals(current.getPosition())) {
                    if (current.interact(this.getOrganisms().get(j), this.getSize(), this.getBlock(current),
                            this.hungrMove(current)) != null) {
                        this.getOrganisms().add(current.interact(this.getOrganisms().get(j), this.getSize(),
                                this.getBlock(current), this.hungrMove(current)));
                    } else {
                        current.interact(this.getOrganisms().get(j), this.getSize(), this.getBlock(current),
                                this.hungrMove(current));
                    }
                }
                this.rmNull();
            }
            if (current.getClass().getSuperclass().getName().equals("Organisms.Animal")) {
                current.setPower(current.getPower() - 1);
            }
            this.rmNull();
        }
    }

    // usuwanie martwych organizmow z tablicy
    private void rmDead() {
        // szukanie martwych
        ArrayList<Organism> found = new ArrayList<>();
        for (int i = 0; i < this.getOrganisms().size(); i++) {
            if (this.getOrganisms().get(i).getPower() <= 0) {
                found.add(this.getOrganisms().get(i));
            }
        }
        // usuwanie znalezionych
        this.getOrganisms().removeAll(found);
    }

    // usuwanie null organizmow z tablicy
    private void rmNull() {
        // szukanie martwych
        ArrayList<Organism> found = new ArrayList<>();
        for (int i = 0; i < this.getOrganisms().size(); i++) {
            if (this.getOrganisms().get(i) == null) {
                found.add(this.getOrganisms().get(i));
            }
        }
        // usuwanie znalezionych
        this.getOrganisms().removeAll(found);
    }

    // dodawanie rosliny co kolejke
    private void spawnPlants() {
        // losowanie pozycji
        Position poz = randPos(this.getSize());
        // losowanie typu szanse 1:4
        int type = ThreadLocalRandom.current().nextInt(0, 5);
        switch (type) {
        case 0:
            this.getOrganisms().add(new Apple(null, poz));
            break;

        default:
            this.getOrganisms().add(new Grass(null, poz));
            break;
        }
    }

    public World(int size) {
        this.setSize(size);
        this.setOrganisms(new ArrayList<>());
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public Draw getDisplay() {
        return display;
    }

    public void setDisplay(Draw display) {
        this.display = display;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public BufferStrategy getBuff() {
        return buff;
    }

    public void setBuff(BufferStrategy buff) {
        this.buff = buff;
    }

    public SpriteS getSpr() {
        return spr;
    }

    public void setSpr(SpriteS spr) {
        this.spr = spr;
    }

    class SortByInitiative implements Comparator<Organism> {
        public int compare(Organism a, Organism b) {
            int ai = 0;
            int bi = 0;
            if (a != null) {
                ai = a.getInitiative();
            }
            if (b != null) {
                bi = b.getInitiative();
            }
            return bi - ai;
        }
    }

    class SortByInitiativeAlt implements Comparator<Organism> {
        public int compare(Organism a, Organism b) {
            int ai = 0;
            int bi = 0;
            if (a != null) {
                ai = a.getInitiative();
            }
            if (b != null) {
                bi = b.getInitiative();
            }
            return ai - bi;
        }
    }
}
