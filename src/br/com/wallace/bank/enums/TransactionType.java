package br.com.wallace.bank.enums;

public enum TransactionType
{
    DEPOSIT("Deposit", 0.01, 10_000, "+"),
    WITHDRAWAL("Withdrawal", 0.01, 1_000, "-"),
    TRANSFER("Transfer", 0.01, 5_000, "-+");

    private final String name;
    private final double minimumOperationValue;
    private final double maxOperationValue;
    private final String signal;

    TransactionType(String name, double minimumOperationValue, double maxOperationValue, String signal)
    {
        this.name = name;
        this.minimumOperationValue = minimumOperationValue;
        this.maxOperationValue = maxOperationValue;
        this.signal = signal;
    }

    public static TransactionType searchByName(String nome){
        for (TransactionType type : values())
        {
            if (type.getName().equalsIgnoreCase(nome)) {
                return type;
            }
        }
        System.out.println("Error: Transaction type does not exist, try again!\n");
        return null;
    }

    public String getName()
    {
        return name;
    }

    public double getMinimumOperationValue()
    {
        return minimumOperationValue;
    }

    public double getMaxOperationValue()
    {
        return maxOperationValue;
    }

    public String getSignal()
    {
        return signal;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
