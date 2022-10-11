package MVCPresentationLayer;

public class Return {
    public String LoginOptions() {
        String loginOptions = "Escolha uma opção\n\n" +
                "1 - Registre-se\n" +
                "2 - Login\n" +
                "0 - Sair\n\n";
        return loginOptions;
    }
    public String AccOptions() {
        String accOptions = "Programa de Conta Corrente\n\n" +
                "1 - Depositar Valor\n" +
                "2 - Sacar Valor\n" +
                "3 - Consultar Saldo\n" +
                "4 - Informações da Conta\n" +
                "5 - Configurações\n" +
                "6 - Voltar\n" +
                "0 - Finalizar\n\n" +
                "Digite a opção desejada:\n";
        return accOptions;
    }
}
