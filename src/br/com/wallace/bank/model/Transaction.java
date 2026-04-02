package br.com.wallace.bank.model;

import br.com.wallace.bank.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction
{
    private LocalDateTime timestamp;
    private TransactionType type;
    private double amount;
    private String numberAccount;

    public Transaction(LocalDateTime timestamp, TransactionType type, double amount, String numberAccount)
    {
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.numberAccount = numberAccount;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public TransactionType getType()
    {
        return type;
    }

    public double getAmount()
    {
        return amount;
    }

    public String getNumberAccount()
    {
        return numberAccount;
    }

    @Override
    public String toString()
    {
        return "Transaction{" + "timestamp=" + timestamp + ", type=" + type + ", amount=" + amount + ", numberAccount='" + numberAccount + '\'' + '}';
    }
}
