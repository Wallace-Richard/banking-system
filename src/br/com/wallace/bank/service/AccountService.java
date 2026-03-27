package br.com.wallace.bank.service;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.repository.AccountRepository;
import br.com.wallace.bank.repository.CustomerRepository;
import br.com.wallace.bank.util.FormReader;
import br.com.wallace.bank.util.InputReader;

import java.util.ArrayList;
import java.util.List;

public class AccountService
{
    private static final List<String> questions =  FormReader.readAccountQuestions();

    public static void createAccount()
    {
        String cpf =              InputReader.accountCpf(questions.get(0));
        AccountType accountType = InputReader.accountType(questions.get(1));
        double initialDeposit =   InputReader.initialDeposit(questions.get(2), accountType);

        Customer customer = CustomerRepository.findByCPF(cpf);
        Account account = new Account(customer, accountType, initialDeposit);

        List<AccountType> listAccountType = new ArrayList<>();
        listAccountType.add(accountType);
        customer.setAccountType(listAccountType);

        AccountRepository.saveAccount(account, customer);
    }
}
