package MVCPresentationLayer;

import javax.swing.JOptionPane;

import MVCPresentationLayer.Controllers.UserController;

public class UserOptions {
    public void Show() {
        Return Return = new Return();
        UserController U = new UserController();
        int userOption = 1;
        while (userOption != 0) {
            try {
                userOption = Integer.parseInt(JOptionPane.showInputDialog(null, Return.AccOptions(),
                        "Bem-vindo(a) " + U.getsetUser("Get", "Name", "") + "!", JOptionPane.QUESTION_MESSAGE));
                if (userOption == 0)
                    continue;
                switch (userOption) {
                    case 1:
                        U.userDeposit();
                        break;
                    case 2:
                        U.userWithdraw();
                        break;
                    case 3:
                        U.checkBalance();
                        break;
                    case 4:
                        U.userInfo();
                        break;
                    case 5:
                        U.userConfig();
                        break;
                    case 6:
                        U.showIndex(userOption, Return.LoginOptions(), U);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Opção Inválida.\nSelecione uma opção do Menu",
                                "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Algo deu errado.." + ex,
                        "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("# Fim do Programa #");
    }
}
