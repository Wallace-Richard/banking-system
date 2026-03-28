package br.com.wallace.bank.service;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.repository.AccountRepository;
import br.com.wallace.bank.repository.CustomerRepository;
import br.com.wallace.bank.util.FormReader;
import br.com.wallace.bank.util.InputReader;
import br.com.wallace.bank.util.NumberAccount;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountService
{
    private static final List<String> questions =  FormReader.readAccountQuestions();
    private static final List<AccountType> listAccountType = new ArrayList<>();

    public static void createAccount()
    {
        String numberAccount    = NumberAccount.generator();
        String cpf              = InputReader.accountCpf(questions.get(0));
        AccountType accountType = InputReader.accountType(questions.get(1));
        double initialDeposit   = InputReader.initialDeposit(questions.get(2), accountType);
        LocalDateTime date      = LocalDateTime.now();
        boolean isActive        = true;

        Customer customer = CustomerRepository.loadCustomer(cpf);
        Account account = new Account(numberAccount, customer, accountType, initialDeposit, date, isActive);

        listAccountType.add(accountType);
        customer.setAccountType(listAccountType);

        AccountRepository.saveAccount(account, customer);
    }
}
