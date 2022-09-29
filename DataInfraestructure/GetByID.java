package DataInfraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetByID{
    public static Integer Client(String iVariable, String Column){
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
        Statement st = conn.createStatement();
        java.sql.ResultSet rs;
        rs = st.executeQuery("SELECT ID FROM clients WHERE clients."+Column+"='"+iVariable+"'");
        if(rs.next()){
            int userid = rs.getInt("ID");
            return userid;
        }
        else{
            return -1;
        }
        }catch(SQLException ex) { System.out.println(ex);}
        return null;
    }

}