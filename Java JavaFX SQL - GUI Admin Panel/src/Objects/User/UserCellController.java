package Objects.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UserCellController{
    @FXML
    private Text login;
    @FXML
    private Text haslo;
    @FXML
    private ImageView image;
    @FXML
    private Button editbutton;
    @FXML
    private Button delbutton;

    public void setLogin(String val){
        login.setText(val);
    }

    public void setHaslo(String val){
        haslo.setText(val);
    }

    public void setImage(String val){
        image.setImage(new Image(val));
    }

    public Button getDelbutton(){
        return delbutton;
    }

    public Button getEditbutton(){
        return editbutton;
    }
}
