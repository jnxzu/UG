package Objects.Team;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TeamCellController{
    @FXML
    private Text klub;
    @FXML
    private ImageView flag;
    @FXML
    private Text miasto;

    public void setKlub(String club){
        klub.setText(club);
    }

    public void setFlag(String url){
        flag.setImage(new Image("/img/flags/"+url+".png"));
    }

    public void setMiasto(String city){
        miasto.setText(city);
    }
}
