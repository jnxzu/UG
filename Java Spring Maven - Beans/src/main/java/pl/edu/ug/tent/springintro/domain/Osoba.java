package pl.edu.ug.tent.springintro.domain;

public class Osoba {
    String fname;
    String lname;
    int yob;

    public Osoba(String fname, String lname, int yob) {
        this.fname = fname;
        this.lname = lname;
        this.yob = yob;
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

    @Override
    public String toString() {
        return fname + lname + "\n" + yob + "\n";
    }

}