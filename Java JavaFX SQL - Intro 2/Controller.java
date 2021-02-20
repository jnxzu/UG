import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.*;

public class Controller{
    @FXML
    private Label info;
    @FXML
    private VBox returner;
    @FXML
    private TextField id;

    @FXML
    private void wypozyczenie() throws ClassNotFoundException, SQLException{
        info.setText("");
        String out="id_roweru | kategoria | id_klienta\n";
        Query(out, "exec dbo.showall");
    }

    @FXML
    private void magazyn() throws SQLException, ClassNotFoundException{
        info.setText("");
        String out="kategoria | stan\n";
        Query(out, "select kategoria, count(all kategoria) as 'stan' from Rowery group by kategoria");
    }

    private void Query(String out, String s) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("#####"); // REDACTED
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery(s);
        ResultSetMetaData resultMeta=result.getMetaData();
        int kolumny=resultMeta.getColumnCount();
        while(result.next()){
            for(int i=1; i<=kolumny; i++){
                out=out+result.getString(i)+" | ";
            }
            out=out+"\n";
        }
        out=out+"\n";
        info.setText(out);
        stm.close();
    }

    @FXML
    private void oddaj(){
        info.setText("");
        returner.setVisible(true);
    }

    @FXML
    private void fin() throws ClassNotFoundException, SQLException{
        if(!id.getText().isEmpty()){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection("#####"); // REDACTED
            Statement stm=con.createStatement();
            stm.executeUpdate("delete from Wypozyczenia where rower="+id.getText()+";");
            stm.close();
        }
        returner.setVisible(false);
    }
}
