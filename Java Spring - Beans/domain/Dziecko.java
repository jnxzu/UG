package pl.edu.ug.tent.springintro.domain;

public class Dziecko extends Osoba {
    String fname;
    String lname;
    int yob;
    Ojciec dad;
    Matka mom;

    public Dziecko(String fname, String lname, int yob, Ojciec dad, Matka mom) {
        super(fname, lname, yob);
        this.fname = fname;
        this.lname = lname;
        this.yob = yob;
        this.dad = dad;
        this.mom = mom;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public Ojciec getDad() {
        return dad;
    }

    public void setDad(Ojciec dad) {
        this.dad = dad;
    }

    public Matka getMom() {
        return mom;
    }

    public void setMom(Matka mom) {
        this.mom = mom;
    }

    @Override
    public String toString() {
        return super.toString() + "Ojciec: " + this.dad + "\nMatka: " + this.mom + "\n";
    }
}