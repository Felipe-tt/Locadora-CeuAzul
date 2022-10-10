package MVCPresentationLayer;

import javax.swing.JOptionPane;

import Domain.User;
import MVCPresentationLayer.Controllers.UserController;

class Program {
    public static void main(String[] args) {
        UserController U = new UserController();
        User u = new User();
        String loginOptions = "Escolha uma opção\n\n" +
                "1 - Registre-se\n" +
                "2 - Login\n" +
                "0 - Sair\n\n";
        int userOption = 1;
        String accOptions = "Programa de Conta Corrente\n\n" +
                "1 - Depositar Valor\n" +
                "2 - Sacar Valor\n" +
                "3 - Consultar Saldo\n" +
                "4 - Informações da Conta\n" +
                "5 - Configurações\n" +
                "6 - Voltar\n" +
                "0 - Finalizar\n\n" +
                "Digite a opção desejada:\n";

        UserController.showIndex(userOption, loginOptions, U);
        while (userOption != 0) {
            try {
                userOption = Integer.parseInt(JOptionPane.showInputDialog(null, accOptions, "Bem-vindo(a) "+U.getUser("Name")+"!", JOptionPane.QUESTION_MESSAGE));
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
                        UserController.showIndex(userOption, loginOptions, U);
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

        // Override
        // Permite que uma subclasse forneça um método que
        // já é fornecido por uma de suas superclasses.

    }
}