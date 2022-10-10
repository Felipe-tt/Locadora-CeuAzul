package DataInfraestructure;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Service.Validations.Validator;

public class Update{
    public void SQLUpdate(){
        
    }

    public void Name() throws HeadlessException, SQLException{
        while (!Validator.isFullName("")) {
            String newUserName = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Alteração de Nome",
                    JOptionPane.QUESTION_MESSAGE);
        }


    }

    public void Email(){
        
    }

    public void Password(){
        
    }
    
}