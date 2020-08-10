package Objects.Login;

public class Login{
    private int lp;
    private String data;
    private String czas;
    private String login;

    public Login(int lp, String data, String czas, String login){
        setLp(lp);
        setData(data);
        setCzas(czas);
        setLogin(login);
    }

    public int getLp(){
        return lp;
    }

    private void setLp(int lp){
        this.lp=lp;
    }

    public String getData(){
        return data;
    }

    private void setData(String data){
        this.data=data;
    }

    public String getCzas(){
        return czas;
    }

    private void setCzas(String czas){
        this.czas=czas;
    }

    public String getLogin(){
        return login;
    }

    private void setLogin(String login){
        this.login=login;
    }
}
