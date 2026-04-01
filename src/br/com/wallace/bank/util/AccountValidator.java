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
        String cpfNumbers = cpf.replaceAll("[^0-9]", "");
        boolean isInvalidFormat = !cpf.matches("[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}");
        boolean isRepeated = cpfNumbers.matches("(\\d)\\1{10}");

        if (isInvalidFormat || isRepeated || !hasValidDigitsCpf(cpfNumbers)) {
            if (isInvalidFormat){
                System.out.println("Error: Invalid cpf, use the format (XXX.XXX.XXX-XX)\n");
                return null;
            }
            System.out.println("Error: Invalid cpf, try again.\n");
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

    public static Double balance(String amount)
    {
        try {
            double value = Double.parseDouble(amount);
            if (value <= 0) {
                System.out.println("Error: The deposit cannot be zero or negative.\n");
                return null;
            }
            else if (value > 10_000) {
                System.out.println("Error: The maximum deposit amount is 10_000.\n");
                return null;
            }
            else {
                return value;
            }
        }
        catch (NumberFormatException e){
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return null;
    }
}
