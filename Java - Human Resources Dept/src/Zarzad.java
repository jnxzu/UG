
public class Zarzad extends Pracownik {
	private double ryczalt;
	private String asystent;
	private int spotkania;
	
	public double getRyczalt() {
		return ryczalt;
	}
	public void setRyczalt(double ryczalt) {
		this.ryczalt = ryczalt;
	}
	public String getAsystent() {
		return asystent;
	}
	public void setAsystent(String asystent) {
		this.asystent = asystent;
	}
	public int getSpotkania() {
		return spotkania;
	}
	public void setSpotkania(int spotkania) {
		this.spotkania = spotkania;
	}
	
	public Zarzad(String imie, String nazwisko, int rokUr, double ryczalt, String asystent, int spotkania) {
		super(imie, nazwisko, rokUr);
		this.setRyczalt(ryczalt);
		this.setAsystent(asystent);
		this.setSpotkania(spotkania);
	}
	
	public double Hajs() {
		return this.getRyczalt()+this.getSpotkania()*500;
	}
	
	@Override
	public String toString() {
		String base = "INFORMACJE O OSOBIE:" + "\nPozycja: " + this.getClass().getSimpleName() + "\nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nRok urodzenia: " + this.getRokUr();
		String hajs = "\nMiesieczne zarobki: " + this.Hajs() + "zl";
		String specific = "\nRyczalt: " + this.getRyczalt() + "zl\nAsystent: " + this.getAsystent() + "\nIlosc odbytych spotkan: " + this.getSpotkania();
		return base + specific + hajs + "\n\n";
	}
	
}
