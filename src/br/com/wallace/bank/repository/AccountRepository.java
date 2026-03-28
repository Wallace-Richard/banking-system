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

    public static void saveAccount(Account account, Customer customer)
    {
        File file = new File("data/accounts/active/" + account.getNumberAccount() + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Account number: "    + account.getNumberAccount() + "\n");
            writer.write("Customer's CPF: "    + account.getCustomer().getCpf() + "\n");
            writer.write("Account type: "      + account.getAccountType().getName().toUpperCase() + "\n");
            writer.write("Balance: "           + account.getBalance() + "\n");
            writer.write("Date when created: " + account.getDateCreated().format(formatter) + "\n");
            writer.write("Active: "            + account.isActive());
            saveAccountToCustomerFile(customer, account);
        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save." + e.getMessage(), e);
        }
    }

    public static void saveAccountToCustomerFile(Customer customer, Account account)
    {
        String fileName = customer.getCpf().replaceAll("[.-]", "") + ".txt";
        File file = new File("data/customers/" + fileName);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            if (customer.getAccountType() != null) {
                writer.println("Account number: " + account.getNumberAccount() + ", Type: " + customer.getAccountType().getName().toUpperCase());
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save to customer." + e.getMessage(), e);
        }
    }

    public static Account loadAccount(String numberAccount, String cpf)
    {
        Customer customer         = CustomerRepository.loadCustomer(cpf);
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
}
