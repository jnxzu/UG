import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

@SuppressWarnings("SqlResolve")
public class Main extends Application{
    private Text pesel_error = new Text("");
    private Text miasto_error = new Text("");
    private Text kod_error = new Text("");
    private Text tel_error = new Text("");
    private Text mail_error = new Text("");
    private String store_pesel;
    private String store_miasto;
    private String store_kod;
    private String store_tel;
    private String store_mail;

    @Override
    public void start(Stage primaryStage){
        primaryStage.initStyle(StageStyle.UNDECORATED);
        BorderPane okno=new BorderPane();
        HBox buttons = new HBox();
        Button min = new Button("Minimalizuj");
        Button close = new Button("Zamknij");
        buttons.getChildren().addAll(min, close);
        buttons.setAlignment(Pos.TOP_RIGHT);
        buttons.setPadding(new Insets(5,5,5,5));
        buttons.setSpacing(20);
        HBox content = new HBox();
        VBox label = new VBox();
        Text pesel_text = new Text("PESEL:");
        Text miasto_text = new Text("miasto:");
        Text kod_text = new Text("kod pocztowy:");
        Text tel_text = new Text("telefon:");
        Text mail_text = new Text("adres email:");
        label.getChildren().addAll(pesel_text,miasto_text,kod_text,tel_text,mail_text);
        label.setSpacing(30);
        VBox data = new VBox();
        TextField pesel_field = new TextField();
        TextField miasto_field = new TextField();
        TextField kod_field = new TextField();
        HBox tel_box = new HBox();
        TextField tel_field = new TextField();
        Text tel_pattern = new Text("(+48)XXX-XXX-XXX");
        tel_box.getChildren().addAll(tel_field,tel_pattern);
        TextField mail_field = new TextField();
        data.getChildren().addAll(pesel_field,miasto_field,kod_field,tel_box,mail_field);
        data.setSpacing(20);
        data.setPadding(new Insets(15,0,0,0));
        VBox info = new VBox();
        info.getChildren().addAll(pesel_error, miasto_error, kod_error, tel_error, mail_error);
        info.setSpacing(30);
        info.setMinWidth(60);
        content.getChildren().addAll(label,data,info);
        content.setAlignment(Pos.BASELINE_CENTER);
        content.setSpacing(50);
        content.setPadding(new Insets(100,50,0,0));
        okno.setTop(buttons);
        okno.setCenter(content);
        HBox go = new HBox();
        Button enter = new Button("Wprowadz");
        Button check = new Button("Dalej");
        go.setAlignment(Pos.BASELINE_CENTER);
        go.setPadding(new Insets(0,0,100,0));
        go.getChildren().add(check);
        okno.setBottom(go);

        check.setOnAction(event -> {
            if(validate(pesel_field.getText(),miasto_field.getText(),kod_field.getText(),tel_field.getText(),mail_field.getText())){
                store_pesel=pesel_field.getText();
                store_miasto=miasto_field.getText();
                store_kod=kod_field.getText();
                store_tel=tel_field.getText();
                store_mail=mail_field.getText();
//                validate(pesel_field.getText(),miasto_field.getText(),kod_field.getText(),tel_field.getText(),mail_field.getText());
                data.getChildren().clear();
                data.getChildren().addAll(new Text(store_pesel),new Text(store_miasto),new Text(store_kod),new Text(store_tel),new Text(store_mail));
                data.setSpacing(label.getSpacing());
                data.setPadding(new Insets(-18,0,0,0));
                go.getChildren().clear();
                go.getChildren().add(enter);
            }
        });
        enter.setOnAction(event -> {
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con=DriverManager.getConnection("#####"); // REDACTED
                PreparedStatement pst = con.prepareStatement("insert into Form(pesel, miasto, kod, telefon, email) values (?,?,?,?,?);");
                pst.setString(1, store_pesel);
                pst.setString(2, store_miasto);
                pst.setString(3, store_kod);
                pst.setString(4, store_tel);
                pst.setString(5, store_mail);
                pst.executeUpdate();
                con.close();
                content.getChildren().clear();
                content.getChildren().add(new Text("Prawidlowo dodano do bazy danych."));
            }catch(SQLException|ClassNotFoundException rip){
                content.getChildren().clear();
                content.getChildren().add(new Text(rip.getMessage()));
            }
        });
        min.setOnAction(event -> primaryStage.setIconified(true));
        close.setOnAction(event -> primaryStage.close());

        primaryStage.setTitle("Formularz");
        primaryStage.setScene(new Scene(okno, 800, 600));
        primaryStage.show();
    }

    private boolean validate(String pesel, String miasto, String kod, String tel, String mail){
        pesel_error.setText("Dane poprawne");
        miasto_error.setText("Dane poprawne");
        kod_error.setText("Dane poprawne");
        tel_error.setText("Dane poprawne");
        mail_error.setText("Dane poprawne");
        boolean flag = true;
        if(!pesel.matches("[0-9]{11}")){
            flag=false;
            pesel_error.setText("Blad");
        }
        if(miasto.matches(".*[0-9].*") || miasto.isEmpty() || miasto.length()>50){
            flag=false;
            miasto_error.setText("Blad");
        }
        if(!kod.matches("[0-9]{2}-[0-9]{3}")){
            flag=false;
            kod_error.setText("Blad");
        }
        if(!tel.matches("[0-9]{9}")){
            flag=false;
            tel_error.setText("Blad");
        }
        if(!mail.matches("[^@]+@[^\\.]+\\..+") || mail.length()>50){
            flag=false;
            mail_error.setText("Blad");
        }
        return flag;
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
