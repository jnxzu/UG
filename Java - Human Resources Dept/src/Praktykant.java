
public class Praktykant extends Pracownik {
	private String opiekun;
	private boolean stypendium;
	
	public String getOpiekun() {
		return opiekun;
	}
	public void setOpiekun(String opiekun) {
		this.opiekun = opiekun;
	}
	public boolean isStypendium() {
		return stypendium;
	}
	public void setStypendium(boolean stypendium) {
		this.stypendium = stypendium;
	}
	
	public Praktykant(String imie, String nazwisko, int rokUr, String opiekun, boolean stypendium) {
		super(imie, nazwisko, rokUr);
		this.setOpiekun(opiekun);
		this.setStypendium(stypendium);
	}
	
	public double Hajs() {
		if(this.isStypendium()) return 1500.0;
		else return 0;
	}
	
	@Override
	public String toString() {
		String base = "INFORMACJE O OSOBIE:" + "\nPozycja: " + this.getClass().getSimpleName() + "\nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nRok urodzenia: " + this.getRokUr();
		String styp = "Nie";
		if(this.isStypendium()) styp = "Tak";
		String hajs = "\nMiesieczne zarobki: " + this.Hajs() + "zl";
		String specific = "\nOpiekun: " + this.getOpiekun() + "\nStypendium: " + styp;
		return base + specific + hajs + "\n\n";
	}
	
}
