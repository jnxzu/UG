package AdminPanel;

import Objects.Login.Login;
import Objects.Login.LoginCellController;
import Objects.User.User;
import Objects.User.UserCellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

public class AdminPanel{
    @FXML
    private ListView<User> userlist;
    @FXML
    private ListView<Login> loginlist;
    @FXML
    public HBox box;
    @FXML
    public Button addbutton;
    private ObservableList<User> users;
    private ObservableList<Login> logins;

    public AdminPanel(){
        users=FXCollections.observableArrayList();
        logins=FXCollections.observableArrayList();
    }

    private void ReloadList() throws ClassNotFoundException, SQLException{
        users.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select * from Uzytkownicy");
        while(result.next()){
            boolean perm;
            perm=result.getString(4).equals("A");
            User newuser=new User(result.getInt(1), result.getString(2), result.getString(3), perm);
            users.add(newuser);
        }
        con.close();
        userlist.setItems(users);
    }

    private void LoadLogins() throws ClassNotFoundException, SQLException{
        logins.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select L.lp, L.data, L.czas, U.login from Logowania L left join Uzytkownicy U on L.login = U.id order by L.lp desc");
        while(result.next()){
            Login newlogin = new Login(result.getInt(1),result.getString(2),result.getString(3),result.getString(4));
            logins.add(newlogin);
        }
        con.close();
        loginlist.setItems(logins);
    }

