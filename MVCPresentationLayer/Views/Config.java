package MVCPresentationLayer.Views;
import javax.swing.JOptionPane;

public class Config {
    public static void show(){
        String Options = "Escolha uma opção\n\n" +
        "1 - Alterar Nome\n" +
        "2 - Alterar Email\n" +
        "3 - Alterar Senha\n" +
        "0 - Voltar\n\n";
        int userProfileOption = 3;
        while (userProfileOption != 0) {
            userProfileOption = Integer.parseInt(JOptionPane.showInputDialog(null, Options, "Alteração", JOptionPane.QUESTION_MESSAGE));

            switch (userProfileOption) {
                case 0:
                    //Back
                case 1:
                    // NameUpdate();
                    break;
                case 2:
                    // EmailUpdate();
                    break;
                case 3:
                    // PasswordUpdate();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção Inválida.\nSelecione uma opção do Menu",
                            "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}