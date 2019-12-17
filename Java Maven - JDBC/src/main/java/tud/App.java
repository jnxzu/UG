package tud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        System.out.println(CRUD.DisplayLeagueTable(con));
        con.close();
    }
}
