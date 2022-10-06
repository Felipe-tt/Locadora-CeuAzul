package MVCPresentationLayer.Controllers;

import javax.swing.JOptionPane;

import DataInfraestructure.Get;
import DataInfraestructure.GetByID;
import DataInfraestructure.Set;
import Domain.Client;
import MVCPresentationLayer.Views.Config;
import MVCPresentationLayer.Views.Login;
import DataInfraestructure.MySqlConnection;
import Service.Validations.*;
import Service.Verification.Verificator;

public class ClientController {
    Client client = new Client();

    public void setVariables(int ID) {
        client.Name = Get.Row("Name", ID);
        client.CPF = Get.Row("CPF", ID);
        client.Email = Get.Row("Email", ID);
        client.Password = Get.Row("Password", ID);
        client.Type = Get.Row("Type", ID);
        client.Balance = Get.BalanceRow("Balance", ID);
        client.ID = ID;
    }

    public void resetVariables() {
        client.Name = "";
        client.CPF = "";
        client.Email = "";
        client.Password = "";
        client.Type = "";
        client.Balance = 0.0;
    }

    public void userLogin() {
        boolean stop = false;
        while (!stop) {
            String loginEmail = JOptionPane.showInputDialog(null, "Email do cliente: ", "Login",
                    JOptionPane.QUESTION_MESSAGE);
            client.ID = client.Email.indexOf(loginEmail);
            int ID = GetByID.Client(loginEmail, "email");
            if (ID != -1 && !Validator.isNullOrBlank(loginEmail)) {
                while (true) {
                    if (Verificator.Password(Login.Show(), ID)) {
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

    public void userRegister() {
        try {
            MySqlConnection dbl = new MySqlConnection();
            dbl.OpenDatabase();
            while (!Validator.isFullName(client.Name)) {
                client.Name = JOptionPane.showInputDialog(null, "Digite seu nome: ", "Registro",
                        JOptionPane.QUESTION_MESSAGE);
            }

            while (!Validator.isCPF(client.CPF)) {
                while (true) {
                    String userCPF = JOptionPane.showInputDialog(null, "Digite seu CPF: ", "Registro",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!Verificator.Exists(userCPF, "CPF")) {
                        client.CPF = userCPF;
                        break;
                    }
                }
            }

            while (!Validator.isEmail(client.Email)) {
                while (true) {
                    String userEmail = JOptionPane.showInputDialog(null, "Digite seu Email: ", "Registro",
                            JOptionPane.QUESTION_MESSAGE);
                    if (!Verificator.Exists(userEmail, "Email")) {
                        client.Email = userEmail;
                        break;
                    }
                }
            }

            while (!Validator.isPassword(client.Password)) {
                client.Password = Login.Show();
            }

            while (!Validator.isType(client.Type)) {
                client.Type = JOptionPane.showInputDialog(null, "(  Corrente (C) / Poupança (P)  )", "Tipo da Conta",
                        JOptionPane.QUESTION_MESSAGE);
            }
            client.Type = Validator.toType(client.Type);
            dbl.ExecuteQuery(Set.Client(client.Name,
                    client.CPF,
                    client.Email,
                    client.Password,
                    client.Type,
                    client.Balance));
            System.out.println(GetByID.Client(client.Email, "email"));
            client.ID = +1;
            dbl.CloseDatabase();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Algo deu errado..\n" + ex,
                    "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void userDeposit() {
        double valor = Double.parseDouble(
                JOptionPane.showInputDialog("Quanto deseja depositar? (Total: " + client.Balance + ")",
                        "Insira um valor"));
        if (valor > 0) {
            client.Balance = client.Balance + valor;
            Set.Balance(valor, GetByID.Client(client.Email, "email"), "+");
        } else {
            JOptionPane.showMessageDialog(null, "Você não pode depositar um valor menor que zero!", "Erro",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void userWithdraw() {
        double valor = Double.parseDouble(
                JOptionPane.showInputDialog("Quanto deseja sacar? (Total: R$" + client.Balance + ")",
                        "Insira um valor"));
        if (client.Balance >= valor && valor > 0) {
            client.Balance = client.Balance - valor;
            Set.Balance(valor, GetByID.Client(client.Email, "email"), "-");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "Erro", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void checkBalance() {
        JOptionPane.showMessageDialog(null, "Seu saldo é de: R$" + client.Balance, "Mensagem",
                JOptionPane.PLAIN_MESSAGE);
        ;
    }

    public void userInfo() {
        if (client.Name != null && !client.Name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código da conta: " + client.ID + "\n" +
                    "Nome: " + client.Name + "\n" +
                    "CPF: " + Validator.imprimeCPF(client.CPF) + "\n" +
                    "Saldo: R$" + client.Balance + "\n" +
                    "Tipo da conta: " + client.Type + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "Você não possui uma conta!", "Erro", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
        }
    }

    

    public void userConfig() {
        Config.show();
    }
}