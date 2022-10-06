package Service.Verification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Verificator {
    public static boolean Exists(String Entity, String ColumnName) {
        // if (List.contains(Var)) {
        // JOptionPane.showMessageDialog(null, "Já existe uma conta com esse " + I +
        // "!", "Erro",
        // JOptionPane.PLAIN_MESSAGE);
        // return true;
        // } else {
        // return false;
        // }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
            Statement st = conn.createStatement();
            java.sql.ResultSet rs;
            rs = st.executeQuery(
                    "SELECT " + ColumnName + " FROM users WHERE users." + ColumnName + " = '" + Entity + "'");
            if (rs.next()) {
                String rsEntity = rs.getString("" + ColumnName + "");
                // ArrayList<String> Array = (ArrayList<String>) ((ResultSet)
                // st).getArray("'"+ColumnName+"'");
                if (rsEntity.equals(Entity)) {
                    // "'"+Entity+"'"
                    JOptionPane.showMessageDialog(null, "Já existe uma conta com esse " + ColumnName + "!", "Erro",
                            JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static boolean Password(String Password, int ID) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
            Statement st = conn.createStatement();
            java.sql.ResultSet rs;
            rs = st.executeQuery("SELECT PASSWORD FROM users WHERE users.ID = " + ID + "");
            rs.next();
            String rsPassword = rs.getString("Password");
            if (rsPassword.equals(Password)) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
}