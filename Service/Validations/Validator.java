package Service.Validations;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import MVCPresentationLayer.Views.UserLogin;

public class Validator {
    public static boolean isNullOrBlank(String param) {
        return param == null || param.trim().length() == 0;
    }

    public static Boolean isType(String type) {
        if (isNullOrBlank(type)) {
            return false;
        }
        if (type.equals("C") || type.equals("c")) {
            type = "Corrente";
            return true;
        } else if (type.equals("P") || type.equals("p")) {
            type = "Poupança";
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Tipo de conta incorreto!", "Erro", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }

    public static String toType(String type) {
        if (type.equals("C") || type.equals("c")) {
            type = "Corrente";
        } else if (type.equals("P") || type.equals("p")) {
            type = "Poupança";
        }
        return type;
    }

    public static boolean isPassword(String password) {
        if (isNullOrBlank(password)) {
            return false;
        }
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{6,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return matcher.matches();
        } else {
            UserLogin.Show();
            return false;
        }
    }

    private static boolean ErrorMessage() {
        JOptionPane.showMessageDialog(null, "Nome inválido!", "Erro", JOptionPane.PLAIN_MESSAGE);
        return false;
    }
    public static boolean isFullName(String name) throws SQLException {
        if (isNullOrBlank(name)) {
            return false;
        }
        else if(name.length() < 3){
            return ErrorMessage();
        }
        String expression = "^[\\p{L} .'-]+$";
        if (name.matches(expression)) {

            return name.matches(expression);
        } else {
            
            return ErrorMessage();
        }
    }

    public static boolean isEmail(String email) {
        if (isNullOrBlank(email)) {
            return false;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if (m.matches()) {
            return m.matches();
        } else {
            JOptionPane.showMessageDialog(null, "Email inválido!", "Erro", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}
