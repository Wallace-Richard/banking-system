package br.com.wallace.bank.service;

import br.com.wallace.bank.model.Address;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.repository.CustomerRepository;
import br.com.wallace.bank.util.FormReader;
import br.com.wallace.bank.util.InputReader;

import java.time.LocalDate;
import java.util.*;

public class CustomerService
{
    public static void createCustomer()
    {
        List<String> questions = FormReader.readCustomerQuestions();
        String name =            InputReader.name(questions.get(0));
        String cpf =             InputReader.CustomerCpf(questions.get(1));
        LocalDate birthDate =    InputReader.birthDate(questions.get(2));
        String email =           InputReader.email(questions.get(3));
        String phoneNumber =     InputReader.phoneNumber(questions.get(4));
        String state =           InputReader.state(questions.get(5));
        String city =            InputReader.city(questions.get(6));
        String zipCode =         InputReader.zipCode(questions.get(7));
        double monthlyIncome =   InputReader.monthlyIncome(questions.get(8));

        Address address = new Address(state, city, zipCode);
        Customer customer = new Customer(name, cpf, birthDate, email, phoneNumber, address, monthlyIncome);

        CustomerRepository.saveCustomer(customer);
    }
}
