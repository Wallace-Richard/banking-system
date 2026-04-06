package br.com.wallace.bank.repository;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Customer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccountRepository
{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private AccountRepository()
    {
        throw new UnsupportedOperationException("Repository class - cannot be instantiated");
    }

    public static void saveAccount(Account account)
    {
        File file = new File("data/accounts/active/" + account.getNumberAccount() + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Account number: "    + account.getNumberAccount() + "\n");
            writer.write("Customer's CPF: "    + account.getCustomer().getCpf() + "\n");
            writer.write("Account type: "      + account.getAccountType().getName().toUpperCase() + "\n");
            writer.write("Balance: "           + account.getBalance() + "\n");
            writer.write("Date when created: " + account.getDateCreated().format(formatter) + "\n");
            writer.write("Active: "            + account.isActive());
        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save." + e.getMessage(), e);
        }
    }

    public static void createStatement(Account account)
    {
        File file = new File("data/transactions/" + account.getNumberAccount() + "_statement.txt" );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("==================================================================\n");
            writer.write("BANKING SYSTEM - ACCOUNT STATEMENT: " + account.getNumberAccount() + "\n");
            writer.write("==================================================================\n\n");
            writer.write("==================================================================\n");
            writer.write("[" + account.getDateCreated().format(formatter) + "]" + " | INITIAL DEPOSIT | "
                             + "+" + account.getBalance() + " | " + account.getBalance() + "\n");
            writer.write("==================================================================\n");
        }
        catch (IOException e){
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }
    }

    public static void saveAccountToCustomerFile(Account account, Customer customer)
    {
        String fileName = customer.getCpf().replaceAll("[.-]", "") + ".txt";
        File file = new File("data/customers/" + fileName);

        if (existAccountNumber(file, account)) return;

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            if (customer.getAccountType() != null) {
                writer.println("Account number: " + account.getNumberAccount() + ", Type: " + customer.getAccountType().getName().toUpperCase());
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save to customer." + e.getMessage(), e);
        }
    }

    public static Account loadAccount(String numberAccount)
    {
        Customer customer         = null;
        AccountType accountType   = null;
        double balance            = 0;
        LocalDateTime dateCreated = null;
        boolean isActive          = true;

        File file = new File("data/accounts/active/" + numberAccount + ".txt");
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(": ", 2);
                String text = parts[0];
                String value = parts[1];

                switch (text){
                    case "Account type":
                        accountType = AccountType.valueOf(value);
                        break;
                    case "Customer's CPF":
                        customer = CustomerRepository.loadCustomer(value);
                        break;
                    case "Balance":
                        balance = Double.parseDouble(value.trim());
                        break;
                    case "Date when created":
                        dateCreated = LocalDateTime.parse(value, formatter);
                        break;
                }
            }

        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }
        return new Account(numberAccount, customer, accountType, balance, dateCreated, isActive);
    }

    public static String existsByAccount(String numberAccount)
    {
        File folder = new File("data/accounts/active");
        File[] files = folder.listFiles();

        if (files == null) return null;

        for (File file : files) {
            if (file.getName().contains(numberAccount)){
                return numberAccount;
            }
        }
        return null;
    }

    public static boolean existAccountNumber(File file, Account account)
    {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                if (!line.contains(":")) continue;

                String[] parts = line.split(": ", 2);
                String text = parts[0];
                String value = parts[1];

                if (text.equals("Account number")) {
                    String[] partsNumber = value.split(", ", 2);
                    String textType = partsNumber[0];
                    if (textType.equals(account.getNumberAccount().trim())) {
                        return true;
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }
        return false;
    }


    public static String addAccountNumber()
    {
        File file = new File("data/forms/number_account.txt");
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

