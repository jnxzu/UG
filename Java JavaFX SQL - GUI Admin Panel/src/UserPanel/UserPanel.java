package UserPanel;

import Objects.Player.Player;
import Objects.Player.PlayerCellController;
import Objects.Team.Team;
import Objects.Team.TeamCellController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.*;

@SuppressWarnings("Duplicates")
public class UserPanel{
    @FXML
    private ComboBox<String> procchoice;
    @FXML
    private Button proc;
    @FXML
    private ComboBox<String> querychoice;
    @FXML
    private Button query;
    @FXML
    private ComboBox<String> triggerchoice;
    @FXML
    private Button trigger;
    @FXML
    private ListView<Team> teamlist;
    @FXML
    private ListView<Player> playerlist;
    private ObservableList<Team> druzyny;
    private ObservableList<Player> pilkarze;
    private ObservableList<String> teams;
    private ObservableList<String> countries;

    public UserPanel(){
        druzyny=FXCollections.observableArrayList();
        pilkarze=FXCollections.observableArrayList();
        teams=FXCollections.observableArrayList();
        countries=FXCollections.observableArrayList();
    }

    @FXML
    private void ReloadLists() throws ClassNotFoundException, SQLException{
        druzyny.clear();
        pilkarze.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        Statement stm1=con.createStatement();
        ResultSet result1=stm1.executeQuery("select * from Druzyny");
        while(result1.next()){
            Team newteam=new Team(result1.getInt(1), result1.getString(2), result1.getString(3), result1.getString(4));
            druzyny.add(newteam);
        }
        Statement stm2=con.createStatement();
        ResultSet result2=stm2.executeQuery("select * from Pilkarze");
        while(result2.next()){
            Player newplayer=new Player(result2.getInt(1), result2.getString(2), result2.getString(3), result2.getInt(4), result2.getString(5),  result2.getInt(7));
            pilkarze.add(newplayer);
        }
        con.close();
        teamlist.setItems(druzyny);
        playerlist.setItems(pilkarze);
    }

    private void LoadTeams() throws ClassNotFoundException, SQLException{
        teams.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        Statement stm=con.createStatement();
        ResultSet result=stm.executeQuery("select * from Druzyny");
        while(result.next()){
            teams.add(result.getString(2));
        }
        con.close();
        querychoice.setItems(teams);
        triggerchoice.setItems(teams);
    }

    private void LoadCountries() throws SQLException, ClassNotFoundException{
        countries.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        Statement stm1=con.createStatement();
        ResultSet result1=stm1.executeQuery("select * from Druzyny");
        while(result1.next()){
            if(countries.contains(result1.getString(4))) continue;
            countries.add(result1.getString(4));
        }
        Statement stm2=con.createStatement();
        ResultSet result2=stm2.executeQuery("select * from Pilkarze");
        while(result2.next()){
            if(countries.contains(result2.getString(5))) continue;
            countries.add(result2.getString(5));
        }
        con.close();
        procchoice.setItems(countries);
    }

    public void initialize() throws SQLException, ClassNotFoundException{
        ReloadLists();
        LoadTeams();
        LoadCountries();
        querychoice.setOnAction(choiceevent -> query.setOnAction(buttoneven -> {
            try{
                Query(querychoice.getValue());
            }catch(ClassNotFoundException|SQLException ignored){
            }
            teamlist.setItems(druzyny);
            playerlist.setItems(pilkarze);
        }));
        procchoice.setOnAction(choiceevent -> proc.setOnAction(buttonevent -> {
            try{
                Procedure(procchoice.getValue());
            }catch(ClassNotFoundException|SQLException ignored){
            }
            teamlist.setItems(druzyny);
            playerlist.setItems(pilkarze);
        }));
        triggerchoice.setOnAction(choiceevent -> trigger.setOnAction(buttoneven -> {
            try{
                Trigger(triggerchoice.getValue());
            }catch(ClassNotFoundException|SQLException e){
                System.out.println(e);
            }
        }));
        teamlist.setCellFactory(teamListView -> new ListCell<Team>(){
            private HBox graphic;
            private TeamCellController controller;

            {
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Objects/Team/TeamCell.fxml"));
                    graphic=loader.load();
                    controller=loader.getController();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Team team, boolean empty){
                super.updateItem(team, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    controller.setKlub(team.getNazwa());
                    controller.setFlag(team.getKraj());
                    controller.setMiasto(team.getMiasto());
                    setGraphic(graphic);
                }
            }
        });
        playerlist.setCellFactory(playerListView -> new ListCell<Player>(){
            private HBox graphic;
            private PlayerCellController controller;

            {
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/Objects/Player/PlayerCell.fxml"));
                    graphic=loader.load();
                    controller=loader.getController();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Player player, boolean empty){
                super.updateItem(player, empty);
                if(empty){
                    setGraphic(null);
                }else{
                    controller.setImie(player.getImie());
                    controller.setNazwisko(player.getNazwisko());
                    controller.setWiek(player.getWiek());
                    controller.setFlag(player.getKraj());
                    controller.setWartosc(player.getWartosc());
                    setGraphic(graphic);
                }
            }
        });
    }

    private void Query(String str) throws ClassNotFoundException, SQLException{
        druzyny.clear();
        pilkarze.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        PreparedStatement stm1=con.prepareStatement("select * from Druzyny where nazwa=?");
        stm1.setString(1, str);
        ResultSet result1=stm1.executeQuery();
        while(result1.next()){
            Team newteam=new Team(result1.getInt(1), result1.getString(2), result1.getString(3), result1.getString(4));
            druzyny.add(newteam);
        }
        PreparedStatement stm2=con.prepareStatement("select * from Pilkarze P inner join Druzyny D on P.klub = D.id where D.nazwa=?");
        stm2.setString(1, str);
        ResultSet result2=stm2.executeQuery();
        while(result2.next()){
            Player newplayer=new Player(result2.getInt(1), result2.getString(2), result2.getString(3), result2.getInt(4), result2.getString(5), result2.getInt(7));
            pilkarze.add(newplayer);
        }
        con.close();
    }

    private void Procedure(String str) throws ClassNotFoundException, SQLException{
        druzyny.clear();
        pilkarze.clear();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        PreparedStatement stm=con.prepareStatement("exec dbo.Kraj @kraj=?");
        stm.setString(1, str);
        boolean result=stm.execute();
        while(true){
            if(result){
                ResultSet rs=stm.getResultSet();
                while(rs.next()){
                    if(rs.getMetaData().getColumnCount()<5){
                        Team newteam=new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                        druzyny.add(newteam);
                    }
                    else{
                        Player newplayer=new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(7));
                        pilkarze.add(newplayer);
                    }
                }
                rs.close();
            }else{
                if(stm.getUpdateCount()==-1){
                    break;
                }
            }
            result=stm.getMoreResults();
        }
    }

    private void Trigger(String str) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-63STB1N;database=Projekt", "jnxzu", "password");
        PreparedStatement stm=con.prepareStatement("delete from Druzyny where nazwa=?");
        stm.setString(1,str);
        stm.executeUpdate();
        con.close();
        LoadTeams();
        LoadCountries();
        ReloadLists();
    }
}