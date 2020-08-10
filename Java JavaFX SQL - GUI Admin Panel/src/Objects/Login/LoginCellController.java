package Objects.Login;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LoginCellController{
    @FXML
    private Text lp;
    @FXML
    private Text data;
    @FXML
    private Text czas;
    @FXML
    private Text login;

    public void setLp(int lpoz){
        lp.setText(String.valueOf(lpoz));
    }

    public void setData(String date){
        data.setText(date);
    }

    public void setCzas(String time){
        czas.setText(time);
    }

    public void setLogin(String log){
        login.setText(log);
    }
}
