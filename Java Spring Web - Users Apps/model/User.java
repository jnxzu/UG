package J.AppUsers.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import J.AppUsers.support.RandomString;
import J.AppUsers.validator.Password;
import lombok.Data;

@Data
public class User {
    private String id;
    @NotNull
    @Size(min = 2, message = "First Name too short.")
    private String imie;
    @NotNull
    @Size(min = 2, message = "Last Name too short.")
    private String nazwisko;
    @Email
    private String email;
    @NotNull
    @Size(min = 4, message = "Country name too short.")
    private String kraj;
    @NotNull
    @Size(min = 5, message = "Login too short.")
    private String login;
    @Password
    private String haslo;
    private List<String> apps;

    public User(String id, String imie, String nazwisko, String email, String kraj, String login, String haslo){
        this.id = id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.kraj=kraj;
        this.login=login;
        this.haslo=haslo;
        this.apps = new ArrayList<String>();
    }

    public User(){
        this.id = new RandomString().nextString();
        this.imie = "";
        this.nazwisko = "";
        this.email = "";
        this.login = "";
        this.haslo = "";
        this.apps = new ArrayList<String>();
    }
}