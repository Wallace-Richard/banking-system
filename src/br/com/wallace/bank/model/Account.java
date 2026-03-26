package br.com.wallace.bank.model;

import br.com.wallace.bank.enums.AccountType;

public class Account
{
    private Customer          customer;
    private final AccountType accountType;
    private double            initialDeposit;


    public Account(Customer customer, AccountType accountType, double initialDeposit)
    {
        this.customer = customer;
        this.accountType = accountType;
        this.initialDeposit = initialDeposit;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public double getInitialDeposit()
    {
        return initialDeposit;
    }
}
