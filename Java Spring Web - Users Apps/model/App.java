package J.AppUsers.model;

import java.util.ArrayList;
import java.util.List;

import J.AppUsers.support.RandomString;
import J.AppUsers.validator.Domain;
import J.AppUsers.validator.Name;
import lombok.Data;

@Data
public class App {
    private String id;
    @Name
    private String name;
    @Domain
    private String domain;
    private List<String> users;

    public App(String id, String name, String domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.users = new ArrayList<String>();
    }

    public App(){
        this.id = new RandomString().nextString();
        this.name = "";
        this.domain = "";
        this.users = new ArrayList<String>();
    }
}