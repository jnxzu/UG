import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Koszyk {
	public ArrayList<Produkt> zakupy = new ArrayList<Produkt>();
	
	public static void main(String[] args) {
		Produkt okno = new Produkt("Okno",50.0);
		Produkt drzwi = new Produkt("Drzwi",75.0);
		Produkt dach = new Produkt("Dach",200.0);
		Produkt lustro = new Produkt("Lustro",25.0);
		Koszyk kosz = new Koszyk();
		kosz.zakupy.add(okno);
		kosz.Kupon();
		//kosz.zakupy.add(drzwi);
		kosz.zakupy.add(lustro);
		kosz.zakupy.add(dach);
		//Collections.sort(kosz.zakupy, Produkt.CenaAsc);
		//Collections.sort(kosz.zakupy, Produkt.CenaDsc);
		//Collections.sort(kosz.zakupy, Produkt.NazwaAsc);
		//Collections.sort(kosz.zakupy, Produkt.NazwaDsc);
		System.out.println("Suma: " + kosz.Suma());
		System.out.println(kosz);
	}
	
	public void Kupon() {
		int i = zakupy.size()-1;
		zakupy.get(i).setDiscounted(zakupy.get(i).getCena()*0.7);
	}
	
	public Produkt najtanszy() {
		Produkt min = new Produkt("",0);
		Collections.sort(zakupy, Produkt.CenaAsc);
		min = zakupy.get(0);
		return min;
	}
	
	public Produkt najdrozszy() {
		Produkt max = new Produkt("",0);
		Collections.sort(zakupy, Produkt.CenaDsc);
		max = zakupy.get(0);
		return max;
	}

	public String toString() {
		String result = "Zawartosc koszyka: \n \n";
		for(int i=0;i<zakupy.size();i++) {
			result += zakupy.get(i);
		}
		return result;
	}
	
	public double Suma() {
		double sum = 0;
		if (zakupy.size() == 3) {
			Collections.sort(zakupy, Produkt.CenaAsc);
			zakupy.get(0).setDiscounted(0.0);
		}
		for(int i=0;i<zakupy.size();i++) {
			sum+=zakupy.get(i).getDiscounted();
		}
		if (sum>200.0) {
			Produkt kubek = new Produkt("Firmowy Kubek (GRATIS)",0.0);
			zakupy.add(kubek);
		}
		if (sum>300.0) {
			System.out.println("Znizka 5%\n");
			sum=sum*0.95;
		}
		return sum;
	}
}
