package Objects.Team;

public class Team{
    private int id;
    private String nazwa;
    private String miasto;
    private String kraj;

    public Team(int id, String nazwa, String miasto, String kraj){
        setId(id);
        setNazwa(nazwa);
        setMiasto(miasto);
        setKraj(kraj);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getNazwa(){
        return nazwa;
    }

    private void setNazwa(String nazwa){
        this.nazwa=nazwa;
    }

    public String getMiasto(){
        return miasto;
    }

    private void setMiasto(String miasto){
        this.miasto=miasto;
    }

    public String getKraj(){
        return kraj;
    }

    private void setKraj(String kraj){
        this.kraj=kraj;
    }
}
