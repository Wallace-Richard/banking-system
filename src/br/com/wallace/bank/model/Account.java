package br.com.wallace.bank.model;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.util.NumberAccount;

import java.time.LocalDateTime;

public class Account
{
    private String            numberAccount;
    private Customer          customer;
    private final AccountType accountType;
    private double            balance;
    private LocalDateTime     dateCreated;
    private boolean           Active;

    public Account(String numberAccount, Customer customer, AccountType accountType, double balance, LocalDateTime dateCreated, boolean active)
    {
        this.numberAccount = numberAccount;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = balance;
        this.dateCreated = dateCreated;
        this.Active = active;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getNumberAccount()
    {
        return numberAccount;
    }

    public LocalDateTime getDateCreated()
    {
        return dateCreated;
    }

    public boolean isActive()
    {
        return Active;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    @Override
    public String toString()
    {
        return "Account{" + "numberAccount='" + numberAccount + '\'' + ", customer=" + customer + ", accountType=" + accountType + ", balance=" + balance + ", dateCreated=" + dateCreated + ", Active=" + Active + '}';
    }
}
