package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Transaction;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class TransactionRepository
{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private TransactionRepository()
    {
        throw new UnsupportedOperationException("Repository class - cannot be instantiated");
    }

    public static void registerTransaction(Transaction transaction, Account account)
    {
        File file = new File("data/transactions/" + account.getNumberAccount() + "_statement.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){

            writer.write("[" + transaction.getTimestamp().format(formatter) + "] | "
                             + transaction.getType().name() + " | " + transaction.getType().getSignal()
                             + transaction.getAmount() + " | " + account.getBalance() + "\n");
            writer.write("==================================================================\n");
        }
        catch (IOException e){
            throw new RuntimeException("Error: statement data could not be loaded." + e.getMessage(), e);
        }
    }
}
