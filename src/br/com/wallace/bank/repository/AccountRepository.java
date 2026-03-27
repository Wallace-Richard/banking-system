package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.util.NumberAccount;

import java.io.*;

public class AccountRepository
{

    public static void saveAccount(Account account, Customer customer)
    {
        String numberAccount = NumberAccount.generator();
        saveAccountToCustomerFile(customer, numberAccount);
        File file = new File("data/accounts/active/" + numberAccount + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            writer.write("Customer Cpf: "    + account.getCustomer().getCpf() + "\n");
            writer.write("Account type: "    + account.getAccountType().getName() + "\n");
            writer.write("initial deposit: " + account.getInitialDeposit() + "\n");

        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save." + e.getMessage(), e);
        }
    }

    public static void saveAccountToCustomerFile(Customer customer, String numberAccount)
    {
        String fileName = customer.getCpf().replaceAll("[.-]", "") + ".txt";
        File file = new File("data/customers/" + fileName);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            if (customer.getAccountType() != null){
                writer.println("Account number: " + numberAccount + ", Type: " + customer.getAccountType().getName());
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: account data could not be save to customer." + e.getMessage(), e);
        }
    }
}
