import java.util.Comparator;

public class Produkt {
	public String nazwa;
	public double cena;
	public double discounted;
	
	public Produkt(String nazwa, double cena) {
		this.nazwa = nazwa;
		this.cena = cena;
		this.discounted = cena;
	}
	
	public String toString() {
		if(discounted!=cena) {
			return nazwa + "\n Cena (Przeceniona): " + discounted + "\n";
		}
		return nazwa + "\n Cena: " + cena + "\n";
	}

	public String getNazwa() {
		return nazwa;
	}

	public double getDiscounted() {
		return discounted;
	}

	public void setDiscounted(double discounted) {
		this.discounted = discounted;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	public static Comparator<Produkt> NazwaAsc = new Comparator<Produkt>() {
	    public int compare(Produkt a, Produkt b) {
	        return a.nazwa.compareTo(b.nazwa);
	    }
	};
	
	public static Comparator<Produkt> NazwaDsc = new Comparator<Produkt>() {
	    public int compare(Produkt a, Produkt b) {
	        return b.nazwa.compareTo(a.nazwa);
	    }
	};

	public static Comparator<Produkt> CenaAsc = new Comparator<Produkt>() {
		public int compare(Produkt a, Produkt b) {
			double cenaA = a.getCena();
			double cenaB = b.getCena();
			return (int) (cenaA-cenaB);
		}
	};
	
	public static Comparator<Produkt> CenaDsc = new Comparator<Produkt>() {
		public int compare(Produkt a, Produkt b) {
			double cenaA = a.getCena();
			double cenaB = b.getCena();
			return (int) (cenaB-cenaA);
		}
	};
}
