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
    public static void deposit(Account account)
    {
        double deposit = InputReader.deposit();
        double newBalance = account.getBalance() + deposit;
        account.setBalance(newBalance);

        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.DEPOSIT;

        Transaction transaction = new Transaction(timestamp, type, deposit, account.getNumberAccount());

        TransactionRepository.registerTransaction(transaction, account);
        AccountRepository.saveAccount(account);
    }

    public static void withdrawal(Account account)
    {
        double amount;
        double withdrawal;
        double newBalance;
        while (true) {
            amount = InputReader.withdrawal();
            withdrawal = amount + account.getAccountType().getTaxWithdrawal();
            if (account.getBalance() >= withdrawal) {
                newBalance = account.getBalance() - withdrawal;
                break;
            }
            else {
                System.out.println("\nError: Withdrawals cannot exceed the account balance, try again!\n");
            }
        }

        account.setBalance(newBalance);
        LocalDateTime timestamp = LocalDateTime.now();
        TransactionType type = TransactionType.WITHDRAWAL;

        Transaction transaction = new Transaction(timestamp, type, amount, account.getNumberAccount());

        TransactionRepository.registerTransaction(transaction, account);
        AccountRepository.saveAccount(account);
    }
}
