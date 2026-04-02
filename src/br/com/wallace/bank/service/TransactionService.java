package br.com.wallace.bank.service;

import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.repository.AccountRepository;
import br.com.wallace.bank.util.InputReader;

public class TransactionService
{
    public static void deposit (Account account)
    {
        double addBalance = InputReader.deposit();
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + addBalance;
        account.setBalance(newBalance);

        AccountRepository.saveAccount(account);
    }
}
