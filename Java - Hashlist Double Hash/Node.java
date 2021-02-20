public class Node {
    private int ilosc;
    private String nazwisko;

    public Node(String nazwisko){
        this.setIlosc(0);
        this.setNazwisko(nazwisko);
    }

    public void setIlosc(int ilosc){
        this.ilosc = ilosc;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }

    public int getIlosc(){
        return this.ilosc;
    }

    public String getNazwisko(){
        return this.nazwisko;
    }
}
