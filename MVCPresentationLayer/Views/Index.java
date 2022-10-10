// package MVCPresentationLayer.Views;

// import javax.swing.JOptionPane;

// import MVCPresentationLayer.Controllers.UserController;

// public class Index {
//     public static void Show(int userLoginOption, String loginOptions) {
//         UserController C = new UserController();
//         while (userLoginOption != 2) {
//             userLoginOption = Integer.parseInt(JOptionPane.showInputDialog(null, loginOptions,
//                     "Conta Corrente", JOptionPane.QUESTION_MESSAGE));
//             if (userLoginOption == 0)
//                 System.exit(0);
//             switch (userLoginOption) {
//                 case 1:
//                     C.userRegister();
//                     break;
//                 case 2:
//                     C.userLogin();
//                     break;
//                 default:
//                     JOptionPane.showMessageDialog(null,
//                             "Opção Inválida.\nSelecione uma opção do Menu",
//                             "ERRO!", JOptionPane.ERROR_MESSAGE);
//             }
//         }
//     }
// }
