
public class PracownikFizyczny extends Pracownik {
	private double stawkaGodzinowa;
	private int godziny;
	private int nadgodziny;
	private String kierownik;
	private String info;
	
	public double getStawkaGodzinowa() {
		return stawkaGodzinowa;
	}
	public void setStawkaGodzinowa(double stawkaGodzinowa) {
		this.stawkaGodzinowa = stawkaGodzinowa;
	}
	public int getGodziny() {
		return godziny;
	}
	public void setGodziny(int godziny) {
		this.godziny = godziny;
	}
	public int getNadgodziny() {
		return nadgodziny;
	}
	public void setNadgodziny(int nadgodziny) {
		this.nadgodziny = nadgodziny;
	}
	public String getKierownik() {
		return kierownik;
	}
	public void setKierownik(String kierownik) {
		this.kierownik = kierownik;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public PracownikFizyczny(String imie, String nazwisko, int rokUr, double stawkaGodzinowa, int godziny, int nadgodziny, String kierownik, String info) {
		super(imie, nazwisko, rokUr);
		this.setStawkaGodzinowa(stawkaGodzinowa);
		this.setGodziny(godziny);
		this.setNadgodziny(nadgodziny);
		this.setKierownik(kierownik);
		this.setInfo(info);
	}
	
	public double Hajs() {
		return (this.getStawkaGodzinowa()*this.getGodziny())+(1.5*this.getStawkaGodzinowa()*this.getNadgodziny());
	}
	
	@Override
	public String toString() {
		String base = "INFORMACJE O OSOBIE:" + "\nPozycja: " + this.getClass().getSimpleName() + "\nImie: " + this.getImie() + "\nNazwisko: " + this.getNazwisko() + "\nRok urodzenia: " + this.getRokUr();
		String hajs = "\nMiesieczne zarobki: " + this.Hajs() + "zl";
		String specific = "\nStawka godzinowa: " + this.getStawkaGodzinowa() + "zl/h\nPrzepracowane godziny: " + this.getGodziny() + "\nPrzepracowane nadgodziny: " + this.getNadgodziny() + "\nKierownik: " + this.getKierownik() + "\nUmiejetnosci: " + this.getInfo();
		return base + specific + hajs + "\n\n";
	}
	
}
