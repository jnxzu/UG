package Objects.Player;

public class Player{
    private int id;
    private String imie;
    private String nazwisko;
    private int wiek;
    private String kraj;
    private int wartosc;

    public Player(int id, String imie, String nazwisko, int wiek, String kraj, int wartosc){
        setId(id);
        setImie(imie);
        setNazwisko(nazwisko);
        setWiek(wiek);
        setKraj(kraj);
        setWartosc(wartosc);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getImie(){
        return imie;
    }

    private void setImie(String imie){
        this.imie=imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    private void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    public int getWiek(){
        return wiek;
    }

    private void setWiek(int wiek){
        this.wiek=wiek;
    }

    public String getKraj(){
        return kraj;
    }

    private void setKraj(String kraj){
        this.kraj=kraj;
    }

    public int getWartosc(){
        return wartosc;
    }

    private void setWartosc(int wartosc){
        this.wartosc=wartosc;
    }
}
