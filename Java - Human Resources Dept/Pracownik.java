
public class Pracownik {
	private String imie;
	private String nazwisko;
	private int rokUr;
	
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public int getRokUr() {
		return rokUr;
	}
	public void setRokUr(int rokUr) {
		this.rokUr = rokUr;
	}
	
	public Pracownik(String imie, String nazwisko, int rokUr) {
		this.setImie(imie);
		this.setNazwisko(nazwisko);
		this.setRokUr(rokUr);
	}
	
	public static void main(String[] args) {
		Praktykant prakt = new Praktykant("Ksawier","Krawczyk",1997,"Stanis³aw Brzozowski",false);
		System.out.println(prakt);
		Zarzad menago = new Zarzad("Oliwia","Kowalczyk",1965,5000.0,"Miko³aj Wawrzyniak",6);
		System.out.println(menago);
		Kierownik boss = new Kierownik("Daniel","Bukowski",1980,4000.0,10,500.0,"649546833",107,"HR");
		System.out.println(boss);
		PracownikUmyslowy brain = new PracownikUmyslowy("Dominika","Grabowska",1990,4000.0,10,"Angelika Mazur","649546833",107);
		System.out.println(brain);
		PracownikFizyczny brawn = new PracownikFizyczny("Oliwier","Wrona",1988,13.0,40,12,"Natalia Piasecka","Spawacz");
		System.out.println(brawn);
	}
	
}
