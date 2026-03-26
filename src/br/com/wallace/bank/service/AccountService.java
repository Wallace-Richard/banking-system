package br.com.wallace.bank.service;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Customer;
import br.com.wallace.bank.repository.AccountRepository;
import br.com.wallace.bank.repository.CustomerRepository;
import br.com.wallace.bank.util.FormReader;
import br.com.wallace.bank.util.InputReader;

import java.util.List;

public class AccountService
{
    public static void createAccount()
    {
        List<String> questions = FormReader.readQuestionsAccount();
        String cpf = InputReader.accountCpf(questions.get(0));
        AccountType accountType = InputReader.accountType(questions.get(1));
        double initialDeposit = InputReader.initialDeposit(questions.get(2), accountType);

        Customer customer = CustomerRepository.findByCPF(cpf);

        Account account = new Account(customer, accountType, initialDeposit);

        AccountRepository.saveAccount(account);
    }
}
