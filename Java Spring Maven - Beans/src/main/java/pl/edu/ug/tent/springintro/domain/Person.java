package pl.edu.ug.tent.springintro.domain;

public class Person {

  String fname;
  String lname;
  String email;
  String comp;

  public Person() {
    System.out.println("Creating person:\n" + this);
  }

  public Person(String fname, String lname, String email, String comp) {
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.comp = comp;
    System.out.println("Creating person:\n" + this);
  }

  @Override
  public String toString() {
    return fname + " " + lname + "\n" + email + "\n" + comp + "\n";
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getComp() {
    return comp;
  }

  public void setComp(String comp) {
    this.comp = comp;
  }
}
