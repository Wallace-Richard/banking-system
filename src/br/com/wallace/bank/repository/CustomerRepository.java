package br.com.wallace.bank.repository;

import br.com.wallace.bank.model.Address;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.util.FormReader;
import br.com.wallace.bank.util.InputReader;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class CustomerRepository
{
    public static void saveCustomer(Customer customer)
    {
        String fileName = customer.getCpf().replaceAll("[.-]", "") + ".txt";
        File file = new File("data/customers/" + fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write("Name: " + customer.getName() + "\n");
            writer.write("CPF: " + customer.getCpf() + "\n");
            writer.write("Birthdate: " + customer.getBirthData()+ "\n");
            writer.write("Email: " + customer.getEmail() + "\n");
            writer.write("Phone number: " + customer.getPhoneNumber() + "\n");
            writer.write("State: " + customer.getAdress().getState() + "\n");
            writer.write("City: " + customer.getAdress().getCity() + "\n");
            writer.write("ZipCode: " + customer.getAdress().getZipCode() + "\n");
            writer.write("Monthly income: " + customer.getMonthlyIncome() + "\n");
        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be save." + e.getMessage(), e);
        }
    }

    public static boolean existsByCpf(String cpf)
    {
        File folder = new File("data/customers");
        File[] files = folder.listFiles();

        if (files == null) return false;

        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains(cpf)) {
                return true;
            }
        }
        return false;
    }

    public static Customer findByCPF(String cpfStr)
    {
        String name = null;
        String cpf = cpfStr;
        LocalDate birthDate = null;
        String email = null;
        String phoneNumber = null;
        String state = null;
        String city = null;
        String zipCode = null;
        double monthlyIncome = 0;

        String cpfNumbers = cpfStr.replaceAll("[^0-9]", "");
        File file = new File("data/customers/" + cpfNumbers + ".txt");
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            while ((line = reader.readLine()) != null){
                if (!line.contains(":")) continue;

                String[] parts = line.split(": ", 2);
                String text = parts[0];
                String value = parts[1];

                switch (text){
                    case "Name": name = value; break;
                    case "Birthdate": birthDate = LocalDate.parse(value); break;
                    case "Email": email = value; break;
                    case "Phone number": phoneNumber = value; break;
                    case "State": state = value; break;
                    case "City": city = value; break;
                    case "ZipCode": zipCode = value; break;
                    case "Monthly income": monthlyIncome = Double.parseDouble(value); break;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: customer data could not be loaded." + e.getMessage(), e);
        }

        Address address = new Address(state, city, zipCode);
        return new Customer(name, cpf, birthDate, email, phoneNumber, address, monthlyIncome);
    }
}
