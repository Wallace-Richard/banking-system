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
    public static void deposit(Account account, double amount)
    {
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.DEPOSIT;

        Transaction transaction = new Transaction(timestamp, type, amount, account.getNumberAccount());

        TransactionRepository.registerTransaction(transaction, account);
        AccountRepository.saveAccount(account);
    }

    public static void withdrawal(Account account, double amount)
    {
        double dailyLimit = TransactionRepository.checkLimitWithdrawal(account);
        double withdrawal;
        double newBalance;
        withdrawal = amount + account.getAccountType().getTaxWithdrawal();
        if ((dailyLimit - withdrawal) >= -1000) {
            System.out.println("Error: Daily limit to withdrawal is " + (dailyLimit + 1000));
            return;
        }
        else if (account.getBalance() < withdrawal) {
            System.out.println("\nError: Withdrawals cannot exceed the account balance, try again!\n");
            return;
        }

        newBalance = account.getBalance() - withdrawal;

        account.setBalance(newBalance);
        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.WITHDRAWAL;

        Transaction transaction = new Transaction(timestamp, type, amount, account.getNumberAccount());

        TransactionRepository.registerTransaction(transaction, account);
        AccountRepository.saveAccount(account);
    }

    public static void transfer(Account accountIn, Account accountOut, double amount)
    {
        accountOut.setBalance(accountOut.getBalance() - amount);

        accountIn.setBalance(accountIn.getBalance() + amount);

        if (accountOut.getBalance() < amount) {
            System.out.println("\nError: amount transfer cannot exceed the account balance, try again!\n");
            return;
        }
        else if (accountOut.getNumberAccount().equals(accountIn.getNumberAccount())){
            System.out.println("\nError: you cannot transfer to identical accounts.");
            return;
        }

        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.TRANSFER;

        Transaction transactionOut = new Transaction(timestamp, type, amount, accountOut.getNumberAccount());
        Transaction transactionIn = new Transaction(timestamp, type, amount, accountIn.getNumberAccount());

        TransactionRepository.registerTransaction(transactionOut, accountOut);
        TransactionRepository.registerTransaction(transactionIn, accountIn);

        AccountRepository.saveAccount(accountOut);
        AccountRepository.saveAccount(accountIn);

    }
}
