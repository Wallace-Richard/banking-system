package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TransactionRepository
{
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TransactionRepository()
    {
        throw new UnsupportedOperationException("Repository class - cannot be instantiated");
    }

    public static void registerTransaction(Transaction transaction, Account account)
    {
        File file = new File("data/transactions/" + account.getNumberAccount() + "_statement.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            writer.write("[" + transaction.getTimestamp().format(formatterTime) + "] | " + transaction.getType().name() + " | " + transaction.getType().getSignal() + transaction.getAmount() + " | " + account.getBalance() + "\n");
            writer.write("==================================================================\n");
        }
        catch (IOException e) {
            throw new RuntimeException("Error: statement data could not be loaded." + e.getMessage(), e);
        }
    }

    public static double checkLimitWithdrawal(Account account)
    {
        File file = new File("data/transactions/" + account.getNumberAccount() + "_statement.txt");

        double limitWithdrawal = 0.0;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                if (!line.contains(" | ")) continue;

                String[] parts = line.split(" \\| ", 4);
                String part1 = parts[0].replace("[", "").replace("]", "");
                String part2 = parts[1];
                String part3 = parts[2];

                LocalDate date = LocalDate.parse(part1, formatterTime);

                if (!date.equals(LocalDate.now())) continue;
                if (!part2.equals("WITHDRAWAL")) continue;
                limitWithdrawal += Double.parseDouble(part3);
            }
            return limitWithdrawal;
        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }
    }
}
