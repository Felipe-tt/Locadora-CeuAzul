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

    private void setVariables(int ID) {
        client.Name = Get.Row("Name", ID);
        client.CPF = Get.Row("CPF", ID);
        client.Email = Get.Row("Email", ID);
        client.Password = Get.Row("Password", ID);
        client.Type = Get.Row("Type", ID);
        client.Balance = Get.BalanceRow("Balance", ID);
        client.ID = ID;
    }

    private void resetVariables() {
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
            String loginEmail = JOptionPane.showInputDialog("Email do cliente: ");
            client.ID = client.Email.indexOf(loginEmail);
            int ID = GetByID.Client(loginEmail, "email");
            if (ID != -1 && !Validator.isNullOrBlank(loginEmail)) {
                while (true) {
                    if (Verificator.Password(Login.show(), ID)) {
                        stop = true;
                        setVariables(ID);
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
            resetVariables();
            while (!Validator.isFullName(client.Name)) {
                client.Name = JOptionPane.showInputDialog("Digite seu nome: ");
            }

            while (!Validator.isCPF(client.CPF)) {
                while (true) {
                    String userCPF = JOptionPane.showInputDialog("Digite seu CPF: ");
                    if (!Verificator.Exists(userCPF, "CPF")) {
                        client.CPF = userCPF;
                        break;
                    }
                }
            }

            while (!Validator.isEmail(client.Email)) {
                while (true) {
                    String userEmail = JOptionPane.showInputDialog("Digite seu Email: ");
                    if (!Verificator.Exists(userEmail, "Email")) {
                        client.Email = userEmail;
                        break;
                    }
                }
            }

            while (!Validator.isPassword(client.Password)) {
                client.Password = Login.show();
            }

            while (!Validator.isType(client.Type)) {
                client.Type = JOptionPane.showInputDialog("Tipo da Conta: (  Corrente (C) / Poupança (P)  )");
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

    public void userList() {
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