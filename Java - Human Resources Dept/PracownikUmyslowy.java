
public class PracownikUmyslowy extends Pracownik {
	private double ryczalt;
	private int premia;
	private String kierownik;
	private String tel;
	private int room;
	
	public double getRyczalt() {
		return ryczalt;
	}
	public void setRyczalt(double ryczalt) {
		this.ryczalt = ryczalt;
	}
	public int getPremia() {
		return premia;
	}
	public void setPremia(int premia) {
		this.premia = premia;
	}
	public String getKierownik() {
		return kierownik;
	}
	public void setKierownik(String kierownik) {
		this.kierownik = kierownik;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	
	public PracownikUmyslowy(String imie, String nazwisko, int rokUr, double ryczalt, int premia, String kierownik, String tel, int room) {
		super(imie, nazwisko, rokUr);
		this.setRyczalt(ryczalt);
		this.setPremia(premia);
		this.setKierownik(kierownik);
		this.setTel(tel);
		this.setRoom(room);
	}
	
	public double Hajs() {
		return this.getRyczalt()+(this.getRyczalt()*this.getPremia()/100);
	}
	
	@Override
	public String toString() {
		String base = "INFORMACJE O OSOBIE:" + "\nPozycja: " + this.getClass().getSimpleName() + "\nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nRok urodzenia: " + this.getRokUr();
		String hajs = "\nMiesieczne zarobki: " + this.Hajs() + "zl";
		String specific = "\nRyczalt: " + this.getRyczalt() + "zl\nPremia: " + this.getPremia() + "%\nKierownik: " + this.getKierownik() + "\nTelefon: " + this.getTel() + "\nPokoj: " + this.getRoom();
		return base + specific + hajs + "\n\n";
	}
	
}
