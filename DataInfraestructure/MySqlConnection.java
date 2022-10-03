package DataInfraestructure;

// import java.beans.Statement;
// import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnection {
    public static String URL =
        "jdbc:mysql://localhost:3306/bank";

    private Connection dbconn = null;
    private java.sql.Statement sqlmgr = null;
    // private ResultSet resultsql = null;

    public void OpenDatabase(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            dbconn = DriverManager.getConnection(URL);
            System.out.println("Successfully connected to: "+ URL);
            sqlmgr = dbconn.createStatement();
        } catch(Exception ex){
            System.out.println("Error content: "+ ex.getMessage());
        }
    }

    public void CloseDatabase() throws SQLException{
        sqlmgr.close();
        dbconn.close();
    }

    public int ExecuteQuery(String sql){
        try {
            return sqlmgr.executeUpdate(sql); //CRUD
        } catch (Exception ex) {
            System.out.println("Error content: "+ ex.getMessage());
        }
        return -1;
    }









    // java.sql.Statement sqlSt; //runs SQL
    // String useSQL = new String("use bank")
    // String output;
    // ResultSet result;   //holds the output from the SQL
    //  String SQL = "select * from clients order by ID";
    // try {
    //     Class.forName("com.mysql.jdbc.Driver");
    //     String dbURL = "jdbs:mysql://localhost:3306/bank";
    //     Connection dbConnect = DriverManager.getConnection(dbURL, "root", "");
    //     sqlSt = dbConnect.createStatement();
    //     result = sqlSt.executeQuery(SQL);
    //     //result holds the output from the SQL
    //     while(result.next() != false){
    //         output = result.getString("Name") + " " + result.getString("Balance");
    //         System.out.println(output);
    //     }
    //     sqlSt.close();
    // } catch (ClassNotFoundException ex) {
    //     Logger.getLogger
    // }
}