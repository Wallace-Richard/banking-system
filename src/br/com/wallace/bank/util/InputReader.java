package br.com.wallace.bank.util;

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

            if(Validator.name(name) != null){
                return name;
            }
        }
    }

    public static String cpf(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            String cpf = input.nextLine();

            if(Validator.cpf(cpf) != null){
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
                if (Validator.birthDate(birthDate) != null){
                    return birthDate;
                }
            }
            catch (DateTimeParseException e){
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

            if(Validator.email(email) != null){
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

            if(Validator.phoneNumber(phoneNumber) != null){
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

            if(Validator.state(state) != null){
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

            if(Validator.city(city) != null){
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

            if(Validator.zipCode(zipCode) != null){
                return zipCode;
            }
        }
    }

    public static double rDouble(String msg)
    {
        while (true) {
            System.out.println(msg);
            System.out.print("-> ");
            try {
                String string = input.nextLine().trim().replace(",", ".");
                double value = Double.parseDouble(string);
                if (value > 0){
                    return value;
                }
                else if (value == 0){
                    System.out.println("Error: The number cannot be 0, Try again!\n");
                }
                else {
                    System.out.println("Error: Negative number, Try again!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number, Try again!\n");
            }
        }
    }
}
