
public class Date {
	public int getDzien() {
		return dzien;
	}

	public int getMiesiac() {
		return miesiac;
	}

	public int getRok() {
		return rok;
	}

	int dzien;
	int miesiac;
	int rok;
	
	public Date(int dzien, int miesiac, int rok) {
		this.dzien = dzien;
		this.miesiac = miesiac;
		this.rok = rok;
	}

	@Override
	public String toString() {
		if(dzien==0 && miesiac==0 && rok==0) return "N/A";
		else return dzien + "-" + miesiac + "-" + rok;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dzien;
		result = prime * result + miesiac;
		result = prime * result + rok;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (dzien != other.dzien)
			return false;
		if (miesiac != other.miesiac)
			return false;
		if (rok != other.rok)
			return false;
		return true;
	}
}
