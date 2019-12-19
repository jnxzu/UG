package tud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {
    public static void LeagueTableUpdate(Connection con, String club, int played, int wins, int draws, int loses,
            int goals, int conceded, int points) throws SQLException {
        PreparedStatement stm = con.prepareStatement(
                "update LeagueTable set played = played + ?, wins = wins + ?, draws = draws + ?, loses = loses + ?, goals = goals + ?, conceded = conceded + ?, points = points + ? where club=(select clubId from ClubInfo where shortName=?)");
        stm.setString(8, club);
        stm.setInt(1, played);
        stm.setInt(2, wins);
        stm.setInt(3, draws);
        stm.setInt(4, loses);
        stm.setInt(5, goals);
        stm.setInt(6, conceded);
        stm.setInt(7, points);
        stm.executeUpdate();
        stm.close();
    }

    public static String DisplayLeagueTable(Connection con) throws SQLException {
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(
                "select ClubInfo.isChampion, ClubInfo.isPromoted, case when ClubInfo.fullName is null then ClubInfo.shortName else ClubInfo.fullName end, LeagueTable.played, LeagueTable.wins, LeagueTable.draws, LeagueTable.loses, LeagueTable.goals, LeagueTable.conceded, LeagueTable.points from LeagueTable left join ClubInfo on LeagueTable.club=ClubInfo.clubId order by LeagueTable.points desc");
        ResultSetMetaData resultMeta = result.getMetaData();
        int kolumny = resultMeta.getColumnCount();
        String resultString = " \t \tCLUB\t\t\t\tP\tW\tD\tL\tGF\tGA\tPTS\n";
        while (result.next()) {
            for (int i = 1; i <= kolumny; i++) {
                if (i == 1) {
                    if (result.getByte(i) == 1)
                        resultString += "C\t";
                    else
                        resultString += "\t";
                } else if (i == 2) {
                    if (result.getByte(i) == 1)
                        resultString += "P\t";
                    else
                        resultString += "\t";
                } else if (i == 3) {
                    String nazwa = result.getString(i);
                    if (nazwa.length() <= 8)
                        resultString += nazwa + "\t\t\t\t";
                    else if (nazwa.length() > 8 && nazwa.length() <= 15)
                        resultString += nazwa + "\t\t\t";
                    else if (nazwa.length() > 15 && nazwa.length() <= 23)
                        resultString += nazwa + "\t\t";
                    else if (nazwa.length() > 23)
                        resultString += nazwa + "\t";
                } else
                    resultString += result.getString(i) + "\t";
            }
            resultString += "\n";
        }
        stm.close();
        return resultString;
    }

    public static void Relegate(Connection con, String club) throws SQLException {
        PreparedStatement stm = con.prepareStatement(
                "delete from LeagueTable where club=(select clubId from ClubInfo where shortName=?)");
        stm.setString(1, club);
        stm.executeUpdate();
        stm.close();
    }

    public static void AddClub(Connection con, String shortName, String fullName) throws SQLException {
        PreparedStatement stm = con
                .prepareStatement("insert into ClubInfo(shortName, fullName, isChampion, isPromoted) values (?,?,0,0)");
        stm.setString(1, shortName);
        stm.setString(2, fullName);
        stm.executeUpdate();
        stm.close();
    }

    public static void Promote(Connection con, String club) throws SQLException {
        PreparedStatement promo = con.prepareStatement("update ClubInfo set isPromoted=1 where shortName=?");
        promo.setString(1, club);
        promo.executeUpdate();
        PreparedStatement stm = con.prepareStatement(
                "insert into LeagueTable values ((select clubId from ClubInfo where shortName=?),0,0,0,0,0,0,0)");
        stm.setString(1, club);
        stm.executeUpdate();
        stm.close();
    }

    public static void ResetTable(Connection con) throws SQLException {
        Statement stm = con.createStatement();
        stm.executeQuery(
                "update LeagueTable set played = 0, wins = 0, draws = 0, loses = 0, goals = 0, conceded = 0, points = 0");
        stm.close();
    }

    public static void Match(Connection con, String teamA, int scoreA, int scoreB, String teamB) throws SQLException {
        if (scoreA > scoreB) {
            LeagueTableUpdate(con, teamA, 1, 1, 0, 0, scoreA, scoreB, 3);
            LeagueTableUpdate(con, teamB, 1, 0, 0, 1, scoreB, scoreA, 0);
        }
        if (scoreB > scoreA) {
            LeagueTableUpdate(con, teamB, 1, 1, 0, 0, scoreB, scoreA, 3);
            LeagueTableUpdate(con, teamA, 1, 0, 0, 1, scoreA, scoreB, 0);
        }
        if (scoreA == scoreB) {
            LeagueTableUpdate(con, teamA, 1, 0, 1, 0, scoreA, scoreB, 1);
            LeagueTableUpdate(con, teamB, 1, 0, 1, 0, scoreB, scoreA, 1);
        }
    }

    public static void ChangeChampion(Connection con, String club) throws SQLException {
        Statement stm1 = con.createStatement();
        stm1.executeQuery("update ClubInfo set isChampion=0");
        PreparedStatement stm2 = con.prepareStatement(
                "update ClubInfo set isChampion=1 where shortName=?");
        stm2.setString(1, club);
        stm2.executeUpdate();
        stm1.close();
        stm2.close();
    }

    public static void NewSeason(Connection con, String champion, String[] promoted) throws SQLException {
        ResetTable(con);
        ChangeChampion(con, champion);

        PreparedStatement relegationName;
        Statement getRelegated = con.createStatement();
        ResultSet relegatedSet = getRelegated.executeQuery("select top 3 club from LeagueTable order by points asc");
        while (relegatedSet.next()) {
            relegationName = con.prepareStatement("select shortName from ClubInfo where clubId=?");
            relegationName.setString(1, relegatedSet.getString(1));
            ResultSet relNameSet = relegationName.executeQuery();
            relNameSet.next();
            Relegate(con, relNameSet.getString(1));
        }

        for (String club : promoted) {
            Promote(con, club);
        }
    }
}