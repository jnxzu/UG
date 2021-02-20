package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class MainController{
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane loginpane;
    @FXML
    private TextField usr;
    @FXML
    private PasswordField pwd;
    @FXML
    private Text info;
    @FXML
    private Button logout;
    @FXML
    public Text currentuser;

    @FXML
    public void Close(ActionEvent event){
        Node src=(Node) event.getSource();
        Stage current=(Stage) src.getScene().getWindow();
        current.close();
    }

    @FXML
    void Login() throws SQLException, ClassNotFoundException, IOException{
        char perm=Validate(usr.getText(),pwd.getText());
        if(perm=='A'){
            logout.setVisible(true);
            currentuser.setText(usr.getText());
            AnchorPane adminpanel = FXMLLoader.load(getClass().getResource("/AdminPanel/AdminPanel.fxml"));
            content.getChildren().clear();
            content.getChildren().add(adminpanel);
        }
        else if(perm=='U'){
            logout.setVisible(true);
            currentuser.setText(usr.getText());
            AnchorPane userpanel = FXMLLoader.load(getClass().getResource("/UserPanel/UserPanel.fxml"));
            content.getChildren().clear();
            content.getChildren().add(userpanel);
        }
    }

    private char Validate(String usr, String pwd) throws ClassNotFoundException, SQLException{
        info.setText("");
        if(usr.length()>=3 && usr.length()<=6 && pwd.length()>=3 && pwd.length()<=6){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
            PreparedStatement stm=con.prepareStatement("select * from Uzytkownicy where login = ? and haslo = ?");
            stm.setString(1,usr);
            stm.setString(2,pwd);
            ResultSet result = stm.executeQuery();
            if(result.next()){
                StoreLogin(result.getInt(1));
                return result.getString("upowaznienia").charAt(0);
            }
            else{
                info.setText("Bledne dane.");
            }
            con.close();
        }
        else{
            info.setText("Nieprawidlowe dane.");
        }
        return 'F';
    }

    private void StoreLogin(int id) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        PreparedStatement stm=con.prepareStatement("insert into Logowania(data, czas, login) values (concat(" +
                "datepart(year,getdate()),'-'," +
                "datepart(month,getdate()),'-'," +
                "datepart(day,getdate()))," +
                "concat(datepart(hour,getdate()),':',datepart(minute,getdate())),?)");
        stm.setInt(1,id);
        stm.executeUpdate();
        con.close();
    }

    @FXML
    void Logout(){
        currentuser.setText("");
        logout.setVisible(false);
        usr.setText("");
        pwd.setText("");
        content.getChildren().clear();
        content.getChildren().add(loginpane);
    }
}
