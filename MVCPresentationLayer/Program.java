package MVCPresentationLayer;
import javax.swing.JOptionPane;

import MVCPresentationLayer.Controllers.ClientController;

class Program {
    public static void main(String[] args) {
        ClientController C = new ClientController();
        String loginOptions = "Escolha uma opção\n\n" +
                              "1 - Registre-se\n" +
                              "2 - Login\n" +
                              "0 - Sair\n\n";
        int userOption = 1;
        String Options = "Programa de Conta Corrente\n\n" +
                         "1 - Depositar Valor\n" +
                         "2 - Sacar Valor\n" +
                         "3 - Consultar Saldo\n" +
                         "4 - Listar Cliente\n" +
                         "5 - Configurações\n" +
                         "6 - Voltar\n" +
                         "0 - Finalizar\n\n" +
                         "Digite a opção desejada:\n";

        C.standardWindow(userOption, loginOptions);
        while (userOption != 0) {
            try {
                userOption = Integer.parseInt(JOptionPane.showInputDialog(null, Options));
                if (userOption == 0)
                    continue;
                switch (userOption) {
                    case 1:
                        C.userDeposit();
                        break;
                    case 2:
                        C.userWithdraw();
                        break;
                    case 3:
                        C.checkBalance();
                        break;
                    case 4:
                        C.userList();
                        break;
                    case 5:
                        C.userConfig();
                        break;
                    case 6:
                        C.standardWindow(userOption, Options);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Opção Inválida.\nSelecione uma opção do Menu",
                                "ERRO!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Algo deu errado.."+ex,
                        "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("# Fim do Programa #");

        // Override
        // Permite que uma subclasse forneça um método que
        // já é fornecido por uma de suas superclasses.

    }
}