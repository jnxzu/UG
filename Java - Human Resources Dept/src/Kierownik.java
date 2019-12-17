
public class Kierownik extends Pracownik {
	private double ryczalt;
	private int premia;
	private double bonus;
	private String tel;
	private int room;
	private String dzial;
	
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
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
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
	public String getDzial() {
		return dzial;
	}
	public void setDzial(String dzial) {
		this.dzial = dzial;
	}
	
	public Kierownik(String imie, String nazwisko, int rokUr, double ryczalt, int premia, double bonus, String tel, int room, String dzial) {
		super(imie, nazwisko, rokUr);
		this.setRyczalt(ryczalt);
		this.setPremia(premia);
		this.setBonus(bonus);
		this.setTel(tel);
		this.setRoom(room);
		this.setDzial(dzial);
	}
	
	public double Hajs() {
		return this.getRyczalt()+(this.getRyczalt()*this.getPremia()/100)+this.getBonus();
	}
	
	@Override
	public String toString() {
		String base = "INFORMACJE O OSOBIE:" + "\nPozycja: " + this.getClass().getSimpleName() + "\nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nRok urodzenia: " + this.getRokUr();
		String hajs = "\nMiesieczne zarobki: " + this.Hajs() + "zl";
		String specific = "\nRyczalt: " + this.getRyczalt() + "zl\nPremia: " + this.getPremia() + "%\nBonus: " + this.getBonus() + "zl\nTelefon: " + this.getTel() + "\nPokoj: " + this.getRoom() + "\nDzial: " + this.getDzial();
		return base + specific + hajs + "\n\n";
	}
	
}
