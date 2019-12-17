package pl.edu.ug.tent.springintro.domain;

public class Rodzina {
    Ojciec dad;
    Matka mom;
    Dziecko kid;

    public Rodzina(Ojciec dad, Matka mom, Dziecko kid) {
        this.dad = dad;
        this.mom = mom;
        this.kid = kid;
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

    public Dziecko getKid() {
        return kid;
    }

    public void setKid(Dziecko kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "RODZINA: " + dad.getLname() + "\nOjciec: " + dad.getFname() + " " + dad.getLname() + "\nMatka: " + mom.getFname() + " " + mom.getLname()
                + "\nDziecko: " + kid.getFname() + " " + kid.getLname() + "\n";
    }

}