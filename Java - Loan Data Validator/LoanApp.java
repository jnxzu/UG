import java.util.ArrayList;

public class LoanApp {
	private String firstname;
	private String lastname;
	private String pesel;
	private String nip;
	private String bank;
	private Date dob;
	private String gender;
	
	public LoanApp(String firstname, String lastname, String pesel, String nip, String bank, Date dob, String gender) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setPesel(pesel);
		this.setNip(nip);
		this.setBank(bank);
		this.setDob(dob);
		this.setGender(gender);
	}
	private boolean checkfirstname(String name) {
		if(name==null || name.length()<2 || name.matches(".*[^a-zA-Z ]+.*")) return false;
		else return true;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		if(checkfirstname(firstname)) this.firstname = firstname;
		else this.firstname = "N/A";
	}
	
	private boolean checklastname(String name) {
		if(name==null || name.length()<2 || name.matches(".*[^a-zA-Z ]+.*")) return false;
		else return true;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		if(checklastname(lastname))	this.lastname = lastname;
		else this.lastname = "N/A";
	}
	
	public ArrayList<Integer> toIntArray(String string) {
		ArrayList<Integer> inty = new ArrayList<Integer>();
		for(int i=0;i<string.length();i++) {
			inty.add(Character.getNumericValue((string.charAt(i))));
		}
		return inty;
	}
	
	private boolean checkpesel(String pesel) {
		if(pesel==null || pesel.length()!=11 || pesel.matches("[0-9]*[^0-9]+[0-9]*")) return false;
		else {
			ArrayList<Integer> peselint = toIntArray(pesel);
			int p10 = 9*peselint.get(0)+7*peselint.get(1)+3*peselint.get(2)+peselint.get(3)+9*peselint.get(4)+7*peselint.get(5)+3*peselint.get(6)+peselint.get(7)+9*peselint.get(8)+7*peselint.get(9);
			int kontrolna = peselint.get(10);
			if(p10%10!=kontrolna) return false;
			else return true;
		}
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		if(checkpesel(pesel)) this.pesel = pesel;
		else this.pesel = "N/A";
	}
	
	private boolean checknip(String nip) {
		if(nip==null || nip.length()!=10 || nip.matches("[0-9]*[^0-9]+[0-9]*")) return false;
		else {
			ArrayList<Integer> nipint = toIntArray(nip);
			int p9 = 6*nipint.get(0)+5*nipint.get(1)+7*nipint.get(2)+2*nipint.get(3)+3*nipint.get(4)+4*nipint.get(5)+5*nipint.get(6)+6*nipint.get(7)+7*nipint.get(8);
			int kontrolna = nipint.get(9);
			if(p9%11!=kontrolna) return false;
			else return true;
		}
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		if(checknip(nip))  this.nip = nip;
		else this.nip = "N/A";
	}
	
	private boolean checkbank(String bank) {
		if(bank==null || bank.length()!=26 || bank.matches("[0-9]*[^0-9]+[0-9]*")) return false;
		else {
			ArrayList<Integer> bankint = toIntArray(bank);
			int p7 = 3*bankint.get(2)+9*bankint.get(3)+7*bankint.get(4)+bankint.get(5)+3*bankint.get(6)+9*bankint.get(7)+7*bankint.get(8);
			int p7mod = p7%10;
			if(p7mod!=0) p7mod=10-p7mod;
			int kontrolna = bankint.get(9);
			if(p7mod!=kontrolna) return false;
			else return true;
		}
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		if(checkbank(bank)) this.bank = bank;
		else this.bank = "N/A";
	}
	
	private boolean checkdob(Date dob) {
		boolean flag = true;
		int monthmod = 0;
		ArrayList<Integer> peselint = toIntArray(this.pesel);
		if(dob.getDzien()!=(peselint.get(4)*10+peselint.get(5))) flag = false;
		if(dob.getRok()%100!=(peselint.get(0)*10+peselint.get(1))) flag = false;
		if(1799<dob.getRok() && dob.getRok()<1900) monthmod = 80;
		if(1999<dob.getRok() && dob.getRok()<2100) monthmod = 20;
		if(2099<dob.getRok() && dob.getRok()<2200) monthmod = 40;
		if(2199<dob.getRok() && dob.getRok()<2300) monthmod = 60;
		if((dob.getMiesiac()+monthmod)!=(peselint.get(2)*10+peselint.get(3))) flag = false;
		return flag;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		if(!pesel.equals("N/A") && checkdob(dob)) this.dob = dob;
		else this.dob = new Date(00,00,0000);
	}
	
	private boolean checkgender(String gender) {
		boolean flag = true;
		if(!gender.equals("M") && !gender.equals("K")) flag = false;
		ArrayList<Integer> peselint = toIntArray(this.pesel);
		if(gender.equals("M") && peselint.get(9)%2==0) flag = false;
		if(gender.equals("K") && peselint.get(9)%2==1) flag = false;
		return flag;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		if(!pesel.equals("N/A") && checkgender(gender)) this.gender = gender;
		else this.gender = "N/A";
	}
	
	@Override
	public String toString() {
		if(firstname.equals("N/A") || lastname.equals("N/A") || pesel.equals("N/A") || nip.equals("N/A") || bank.equals("N/A") || gender.equals("N/A") || dob.equals(new Date(0,0,0))) System.out.println("NIEPOPRAWNE DANE\n");
		else System.out.println("Dane po¿yczkobiorcy: \n");
		return "Imiê: " + firstname + "\nNazwisko: " + lastname + "\nPesel: " + pesel + "\nNIP: " + nip
				+ "\nNumer konta: " + bank + "\nData urodzenia: " + dob + "\nP³eæ: " + gender + "\n\n";
	}
	
	public static void main(String[] args) {
		LoanApp ok = new LoanApp("Dobry","Typ","44051401359","1234563218","65106000760000320000057153",new Date(14,05,1944),"M");
		LoanApp nieok = new LoanApp("Podejrzana2","Bez_peselu","96122705285","1234561218","65106010760000320000057153",new Date(27,12,1996),"XD");
		System.out.println(ok);
		System.out.println(nieok);
	}
}
