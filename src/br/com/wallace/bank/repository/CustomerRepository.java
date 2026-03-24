package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Customer;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.*;

public class CustomerRepository
{
    private static int counter = 1;

    public static void saveCustomer(Customer customer)
    {
        String fileName = customer.getCpf().replaceAll("[.-]", "") + ".txt";
        File file = new File("data/customers/" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){

            writer.write("Name: " + customer.getName() + "\n");
            writer.write("CPF: " + customer.getCpf() + "\n");
            writer.write("Birthdate: " + customer.getBirthData() + "\n");
            writer.write("Email: " + customer.getEmail() + "\n");
            writer.write("Phone number: " + customer.getPhoneNumber() + "\n");
            writer.write("State: " + customer.getAdress().getState() + "\n");
            writer.write("City: " + customer.getAdress().getCity() + "\n");
            writer.write("ZipCode: " + customer.getAdress().getZipCode() + "\n");
            writer.write("Monthly income: " + customer.getMonthlyIncome()+ "\n");
        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be save." + e.getMessage(), e);
        }
    }
}
