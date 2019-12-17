import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@SuppressWarnings({"SqlResolve", "Duplicates"})
public class Maciej extends Application{
    Text peselerror = new Text("");
    Text miastoerror = new Text("");


    @Override
    public void start(Stage primaryStage){
        VBox formholder = new VBox();
        VBox form = new VBox();
        form.setSpacing(35);
        form.setPadding(new Insets(35));
        form.setAlignment(Pos.BASELINE_CENTER);

        TextField peselfield = new TextField();
        Text peseltext = new Text("PESEL ");
        HBox pesel = new HBox();
        pesel.setPadding(new Insets(35));
        pesel.setSpacing(35);
        pesel.getChildren().addAll(peseltext,peselfield,peselerror);
        TextField miastofield = new TextField();
        Text miastotext = new Text("Miasto");
        HBox miasto = new HBox();
        miasto.setPadding(new Insets(35));
        miasto.setSpacing(35);
        miasto.getChildren().addAll(miastotext,miastofield,miastoerror);
        Button save = new Button("Zapisz");
        form.getChildren().addAll(pesel,miasto,save);

        BorderPane okno = new BorderPane();
        okno.setCenter(formholder);
        ImageView footer = new ImageView();
        Image stopka = new Image("stopka.png",800,0,false,false);
        footer.setImage(stopka);
        okno.setBottom(footer);
        ImageView headerbar = new ImageView();
        Image naglowek = new Image("naglowek.png",800,0,false,false);
        headerbar.setImage(naglowek);
        ImageView icon = new ImageView();
        Image logo = new Image("logo.png",true);
        icon.setImage(logo);
        Group header = new Group(headerbar,icon);
        okno.setTop(header);
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(35));
        buttons.setSpacing(35);
        Button enter = new Button();
        Button show = new Button();
        enter.setStyle("-fx-background-image: url(p1.png);-fx-min-width: 159;-fx-min-height: 38");
        show.setStyle("-fx-background-image: url(p2.png);-fx-min-width: 159;-fx-min-height: 38");
        buttons.getChildren().addAll(enter,show);
        okno.setLeft(buttons);
        formholder.setPadding(new Insets(35));

        enter.setOnAction(event -> {
            peselerror.setText("");
            miastoerror.setText("");
            formholder.setAlignment(Pos.TOP_LEFT);
            peselfield.clear();
            miastofield.clear();
            formholder.getChildren().clear();
            formholder.getChildren().add(form);
        });

        show.setOnAction(event -> validate(peselfield.getText(),miastofield.getText()));

        save.setOnAction(event -> {
            formholder.getChildren().clear();
            formholder.setAlignment(Pos.BASELINE_CENTER);
            formholder.getChildren().add(new Text("Wprowadzono dane."));
        });

        primaryStage.setTitle("Formularz");
        primaryStage.setScene(new Scene(okno, 800, 600));
        primaryStage.show();
    }

    private void validate(String pesel, String miasto){
        peselerror.setText("");
        miastoerror.setText("");
        if(!pesel.matches("[0-9]{11}")){
            peselerror.setText("Blad");
        }
        if(miasto.matches(".*[0-9].*") || miasto.isEmpty() || miasto.length()>50){
            miastoerror.setText("Blad");
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
