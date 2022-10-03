package MVCPresentationLayer.Views;
import javax.swing.JOptionPane;

import MVCPresentationLayer.Controllers.ClientController;

public class Config {
    public static void show(){
        ClientController C = new ClientController();
        String Options = "Escolha uma opção\n\n" +
        "1 - Alterar Nome\n" +
        "2 - Alterar Email\n" +
        "3 - Alterar Senha\n" +
        "0 - Voltar\n\n" +
        ", Perfil do Usuário";
        int userProfileOption = 0;
        while (userProfileOption != 0) {
            userProfileOption = Integer.parseInt(JOptionPane.showInputDialog(null, 
            Options, JOptionPane.QUESTION_MESSAGE));

            if (userProfileOption == 0)
            switch (userProfileOption) {
                case 1:
                    // userRegister();
                    break;
                case 2:
                    // userLogin();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção Inválida.\nSelecione uma opção do Menu",
                            "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
