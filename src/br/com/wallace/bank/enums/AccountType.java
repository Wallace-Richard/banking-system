package br.com.wallace.bank.enums;

public enum AccountType
{
    CHECKING(50, 2.50, 1000, "Checking"),
    SAVINGS (10, 0, 1000, "Savings"),
    SALARY  (0, 2.50, 0, "Salary");

    private final double initialDeposit;
    private final double taxWithdrawal;
    private final double dailyWithdrawalLimit;
    private final String name;

    AccountType(double initialDeposit, double taxWithdrawal, double dailyWithdrawalLimit, String name)
    {
        this.initialDeposit = initialDeposit;
        this.taxWithdrawal = taxWithdrawal;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        this.name = name;
    }

    public static AccountType searchByName(String nome){
        for (AccountType type : values())
        {
            if (type.getName().equalsIgnoreCase(nome)) {
                return type;
            }
        }
        System.out.println("Error: Account type does not exist, try again!\n");
        return null;
    }
    
    public double getInitialDeposit()
    {
        return initialDeposit;
    }

    public double getTaxWithdrawal()
    {
        return taxWithdrawal;
    }

    public String getName()
    {
        return name;
    }

    public double getDailyWithdrawalLimit()
    {
        return dailyWithdrawalLimit;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
