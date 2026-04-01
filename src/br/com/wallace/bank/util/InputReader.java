package br.com.wallace.bank.util;

import br.com.wallace.bank.enums.AccountType;
import br.com.wallace.bank.repository.AccountRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputReader
{
    private static Scanner input = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private InputReader()
    {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static String name(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String name = input.nextLine();

            if (CustomerValidator.name(name) != null) {
                return name;
            }
        }
    }

    public static String CustomerCpf(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String cpf = input.nextLine();

            if (CustomerValidator.cpf(cpf) != null) {
                return cpf;
            }
        }
    }

    public static LocalDate birthDate(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String birthDateStr = input.nextLine();

            try {
                LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
                if (CustomerValidator.birthDate(birthDate) != null) {
                    return birthDate;
                }
            }
            catch (DateTimeParseException e) {
                System.out.println("Error: Invalid date format! Use DD/MM/YYYY\n");
            }
        }
    }

    public static String email(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String email = input.nextLine();

            if (CustomerValidator.email(email) != null) {
                return email;
            }
        }
    }

    public static String phoneNumber(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> +");
            String phoneNumber = input.nextLine();

            if (CustomerValidator.phoneNumber(phoneNumber) != null) {
                return phoneNumber;
            }
        }
    }

    public static String state(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String state = input.nextLine();

            if (CustomerValidator.state(state) != null) {
                return state;
            }
        }
    }

    public static String city(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String city = input.nextLine();

            if (CustomerValidator.city(city) != null) {
                return city;
            }
        }
    }

    public static String zipCode(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String zipCode = input.nextLine();

            if (CustomerValidator.zipCode(zipCode) != null) {
                return zipCode;
            }
        }
    }

    public static double monthlyIncome(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String value = input.nextLine().trim().replace(",", ".");

            double monthlyIncome = CustomerValidator.monthlyIncome(value);
            if (monthlyIncome != 0) {
                return monthlyIncome;
            }
        }
    }

    public static String accountCpf(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String cpf = input.nextLine();

            if (AccountValidator.cpf(cpf) != null) {
                return cpf;
            }
        }
    }

    public static AccountType accountType(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            AccountType accountType = AccountType.searchByName(input.nextLine());
            if (accountType != null) {
                return accountType;
            }
        }
    }

    public static double initialDeposit(String msg, AccountType type)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String string = input.nextLine();

            Double initialDeposit = AccountValidator.initialDeposit(type, string);
            if (initialDeposit != null) {
                return initialDeposit;
            }
        }
    }

    public static String numberAccount()
    {
        while (true) {
            System.out.println("Account number:");
            System.out.print("-> ");
            String number = input.nextLine();

            if (!number.matches("[0-9]{5}-[0-9]")){
                System.out.println("Error: Account number is invalid, use the format (XXXXX-X)\n");
            }
            else if (AccountRepository.existsByAccount(number) == null){
                System.out.println("Error: Account number does not exist, try again!\n");
            }
            return number;
        }
    }
}
