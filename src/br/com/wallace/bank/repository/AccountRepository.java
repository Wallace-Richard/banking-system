package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.util.NumberAccount;

import java.io.*;

public class AccountRepository
{

    public static void saveAccount(Account account)
    {
        File file = new File("data/accounts/active/" + NumberAccount.generator());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            writer.write("Customer Cpf: " + account.getCustomer().getCpf() + "\n");
            writer.write("Account type: " + account.getAccountType().getName() + "\n");
            writer.write("initial deposit: " + account.getInitialDeposit() + "\n");

        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save." + e.getMessage(), e);
        }
    }


}
