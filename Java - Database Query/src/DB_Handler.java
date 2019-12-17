import java.sql.*;

public class DB_Handler{
    public static void main(String[] args){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con=DriverManager.getConnection("#####"); // REDACTED
            System.out.println("Połączono z bazą danych");

            z1(con);
            z2(con, "Sopot");
            z3(con);
            z4(con);

            con.close();
        }catch(SQLException|ClassNotFoundException rip){
        }
    }

    static void printsql(Connection con, String sql) throws SQLException{
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(sql);
        ResultSetMetaData resultMeta = result.getMetaData();
        int kolumny = resultMeta.getColumnCount();

        while(result.next()){
            for(int i=1;i<=kolumny;i++){
                System.out.print(result.getString(i)+" | ");
            }
            System.out.println();
        }
        System.out.println();
        stm.close();
    }

    static void z1(Connection con) throws SQLException{
        String sql = "select * from t1;";
        printsql(con, sql);
        sql = "select * from t2;";
        printsql(con, sql);
    }

    static void z2(Connection con, String miasto) throws SQLException{
        String sql = "select t1.nazwisko, DATEDIFF(year,t1.data_urodzenia,getdate()), t2.miasto from t1 inner join t2 on t1.nazwisko=t2.nazwisko where t2.miasto='"+miasto+"';";
        printsql(con, sql);
    }

    static void z3(Connection con) throws SQLException{
        String sql = "select nazwisko from t1 where data_urodzenia=(select max(data_urodzenia) from t1);";
        printsql(con,sql);
    }

    static void z4(Connection con) throws SQLException{
        String sql = "select miasto, count(distinct lp) from t2 group by miasto;";
        printsql(con, sql);
    }
}