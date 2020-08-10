package Objects.User;

public class User{
    private String login;
    private String haslo;
    private boolean admin;
    private int id;

    public User(int id, String login, String haslo, boolean admin){
        setId(id);
        setLogin(login);
        setHaslo(haslo);
        setAdmin(admin);
    }

    public int getId(){
        return id;
    }

    private void setId(int id){
        this.id=id;
    }

    public String getLogin(){
        return login;
    }

    private void setLogin(String login){
        this.login=login;
    }

    public String getHaslo(){
        return haslo;
    }

    private void setHaslo(String haslo){
        this.haslo=haslo;
    }

    public boolean isAdmin(){
        return admin;
    }

    private void setAdmin(boolean admin){
        this.admin=admin;
    }
}
