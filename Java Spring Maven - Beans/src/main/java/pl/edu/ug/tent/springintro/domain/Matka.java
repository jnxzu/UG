package pl.edu.ug.tent.springintro.domain;

public class Matka extends Osoba {
    String fname;
    String lname;
    int yob;
    Dziecko kid;

    public Matka(String fname, String lname, int yob, Dziecko kid) {
        super(fname, lname, yob);
        this.fname = fname;
        this.lname = lname;
        this.yob = yob;
        this.kid = kid;
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

    public Dziecko getKid() {
        return kid;
    }

    public void setKid(Dziecko kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return super.toString() + "Dziecko: " + this.kid + "\n";
    }

}