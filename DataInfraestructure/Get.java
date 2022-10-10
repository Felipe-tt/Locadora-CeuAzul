package DataInfraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Get{
    static String doQuery(String Query, String Column, int ID){
    try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
        Statement st = conn.createStatement();
        java.sql.ResultSet rs;
        rs = st.executeQuery(""+Query+""+ID+"");
        rs.next();
        String Index = rs.getString(Column);
        return Index;
        }catch(SQLException ex) { System.out.println(ex);}
        return Column;  
    }

    public static String Row(String Column, int ID){
        return Get.doQuery("SELECT "+Column+" FROM users WHERE ID = ", Column, ID);
    }

    public static Double BalanceRow(String Column, int ID){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
            Statement st = conn.createStatement();
            java.sql.ResultSet rs;
            rs = st.executeQuery("SELECT "+Column+" FROM users WHERE ID = "+ID+"");
            if(rs.next()){
                Double Balance = rs.getDouble("Balance");
                return Balance;
            }
            else{
                return 0.0;
            }
        }
            catch(SQLException ex) { System.out.println(ex);}
            return 0.0;
    }
}