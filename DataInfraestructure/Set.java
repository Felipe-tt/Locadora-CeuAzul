package DataInfraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Set{
    public static String Client(String iNome, String iCPF, String iEmail, String iPassword, String iType, Double iBalance){
        String sql = "INSERT INTO clients (Name, CPF, Email, Password, Type, Balance) "+
                                "VALUES ('"+iNome+"',"+
                                       "'"+iCPF+"',"+
                                       "'"+iEmail+"',"+
                                       "'"+iPassword+"',"+
                                       "'"+iType+"',"+
                                           iBalance+");";
        return sql;
    }

    public static void Balance(Double Value, Integer ID, String MoreOrLess){
        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
        Statement st = conn.createStatement();
        st.executeUpdate("UPDATE clients SET Balance = Balance "+MoreOrLess+" "+Value+" WHERE ID = "+ID+"");
        }catch(SQLException ex) { System.out.println(ex);}
    }


}