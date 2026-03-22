package br.com.wallace.bank.model;

import br.com.wallace.bank.enums.AccountType;
import java.time.LocalDate;

public class Customer
{
    private final String      name;
    private final String      cpf;
    private final LocalDate   birthData;
    private String            email;
    private String            phoneNumber;
    private Address           adress;
    private double            monthlyIncome;
    private AccountType       accountType;

    public Customer(String name, String cpf, LocalDate birthData, String email, String phoneNumber, Address adress, double monthlyIncome)
    {
        this.name = name;
        this.cpf = cpf;
        this.birthData = birthData;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.monthlyIncome = monthlyIncome;
    }

    public String getName()
    {
        return name;
    }

    public String getCpf()
    {
        return cpf;
    }

    public LocalDate getBirthData()
    {
        return birthData;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public Address getAdress()
    {
        return adress;
    }

    public String getEmail()
    {
        return email;
    }

    public double getMonthlyIncome()
    {
        return monthlyIncome;
    }

    public AccountType getAccountType()
    {
        return accountType;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setAdress(Address adress)
    {
        this.adress = adress;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setMonthlyIncome(double monthlyIncome)
    {
        this.monthlyIncome = monthlyIncome;
    }
}
