package MVCPresentationLayer.Views;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DataInfraestructure.Update;

public class UserConfig {
    public static void show() throws HeadlessException, SQLException {
        String Options = "Escolha uma opção para alterar:\n\n" +
                "1 - Nome\n" +
                "2 - Email\n" +
                "3 - Senha\n" +
                "4 - Excluir conta\n" +
                "0 - Voltar\n\n";
        int userProfileOption = 3;
        while (userProfileOption != 0) {
            userProfileOption = Integer
                    .parseInt(JOptionPane.showInputDialog(null, Options, "Alteração", JOptionPane.QUESTION_MESSAGE));
            Update u = new Update();
            switch (userProfileOption) {
                case 0:
                    return;
                case 1:
                    u.User("Name");
                    break;
                case 2:
                    u.User("Email");
                    break;
                case 3:
                    u.User("Password");
                    break;
                case 4:
                    // Delete.Account();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção Inválida.\nSelecione uma opção do Menu",
                            "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}