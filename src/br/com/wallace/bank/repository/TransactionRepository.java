package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Account;

import java.io.*;

public class TransactionRepository
{
    public TransactionRepository()
    {
        throw new UnsupportedOperationException("Repository class - cannot be instantiated");
    }

    public static void createStatement(Account account)
    {
        File file = new File("data/transactions/statement=" + account.getNumberAccount() + ".txt" );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("==================================================================\n");
            writer.write("BANKING SYSTEM - ACCOUNT STATEMENT: " + account.getNumberAccount() + "\n");
            writer.write("==================================================================\n\n");
            writer.write("==================================================================\n");
        }
        catch (IOException e){
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }
    }
}
