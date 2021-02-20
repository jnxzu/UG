import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

@SuppressWarnings("ALL")
public class Buttonsbased extends Application{
    @Override
    public void start(Stage primaryStage){
        BorderPane okno=new BorderPane();
        okno.setStyle("-fx-background-color: pink");
        VBox l=new VBox();
        l.setSpacing(100);
        l.setPadding(new Insets(125, 10, 10, 10));
        l.setStyle("-fx-background-color: black");
        okno.setLeft(l);
        Button patient=new Button("Wizyta");
        patient.setPrefWidth(200);
        Button check=new Button("Polaczenie");
        check.setPrefWidth(200);
        Button doctor=new Button("Lekarze");
        doctor.setPrefWidth(200);
        l.getChildren().addAll(check, doctor, patient);
        VBox r=new VBox();
        r.setPadding(new Insets(250, 50, 50, 50));
        okno.setCenter(r);
        VBox inner=new VBox();
        inner.setAlignment(Pos.BASELINE_CENTER);
        check.setOnAction(e -> {
            r.getChildren().clear();
            inner.getChildren().clear();
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con=DriverManager.getConnection("#####"); // REDACTED
                inner.getChildren().add(new Text("Polaczono z baza danych."));
                r.getChildren().add(inner);
                con.close();
            }catch(SQLException|ClassNotFoundException rip){
                inner.getChildren().add(new Text("Blad polaczenia."));
                r.getChildren().add(inner);
            }
        });
        doctor.setOnAction(e -> {
            r.getChildren().clear();
            inner.getChildren().clear();
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con=DriverManager.getConnection("#####"); // REDACTED
                Statement stm=con.createStatement();
                ResultSet result=stm.executeQuery("select Lekarz.nazwisko, Specjalizacja.nazwa, Lekarz.pokoj from Lekarz left join Specjalizacja on Lekarz.specjalizacja=Specjalizacja.id");
                ResultSetMetaData resultMeta=result.getMetaData();
                int kolumny=resultMeta.getColumnCount();
                inner.getChildren().add(new Text("| NAZWISKO | SPECJALIZACJA | POKOJ |"));
                inner.getChildren().add(new Text(""));
                PrintSQL(inner, result, kolumny);
                stm.close();
                con.close();
            }catch(ClassNotFoundException|SQLException rip){
                inner.getChildren().add(new Text("Blad polaczenia."));
            }
            r.getChildren().add(inner);
        });
        patient.setOnAction(e -> {
            r.getChildren().clear();
            inner.getChildren().clear();
            TextField nazwisko=new TextField();
            nazwisko.setPromptText("Nazwisko");
            Button sprawdz=new Button("Sprawdz");
            inner.getChildren().addAll(nazwisko, sprawdz);
            Text nah=new Text("Nie podano prawidlowego nazwiska.");
            Text error=new Text("Blad polaczenia/SQL.");
            sprawdz.setOnAction(ev -> {
                if(nazwisko.getText()!=null && !nazwisko.getText().isEmpty() && nazwisko.getText().matches("[a-zA-Z]+ *[a-zA-Z]*")){
                    try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection con=DriverManager.getConnection("#####"); // REDACTED
                        Statement stm=con.createStatement();
                        ResultSet result=stm.executeQuery("select top 1 Pacjent.nazwisko as Pacjent, isnull(Wizyta.data, getdate()), Lekarz.nazwisko, Lekarz.pokoj from Wizyta left join Pacjent on Wizyta.pacjent=Pacjent.id left join Lekarz on Wizyta.lekarz=Lekarz.id where Pacjent.nazwisko='"+nazwisko.getText()+"' order by Wizyta.data desc");
                        if(!result.next()){
                            inner.getChildren().add(new Text("Nie ma takiego pacjenta lub ten pacjent nie ma zaplanowanej wizyty."));
                        }else{
                            result=stm.executeQuery("select top 1 Pacjent.nazwisko as Pacjent, isnull(Wizyta.data, getdate()), Lekarz.nazwisko, Lekarz.pokoj from Wizyta left join Pacjent on Wizyta.pacjent=Pacjent.id left join Lekarz on Wizyta.lekarz=Lekarz.id where Pacjent.nazwisko='"+nazwisko.getText()+"' order by Wizyta.data desc");
                            ResultSetMetaData resultMeta=result.getMetaData();
                            int kolumny=resultMeta.getColumnCount();
                            inner.getChildren().clear();
                            inner.getChildren().add(new Text("| PACJENT | DATA | LEKARZ | POKOJ |"));
                            PrintSQL(inner, result, kolumny);
                        }
                        stm.close();
                        con.close();
                    }catch(ClassNotFoundException|SQLException rip){
                        inner.getChildren().remove(error);
                        inner.getChildren().add(error);
                    }
                }else{
                    inner.getChildren().remove(nah);
                    inner.getChildren().add(nah);
                }
            });
            r.getChildren().add(inner);
        });
        primaryStage.setTitle("Przychodnia");
        primaryStage.setScene(new Scene(okno, 800, 600));
        primaryStage.show();
    }

    private void PrintSQL(VBox inner, ResultSet result, int kolumny) throws SQLException{
        StringBuilder resultString=new StringBuilder();
        while(result.next()){
            for(int i=1; i<=kolumny; i++){
                resultString.append(" | ").append(result.getString(i));
            }
            resultString.append(" | ");
            inner.getChildren().add(new Text(resultString.toString()));
            resultString=new StringBuilder();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