    @SuppressWarnings("Duplicates")
    private void initChanger(char mode, int id){
        box.getChildren().clear();
        TextField log=new TextField();
        log.setStyle("-fx-border-color: green");
        log.setPrefWidth(75);
        log.setPromptText("Login");
        TextField pw=new TextField();
        pw.setStyle("-fx-border-color: green");
        pw.setPromptText("Haslo");
        pw.setPrefWidth(75);
        HBox adm=new HBox();
        Text a=new Text("A");
        a.setStyle("-fx-fill: white");
        CheckBox admin=new CheckBox();
        adm.getChildren().addAll(a, admin);
        adm.setAlignment(Pos.CENTER);
        if(mode=='e'){
            Button zmien=new Button("Zmien");
            zmien.setTextFill(Paint.valueOf("white"));
            zmien.setStyle("-fx-background-color: green");
            zmien.setPrefWidth(75);
            zmien.setCursor(Cursor.HAND);
            box.getChildren().addAll(log, pw, adm, zmien);
            zmien.setOnAction(event -> {
                String loginvalue=log.getText();
                String pwvalue=pw.getText();
                log.setStyle("-fx-border-color: green");
                pw.setStyle("-fx-border-color: green");
                if(loginvalue.length()<3 || loginvalue.length()>6){
                    log.setStyle("-fx-border-color: red");
                }
                if(pwvalue.length()<3 || pwvalue.length()>6){
                    pw.setStyle("-fx-border-color: red");
                }
                if(pwvalue.length()>=3 && pwvalue.length()<=6 && loginvalue.length()>=3 && loginvalue.length()<=6){
                    try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
                        PreparedStatement test=con.prepareStatement("select * from Uzytkownicy where login=?");
                        test.setString(1,loginvalue);
                        ResultSet testresult=test.executeQuery();
                        if(!testresult.next()){
                            PreparedStatement stm=con.prepareStatement("update Uzytkownicy set login=?,haslo=?,upowaznienia=? where id=?");
                            stm.setInt(4, id);
                            stm.setString(1, loginvalue);
                            stm.setString(2, pwvalue);
                            if(admin.isSelected()){
                                stm.setString(3, "A");
                            }else{
                                stm.setString(3, "U");
                            }
                            stm.executeUpdate();
                            con.close();
                            ReloadList();
                            box.getChildren().clear();
                            box.getChildren().add(addbutton);
                        }
                        else{
                            log.setStyle("-fx-border-color: red");
                            con.close();
                        }
                    }catch(SQLException|ClassNotFoundException ignored){
                    }
                }
            });
        }else if(mode=='a'){
            Button add=new Button("Dodaj");
            add.setTextFill(Paint.valueOf("white"));
            add.setStyle("-fx-background-color: green;-fx-border-color: white");
            add.setPrefWidth(75);
            add.setCursor(Cursor.HAND);
            box.getChildren().addAll(log, pw, adm, add);
            add.setOnAction(event -> {
                String loginvalue=log.getText();
                String pwvalue=pw.getText();
                log.setStyle("-fx-border-color: green");
                pw.setStyle("-fx-border-color: green");
                if(loginvalue.length()<3 || loginvalue.length()>6){
                    log.setStyle("-fx-border-color: red");
                }
                if(pwvalue.length()<3 || pwvalue.length()>6){
                    pw.setStyle("-fx-border-color: red");
                }
                if(pwvalue.length()>=3 && pwvalue.length()<=6 && loginvalue.length()>=3 && loginvalue.length()<=6){
                    try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
                        PreparedStatement test=con.prepareStatement("select * from Uzytkownicy where login=?");
                        test.setString(1,loginvalue);
                        ResultSet testresult=test.executeQuery();
                        if(!testresult.next()){
                            PreparedStatement stm=con.prepareStatement("insert into Uzytkownicy(login, haslo, upowaznienia) values (?,?,?)");
                            stm.setString(1, loginvalue);
                            stm.setString(2, pwvalue);
                            if(admin.isSelected()){
                                stm.setString(3, "A");
                            }else{
                                stm.setString(3, "U");
                            }
                            stm.executeUpdate();
                            con.close();
                            ReloadList();
                            box.getChildren().clear();
                            box.getChildren().add(addbutton);
                        }
                        else{
                            log.setStyle("-fx-border-color: red");
                            con.close();
                        }
                    }catch(SQLException|ClassNotFoundException ignored){
                    }
                }
            });
        }
    }

    private void Add(){
        initChanger('a', 0);
    }

    public void initialize() throws SQLException, ClassNotFoundException{
        LoadLogins();
        ReloadList();
        addbutton.setOnAction(event -> Add());
        userlist.setCellFactory(userListView -> new ListCell<User>(){
            private HBox graphic;
            private UserCellController controller;

            {
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Objects/User/UserCell.fxml"));
                    graphic=loader.load();
                    controller=loader.getController();
                }catch(IOException ignored){
                }
            }

            @Override
            protected void updateItem(User user, boolean empty){
                super.updateItem(user, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    controller.setLogin(user.getLogin());
                    controller.setHaslo(user.getHaslo());
                    if(user.isAdmin()){
                        controller.setImage("/img/usericons/admin.png");
                    }else{
                        controller.setImage("/img/usericons/user.png");
                    }
                    controller.getDelbutton().setOnAction(event -> {
                        try{
                            Delete(user.getId());
                        }catch(SQLException|ClassNotFoundException ignored){
                        }
                    });
                    controller.getEditbutton().setOnAction(event -> Edit(user.getId()));
                    setGraphic(graphic);
                }
            }

            void Delete(int id) throws SQLException, ClassNotFoundException{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
                PreparedStatement pre=con.prepareStatement("delete from Logowania where login=?");
                pre.setInt(1,id);
                pre.executeUpdate();
                PreparedStatement stm=con.prepareStatement("delete from Uzytkownicy where id=?");
                stm.setInt(1, id);
                stm.executeUpdate();
                con.close();
                ReloadList();
            }

            void Edit(int id){
                initChanger('e', id);
            }
        });
        loginlist.setCellFactory(loginListView -> new ListCell<Login>(){
            private HBox graphic;
            private LoginCellController controller;

            {
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Objects/Login/LoginCell.fxml"));
                    graphic=loader.load();
                    controller=loader.getController();
                }catch(IOException ignored){
                }
            }

            @Override
            protected void updateItem(Login login, boolean empty){
                super.updateItem(login, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    controller.setLp(login.getLp());
                    controller.setData(login.getData());
                    controller.setCzas(login.getCzas());
                    controller.setLogin(login.getLogin());
                    setGraphic(graphic);
                }
            }
        });
    }
}
