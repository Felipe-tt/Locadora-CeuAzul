package DataInfraestructure;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Service.Validations.Validator;

public class Update {
    Domain.User user = new Domain.User();
    public void SQLUpdate(String Column, String ChangeTo, String OldEntity) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank");
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE users SET " + Column + " = '" + ChangeTo + "'' WHERE " + Column + " = '" + OldEntity + "'");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public String UserUpdateQuestion(String FieldToUpdate, String Column) {
        FieldToUpdate = JOptionPane.showInputDialog(null, "Digite seu "+Column+": ", "Alteração de "+Column+"",JOptionPane.QUESTION_MESSAGE);
        return FieldToUpdate;
    }

    public void User(String Column) throws HeadlessException, SQLException {
        String OldUserEntity = user.getsetUser("Get", Column, "");
        String UserEntity = "";
        if (Column == "Name") {
            while (!Validator.isFullName(UserEntity)) {
                UserUpdateQuestion(UserEntity, Column);
            }
        } else if (Column == "Email") {
            while (!Validator.isEmail(UserEntity)) {
                UserUpdateQuestion(UserEntity, Column);
            }
        } else if (Column == "Password") {
            while (!Validator.isPassword(UserEntity)) {
                UserUpdateQuestion(UserEntity, Column);
            }
        }
        user.getsetUser("Set", Column, UserEntity);
        SQLUpdate(Column, UserEntity, OldUserEntity);
    }

}