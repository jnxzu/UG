package Objects.Player;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class PlayerCellController{
    @FXML
    private Text imie;
    @FXML
    private Text nazwisko;
    @FXML
    private Text wiek;
    @FXML
    private ImageView flag;
    @FXML
    private Text wartosc;

    public void setImie(String name){
        imie.setText(name);
    }

    public void setNazwisko(String lastname){
        nazwisko.setText(lastname);
    }

    public void setWiek(int age){
        wiek.setText(String.valueOf(age));
    }

    public void setFlag(String url){
        flag.setImage(new Image("/img/flags/"+url+".png"));
    }

    public void setWartosc(int value){
        wartosc.setText("£"+value+"m");
    }
}
