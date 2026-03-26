package br.com.wallace.bank.util;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.repository.CustomerRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.wallace.bank.util.CustomerValidator.hasValidDigitsCpf;

public class AccountValidator
{
    private AccountValidator()
    {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated\n");
    }

    public static String cpf(String cpf)
    {
        String regexCpf = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}";
        Pattern pattern = Pattern.compile(regexCpf);
        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) {
            System.out.println("Error: Invalid CPF. Use the format XXX.XXX.XXX-XX\n");
            return null;
        }

        String cpfNumbers = cpf.replaceAll("[^0-9]", "");

        if (cpfNumbers.matches("(\\d)\\1{10}")) {
            System.out.println("Error: Invalid CPF.\n");
            return null;
        }

        if (!hasValidDigitsCpf(cpfNumbers)) {
            System.out.println("Error: Invalid CPF.\n");
            return null;
        }

        if (CustomerRepository.existsByCpf(cpfNumbers)) {
            return cpf;
        }
        return null;
    }

    public static Double initialDeposit(AccountType type, String amount)
    {
        try {
            double value = Double.parseDouble(amount);
            if (value >= type.getInitialDeposit()){
                return value;
            }
            System.out.println("Error: Invalid initial deposit for this account type.\n");
        }
        catch (NumberFormatException e){
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return null;
    }
}
