import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class Controller{
    private float total;
    private int prodcount;
    private HashMap<String, Produkt> koszyk=new HashMap<>();
    private int[] stany=new int[9];
    private float[] ceny=new float[3];
    private String[] nazwy=new String[3];
    @FXML
    private HBox categories;
    @FXML
    private ImageView banner;
    @FXML
    private Text pln;
    @FXML
    private Text count;
    @FXML
    private VBox core;
    private HBox prods=new HBox();
    private VBox prod1=new VBox();
    private VBox prod2=new VBox();
    private VBox prod3=new VBox();
    private ImageView prod1iv=new ImageView();
    private ImageView prod2iv=new ImageView();
    private ImageView prod3iv=new ImageView();
    private Text nazwa1=new Text("");
    private Text nazwa2=new Text("");
    private Text nazwa3=new Text("");
    private Text cena1=new Text("");
    private Text cena2=new Text("");
    private Text cena3=new Text("");
    private Text stan1=new Text("");
    private Text stan2=new Text("");
    private Text stan3=new Text("");
    private Button dodaj1=new Button("Kup");
    private Button dodaj2=new Button("Kup");
    private Button dodaj3=new Button("Kup");
    private Text emailerror=new Text("");

    private void ProdSetup(){
        core.getChildren().clear();
        core.setAlignment(Pos.CENTER);
        prods.setAlignment(Pos.CENTER);
        prods.setSpacing(150);
        prod1.setSpacing(15);
        prod1iv.setFitHeight(250);
        prod1iv.setFitWidth(250);
        prod1iv.setPreserveRatio(false);
        prod1.getChildren().addAll(prod1iv, nazwa1, cena1, stan1, dodaj1);
        prod1.setAlignment(Pos.CENTER);
        prod2.setSpacing(15);
        prod2iv.setFitHeight(250);
        prod2iv.setFitWidth(250);
        prod2iv.setPreserveRatio(false);
        prod2.getChildren().addAll(prod2iv, nazwa2, cena2, stan2, dodaj2);
        prod2.setAlignment(Pos.CENTER);
        prod3.setSpacing(15);
        prod3iv.setFitHeight(250);
        prod3iv.setFitWidth(250);
        prod3iv.setPreserveRatio(false);
        prod3.getChildren().addAll(prod3iv, nazwa3, cena3, stan3, dodaj3);
        prod3.setAlignment(Pos.CENTER);
        prods.getChildren().addAll(prod1, prod2, prod3);
        core.getChildren().add(prods);
    }

    private void ProdReset(){
        prods.getChildren().clear();
        prod1.getChildren().clear();
        prod2.getChildren().clear();
        prod3.getChildren().clear();
    }

    @FXML
    private void ShowSztosy() throws SQLException, ClassNotFoundException{
        ShowSetup(3);
    }

    @FXML
    private void ShowHity() throws SQLException, ClassNotFoundException{
        ShowSetup(0);
    }

    @FXML
    private void ShowPromo() throws SQLException, ClassNotFoundException{
        ShowSetup(6);
    }

    private void ShowSetup(int k) throws SQLException, ClassNotFoundException{
        ProdSetup();
        stan1.setText(String.valueOf(stany[k]));
        stan2.setText(String.valueOf(stany[k+1]));
        stan3.setText(String.valueOf(stany[k+2]));
        prod1iv.setImage(new Image("img/produkty/"+(k+1)+".jpg"));
        prod2iv.setImage(new Image("img/produkty/"+(k+2)+".jpg"));
        prod3iv.setImage(new Image("img/produkty/"+(k+3)+".jpg"));
        nazwy[0]=getNazwa(k+1);
        nazwy[1]=getNazwa(k+2);
        nazwy[2]=getNazwa(k+3);
        nazwa1.setText(nazwy[0]);
        nazwa2.setText(nazwy[1]);
        nazwa3.setText(nazwy[2]);
        ceny[0]=getCena(k+1);
        ceny[1]=getCena(k+2);
        ceny[2]=getCena(k+3);
        cena1.setText(ceny[0]+" PLN");
        cena2.setText(ceny[1]+" PLN");
        cena3.setText(ceny[2]+" PLN");
        dodaj1.setOnAction(event -> Buy(k));
        dodaj2.setOnAction(event -> Buy(k+1));
        dodaj3.setOnAction(event -> Buy(k+2));
    }

    @FXML
    private void Mail(){
        core.getChildren().clear();
        HBox mailstuff=new HBox();
        mailstuff.setPadding(new Insets(50));
        mailstuff.setSpacing(200);
        VBox mailleft=new VBox();
        mailleft.setMinWidth(250);
        mailleft.setAlignment(Pos.CENTER);
        mailleft.setSpacing(100);
        VBox emailbox=new VBox();
        Text emailtext=new Text("Email");
        TextField email=new TextField();
        emailbox.getChildren().addAll(emailtext, email, emailerror);
        Button submit=new Button("Wyslij");
        mailleft.getChildren().addAll(emailbox, submit);
        TextArea msg=new TextArea();
        mailstuff.getChildren().addAll(mailleft, msg);
        core.getChildren().add(mailstuff);
        submit.setOnAction(event -> {
            try{
                emailerror.setText("");
                SendMail(email.getText(), msg.getText());
            }catch(ClassNotFoundException|SQLException ignored){
            }
        });
    }

    private void SendMail(String email, String tresc) throws ClassNotFoundException, SQLException{
        if(ValidateEmail(email, tresc)){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection("#####"); // REDACTED
            Statement stm=con.createStatement();
            stm.executeUpdate("insert into Opinie(otrzymano,od,tresc) values (getdate(),'"+email+"','"+tresc+"');");
            con.close();
        }
    }

    private boolean ValidateEmail(String email, String tresc){
        if(tresc.length()>200 || tresc.length()<2){
            emailerror.setText("Nieprawidlowa tresc.");
            return false;
        }
        if(!email.matches("[^@]+@[^\\.]+\\..+") || email.length()>50){
            emailerror.setText("Nieprawidlowy email.");
            return false;
        }
        return true;
    }

    @FXML
    private void initialize() throws Throwable{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("#####"); // REDACTED
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select * from Produkty");
        int i=0;
        while(result.next()){
            int value=(result.getInt(4));
            stany[i]=value;
            i++;
        }
        con.close();
    }

    private String getNazwa(int id) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("#####"); // REDACTED
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select * from Produkty where id='"+id+"'");
        result.next();
        String wynik=result.getString(2);
        con.close();
        return wynik;
    }

    private float getCena(int id) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("#####"); // REDACTED
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select * from Produkty where id='"+id+"'");
        result.next();
        float wynik=result.getFloat(3);
        con.close();
        return wynik;
    }

    @FXML
    private void Back(){
        core.getChildren().clear();
        core.setAlignment(Pos.TOP_LEFT);
        core.getChildren().addAll(banner, categories);
        ProdReset();
    }

    @FXML
    private void Reset() throws Throwable{
        initialize();
        Back();
        koszyk.clear();
        total=0;
        prodcount=0;
        pln.setText(total+" PLN");
        count.setText(prodcount+" sztuk");
    }

    @FXML
    private void Faktura(){
        core.getChildren().clear();
        Label rozpiska=new Label();
        Button pay=new Button("Zaplac");
        pay.setOnAction(event -> {
            try{
                UpdateStan();
                Reset();
            }catch(Throwable ignore){
            }
        });
        if(!koszyk.isEmpty()){
            StringBuilder fvat=new StringBuilder();
            for(String nazwa : koszyk.keySet()){
                fvat.append(nazwa).append("      x").append(koszyk.get(nazwa).count).append("        ").append(koszyk.get(nazwa).cena*koszyk.get(nazwa).count).append("PLN\n");
                fvat.append("(").append(koszyk.get(nazwa).cena).append("PLN)\n");
            }
            fvat.append("\n").append("Suma: ").append(total).append("PLN\n");
            rozpiska.setText(fvat.toString());
            rozpiska.setAlignment(Pos.TOP_RIGHT);
            core.getChildren().addAll(rozpiska, pay);
        }else{
            rozpiska.setText("Koszyk jest pusty.");
            core.getChildren().add(rozpiska);
        }
        core.setAlignment(Pos.CENTER);
    }

    private void UpdateStan() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("#####"); // REDACTED
        Statement stm=con.createStatement();
        for(int i=1; i<=9; i++){
            stm.executeUpdate("update Produkty set stan="+stany[i-1]+" where id="+i+";");
        }
        con.close();
    }

    private void Buy(int i){
        if(stany[i]>0){
            AddToCart(ceny[i%3], nazwy[i%3]);
            stany[i]--;
            switch(i%3){
                case 0:
                    stan1.setText(Integer.toString(stany[i]));
                    break;
                case 1:
                    stan2.setText(Integer.toString(stany[i]));
                    break;
                case 2:
                    stan3.setText(Integer.toString(stany[i]));
                    break;
            }
        }
    }

    private void AddToCart(float cena, String nazwa){
        if(koszyk.containsKey(nazwa)){
            Produkt tmp=koszyk.get(nazwa);
            tmp.count++;
            koszyk.put(nazwa, tmp);
        }else{
            koszyk.put(nazwa, new Produkt(cena, 1));
        }
        total=total+cena;
        prodcount++;
        pln.setText(total+" PLN");
        count.setText(prodcount+" sztuk(i/a)");
    }
}
