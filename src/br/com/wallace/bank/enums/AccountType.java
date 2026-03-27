package br.com.wallace.bank.enums;

public enum AccountType
{
    CHECKING(50, 2.50, "Checking"),
    SAVINGS (10, 0, "Savings"),
    SALARY  (0, 0, "Salary");

    private final double initialDeposit;
    private final double taxTransfer;
    private final String name;

    AccountType(double initialDeposit, double tax, String name)
    {
        this.initialDeposit = initialDeposit;
        this.taxTransfer = tax;
        this.name = name;
    }

    public static AccountType serchByName(String nome){
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

    public double getTaxTransfer()
    {
        return taxTransfer;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name + ", amount needed to deposit: $" + initialDeposit + ", Tax for transfer: $" + taxTransfer;
    }
}
