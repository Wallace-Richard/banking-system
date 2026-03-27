package br.com.wallace.bank.util;

import java.io.*;

public class NumberAccount
{
    private static final File file = new File("data/forms/number_account.txt");

    public static String generator()
    {
        String numberAccount = addAccountNumber();

        int soma = 0;
        for (int i = 0; i < numberAccount.length(); i++) {
            soma += numberAccount.charAt(i) - '0';
        }

        int checkDigit = (soma + 4) % 10;

        return numberAccount + "-" + checkDigit;
    }

    private static String addAccountNumber()
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            int numberAccount = Integer.parseInt(line);
            ++numberAccount;
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            line = String.format("%05d", numberAccount);
            writer.write(line);
            writer.close();

            return line;
        }
        catch (IOException e) {
            throw new RuntimeException("Error: Form could not be read!" + e.getMessage(), e);
        }
    }
}
