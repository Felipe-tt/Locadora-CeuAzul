package MVCPresentationLayer.Controllers;

import java.awt.HeadlessException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DataInfraestructure.Get;
import DataInfraestructure.GetByID;
import DataInfraestructure.Set;
import Domain.User;
import MVCPresentationLayer.Views.UserConfig;
import MVCPresentationLayer.Views.UserLogin;
import DataInfraestructure.MySqlConnection;
import Service.Validations.*;
import Service.Verification.Verificator;

public class UserController {
    User user = new User();

    private void setVariables(int ID) {
        user.Name = Get.Row("Name", ID);
        user.CPF = Get.Row("CPF", ID);
        user.Email = Get.Row("Email", ID);
        user.Password = Get.Row("Password", ID);    
        user.Type = Get.Row("Type", ID);
        user.Balance = Get.BalanceRow("Balance", ID);
        user.ID = ID;
    }

    public String getsetUser(String getorset, String Entity, String Change) { //change var fica vazia caso seja get
        if (getorset == "Get"){
            if (Entity == "Name") {
                return user.Name;
            } else if (Entity == "CPF") {
                return user.CPF;
            } else if (Entity == "Email") {
                return user.Email;
            } else if (Entity == "Type") {
                return user.Type;
            }
        }
        else if(getorset == "Set"){
            if (Entity == "Name") {
                user.Name = Change;
            } else if (Entity == "CPF") {
                user.CPF = Change;
            } else if (Entity == "Email") {
                user.Email = Change;
            } else if (Entity == "Type") {
                user.Type = Change;
            }
        }
        return "";
    }

    // private void resetVariables() {
    // user.Name = "";
    // user.CPF = "";
    // user.Email = "";
    // user.Password = "";
    // user.Type = "";
    // user.Balance = 0.0;
    // }

    void userLogin() {
        boolean stop = false;
        while (!stop) {
            String loginEmail = JOptionPane.showInputDialog(null, "Email do cliente: ", "Login",
                    JOptionPane.QUESTION_MESSAGE);
            user.ID = user.Email.indexOf(loginEmail);
            int ID = GetByID.Client(loginEmail, "email");
            if (ID != -1 && !Validator.isNullOrBlank(loginEmail)) {
                while (true) {
                    if (Verificator.Password(UserLogin.Show(), ID)) {
                        setVariables(ID);
                        stop = true;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não existe nenhuma conta com esse email!", "Erro",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void userRegister() {
        try {
            MySqlConnection dbl = new MySqlConnection();
            dbl.OpenDatabase();
            while (!Validator.isFullName(user.Name)) {
                user.Name = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Registro",
                        JOptionPane.QUESTION_MESSAGE);
            }

            while (!Validator.isCPF(user.CPF)) {
                while (true) {
                    String userCPF = JOptionPane.showInputDialog(null, "Digite seu CPF: ", "Registro",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!Verificator.Exists(userCPF, "CPF")) {
                        user.CPF = userCPF;
                        break;
                    }
                }
            }

            while (!Validator.isEmail(user.Email)) {
                while (true) {
                    String userEmail = JOptionPane.showInputDialog(null, "Digite seu Email: ", "Registro",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!Verificator.Exists(userEmail, "Email")) {
                        user.Email = userEmail;
                        break;
                    }
                }
            }

            while (!Validator.isPassword(user.Password)) {
                user.Password = UserLogin.Show();
            }

            while (!Validator.isType(user.Type)) {
                user.Type = JOptionPane.showInputDialog(null, "(  Corrente (C) / Poupança (P)  )", "Tipo da Conta",
                        JOptionPane.QUESTION_MESSAGE);
            }
            user.Type = Validator.toType(user.Type);
            dbl.ExecuteQuery(Set.Client(user.Name,
                    user.CPF,
                    user.Email,
                    user.Password,
                    user.Type,
                    user.Balance));
            System.out.println(GetByID.Client(user.Email, "email"));
            user.ID = +1;
            dbl.CloseDatabase();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado..\n" + ex,
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void userDeposit() {
        double valor = Double.parseDouble(
                JOptionPane.showInputDialog("Quanto deseja depositar? (Total: " + user.Balance + ")",
                        "Insira um valor"));
        if (valor > 0) {
            user.Balance = user.Balance + valor;
            Set.Balance(valor, GetByID.Client(user.Email, "email"), "+");
        } else {
            JOptionPane.showMessageDialog(null, "Você não pode depositar um valor menor que zero!", "Erro",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void userWithdraw() {
        double valor = Double.parseDouble(
                JOptionPane.showInputDialog("Quanto deseja sacar? (Total: R$" + user.Balance + ")",
                        "Insira um valor"));
        if (user.Balance >= valor && valor > 0) {
            user.Balance = user.Balance - valor;
            Set.Balance(valor, GetByID.Client(user.Email, "email"), "-");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "Erro", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Seu saldo é de: R$" + user.Balance, "Mensagem",
                JOptionPane.PLAIN_MESSAGE);
        ;
    }

    public void userInfo() {
        if (user.Name != null && !user.Name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código da conta: " + user.ID + "\n" +
                    "Nome: " + user.Name + "\n" +
                    "CPF: " + Validator.imprimeCPF(user.CPF) + "\n" +
                    "Saldo: R$" + user.Balance + "\n" +
                    "Tipo da conta: " + user.Type + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui uma conta!", "Erro", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
        }
    }

    public void showIndex(int userLoginOption, String loginOptions, UserController U) {
        while (userLoginOption != 2) {
            userLoginOption = Integer.parseInt(JOptionPane.showInputDialog(null, loginOptions,
                    "Conta Corrente", JOptionPane.QUESTION_MESSAGE));
            if (userLoginOption == 0)
                System.exit(0);
            switch (userLoginOption) {
                case 1:
                    U.userRegister();
                    break;
                case 2:
                    U.userLogin();
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opção Inválida.\nSelecione uma opção do Menu",
                            "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void userConfig() throws HeadlessException, SQLException {
        UserConfig.show();
    }
}