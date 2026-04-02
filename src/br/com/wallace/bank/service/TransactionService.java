package br.com.wallace.bank.service;

import br.com.wallace.bank.enums.TransactionType;
import br.com.wallace.bank.model.Account;
import br.com.wallace.bank.model.Transaction;
import br.com.wallace.bank.repository.AccountRepository;
import br.com.wallace.bank.repository.TransactionRepository;
import br.com.wallace.bank.util.InputReader;

import java.time.LocalDateTime;

public class TransactionService
{
    public static void deposit (Account account)
    {
        double amount = InputReader.deposit();
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.DEPOSIT;

        Transaction transaction = new Transaction(timestamp, type, amount, account.getNumberAccount());

        TransactionRepository.registerDeposit(transaction, account);
        AccountRepository.saveAccount(account);
    }
}
