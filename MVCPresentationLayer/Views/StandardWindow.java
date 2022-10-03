package MVCPresentationLayer.Views;

import javax.swing.JOptionPane;

import MVCPresentationLayer.Controllers.ClientController;

public class StandardWindow {
    public static void show(int userOption) {
        ClientController c = new ClientController();
        String loginOptions = "Escolha uma opção\n\n" +
                              "1 - Registre-se\n" +
                              "2 - Login\n" +
                              "0 - Sair\n\n";
        while (userOption != 2) {
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, loginOptions,
                    "Conta Corrente", JOptionPane.QUESTION_MESSAGE));
            if (userOption == 0)
                System.exit(0);
            switch (userOption) {
                case 1:
                    c.userRegister();
                    break;
                case 2:
                    c.userLogin();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção Inválida.\nSelecione uma opção do Menu",
                            "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
