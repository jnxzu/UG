package tud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class CRUDTests {
    @Test
    public void TestDisplay() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String result = CRUD.DisplayLeagueTable(con);
        assertNotNull(result);
        String[] lines = result.split("\r\n|\r|\n");
        assertTrue(lines.length==21);
        assertEquals(lines[0], " \t \tCLUB\t\t\t\tP\tW\tD\tL\tGF\tGA\tPTS");
        for(int i=1;i<lines.length;i++){
            assertTrue(lines[i].matches("^C?\\tP?\\t[0-9a-zA-Z ]+(\\t){1,4}([0-9]{1,3}\\t){7}"));
        }
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void TestUpdate() throws SQLException, ClassNotFoundException{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String[] before = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        String beforeChelsea ="";
        for (String line : before) {
            if(line.matches(".*Chelsea.*")) beforeChelsea = line;
        }
        CRUD.LeagueTableUpdate(con, "Chelsea", 1, 1, 0, 0, 3, 1, 3);
        String[] after = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        String afterChelsea="";
        for (String line : after) {
            if(line.matches(".*Chelsea.*")) afterChelsea = line;
        }
        assertNotEquals(beforeChelsea, afterChelsea);
        String beforeUnited="";
        for (String line : after) {
            if(line.matches(".*Manchester United.*")) beforeUnited = line;
        }
        CRUD.LeagueTableUpdate(con, "Man Utd", 0, 0, 0, 0, 0, 0, 0);
        String[] after2 = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        String afterUnited="";
        for (String line : after2) {
            if(line.matches(".*Manchester United.*")) afterUnited = line;
        }
        assertEquals(beforeUnited, afterUnited);
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void RelegatePromoteTest() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String[] linesbefore = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        boolean unitedNotRelegated = false;
        for (String club : linesbefore) {
            if(club.matches(".*Manchester United.*")) unitedNotRelegated=true;
        }
        assertTrue(unitedNotRelegated);
        CRUD.Relegate(con, "Man Utd");
        String[] linesafter = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        assertTrue(linesbefore.length==linesafter.length+1);
        for (String club : linesafter) {
            assertFalse(club.matches(".*Manchester United.*"));
        }
        CRUD.Promote(con, "Man Utd");
        String[] linesafter2 = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        unitedNotRelegated = false;
        for (String club : linesbefore) {
            if(club.matches(".*Manchester United.*")) unitedNotRelegated=true;
        }
        assertTrue(unitedNotRelegated);
        assertTrue(linesafter.length==linesafter2.length-1);
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void TestAddClubToDB() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String before="";
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery("select * from ClubInfo");
        ResultSetMetaData resultMeta = result.getMetaData();
        int kolumny = resultMeta.getColumnCount();
        while(result.next()){
            for(int i=1;i<=kolumny;i++){
                before+=result.getString(i)+" ";
            }
        }
        assertFalse(before.matches(".*Wimbledon.*"));
        CRUD.AddClub(con, "Wimbledon", null);
        String after="";
        Statement stm2 = con.createStatement();
        ResultSet result2 = stm2.executeQuery("select * from ClubInfo");
        ResultSetMetaData resultMeta2 = result2.getMetaData();
        int kolumny2 = resultMeta2.getColumnCount();
        while(result2.next()){
            for(int i=1;i<=kolumny2;i++){
                after+=result2.getString(i)+" ";
            }
        }
        assertNotEquals(before, after);
        assertTrue(after.matches(".*Wimbledon.*"));
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void TestReset() throws SQLException, ClassNotFoundException{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String[] before = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        boolean matchesPlayed = false;
        for(int i=1;i<before.length;i++){
            if(!before[i].matches("^C?\\tP?\\t[0-9a-zA-Z ]+(\\t){1,4}([0]{1}\\t){7}")) matchesPlayed=true;
        }
        assertTrue(matchesPlayed);
        CRUD.ResetTable(con);
        String[] after = CRUD.DisplayLeagueTable(con).split("\r\n|\r|\n");
        boolean tableSame = true;
        for(int i=1;i<after.length;i++){
            assertTrue(after[i].matches("^C?\\tP?\\t[0-9a-zA-Z ]+(\\t){1,4}([0]{1}\\t){7}"));
            if(!after[i].equals(before[i])) tableSame = false;
        }
        assertFalse(tableSame);
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void MatchTest() throws SQLException, ClassNotFoundException{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String teamAbefore = "";
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery("select * from LeagueTable where club=(select clubId from ClubInfo where shortName='Man Utd')");
        ResultSetMetaData resultMeta = result.getMetaData();
        int kolumny = resultMeta.getColumnCount();
        while(result.next()){
            for(int i=1;i<=kolumny;i++){
                teamAbefore+=result.getString(i)+" ";
            }
        }
        String teamBbefore = "";
        Statement stm2 = con.createStatement();
        ResultSet result2 = stm2.executeQuery("select * from LeagueTable where club=(select clubId from ClubInfo where shortName='Chelsea')");
        ResultSetMetaData resultMeta2 = result2.getMetaData();
        int kolumny2 = resultMeta2.getColumnCount();
        while(result2.next()){
            for(int i=1;i<=kolumny2;i++){
                teamBbefore+=result2.getString(i)+" ";
            }
        }
        CRUD.Match(con, "Chelsea", 4, 0, "Man Utd");
        String teamAafter = "";
        Statement stm3 = con.createStatement();
        ResultSet result3 = stm3.executeQuery("select * from LeagueTable where club=(select clubId from ClubInfo where shortName='Man Utd')");
        ResultSetMetaData resultMeta3 = result3.getMetaData();
        int kolumny3 = resultMeta3.getColumnCount();
        while(result3.next()){
            for(int i=1;i<=kolumny3;i++){
                teamAafter+=result3.getString(i)+" ";
            }
        }
        String teamBafter = "";
        Statement stm4 = con.createStatement();
        ResultSet result4 = stm4.executeQuery("select * from LeagueTable where club=(select clubId from ClubInfo where shortName='Chelsea')");
        ResultSetMetaData resultMeta4 = result4.getMetaData();
        int kolumny4 = resultMeta4.getColumnCount();
        while(result4.next()){
            for(int i=1;i<=kolumny4;i++){
                teamBafter+=result4.getString(i)+" ";
            }
        }
        assertFalse(teamAbefore.equals(teamAafter));
        assertFalse(teamBbefore.equals(teamBafter));
        con.close();
        assertTrue(con.isClosed());
    }

    @Test
    public void ChampionChangeTest() throws SQLException, ClassNotFoundException{
        Class.forName("org.hsqldb.jdbcDriver");
        Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");
        assertNotNull(con);
        String currentChamp = "";
        String currentChampClub = "";
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery("select * from ClubInfo where isChampion=1");
        ResultSetMetaData resultMeta = result.getMetaData();
        int kolumny = resultMeta.getColumnCount();
        while(result.next()){
            currentChampClub = result.getString(2);
            for(int i=1;i<=kolumny;i++){
                currentChamp+=result.getString(i)+" ";
            }
        }
        String nextChamp = "";
        Statement stm2 = con.createStatement();
        ResultSet result2 = stm2.executeQuery("select * from ClubInfo where shortName='Chelsea'");
        ResultSetMetaData resultMeta2 = result2.getMetaData();
        int kolumny2 = resultMeta2.getColumnCount();
        while(result2.next()){
            for(int i=1;i<=kolumny2;i++){
                nextChamp+=result2.getString(i)+" ";
            }
        }
        CRUD.ChangeChampion(con, "Chelsea");
        String oldChamp = "";
        Statement stm3 = con.createStatement();
        ResultSet result3 = stm3.executeQuery("select * from ClubInfo where shortName='"+currentChampClub+"'");
        ResultSetMetaData resultMeta3 = result3.getMetaData();
        int kolumny3 = resultMeta3.getColumnCount();
        while(result3.next()){
            for(int i=1;i<=kolumny3;i++){
                oldChamp+=result3.getString(i)+" ";
            }
        }
        String newChamp = "";
        Statement stm4 = con.createStatement();
        ResultSet result4 = stm4.executeQuery("select * from ClubInfo where shortName='Chelsea'");
        ResultSetMetaData resultMeta4 = result4.getMetaData();
        int kolumny4 = resultMeta4.getColumnCount();
        while(result4.next()){
            for(int i=1;i<=kolumny4;i++){
                newChamp+=result4.getString(i)+" ";
            }
        }
        assertFalse(currentChamp.equals(oldChamp));
        assertFalse(nextChamp.equals(newChamp));
        con.close();
        assertTrue(con.isClosed());
    }
}