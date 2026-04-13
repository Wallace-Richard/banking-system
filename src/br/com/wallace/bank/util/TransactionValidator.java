package br.com.wallace.bank.util;

import br.com.wallace.bank.enums.TransactionType;

public class TransactionValidator
{
    private TransactionValidator()
    {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static Double deposit(String amount)
    {
        try {
            double value = Double.parseDouble(amount);
            if (value < TransactionType.DEPOSIT.getMinimumOperationValue()) {
                System.out.println("Error: The deposit cannot be zero or negative.\n");
                return null;
            }
            else if (value > TransactionType.DEPOSIT.getMaxOperationValue()) {
                System.out.println("Error: The maximum deposit amount is 10_000.\n");
                return null;
            }
            else {
                return value;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return null;
    }

    public static Double withdrawal(String amount)
    {
        try {
            double value = Double.parseDouble(amount);
            if (value < TransactionType.WITHDRAWAL.getMinimumOperationValue()) {
                System.out.println("Error: The withdrawal cannot be zero or negative.\n");
                return null;
            }
            else {
                return value;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return null;
    }

    public static Double transfer(String amount)
    {
        try {
            double value = Double.parseDouble(amount);
            if (value < TransactionType.TRANSFER.getMinimumOperationValue()) {
                System.out.println("Error: The transfer cannot be zero or negative.\n");
                return null;
            }
            else if (value > TransactionType.TRANSFER.getMaxOperationValue()) {
                System.out.println("Error: The maximum transfer amount is 5_000.\n");
                return null;
            }
            else {
                return value;
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return null;
    }
}
