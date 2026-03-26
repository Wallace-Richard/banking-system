package br.com.wallace.bank.util;

import br.com.wallace.bank.repository.CustomerRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidator
{
    private CustomerValidator()
    {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static String name(String name)
    {
        String regexName = "[a-zA-Z]{3,}\\s[a-zA-Z]{3,}";
        Pattern pattern = Pattern.compile(regexName);
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()) {
            return name;
        }
        else {
            System.out.println("Error: Invalid name, first and last name are required.\n");
            return null;
        }
    }

    public static String cpf(String cpf)
    {
        String regexCpf = "[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}";
        Pattern pattern = Pattern.compile(regexCpf);
        Matcher matcher = pattern.matcher(cpf);

        if (!matcher.matches()) {
            System.out.println("Error: Invalid CPF. Use the format XXX.XXX.XXX-XX\n");
            return null;
        }

        String cpfNumbers = cpf.replaceAll("[^0-9]", "");

        if (cpfNumbers.matches("(\\d)\\1{10}")) {
            System.out.println("Error: Invalid CPF.\n");
            return null;
        }

        if (!hasValidDigitsCpf(cpfNumbers)) {
            System.out.println("Error: Invalid CPF.\n");
            return null;
        }

        if (CustomerRepository.existsByCpf(cpfNumbers)) {
            System.out.println("Error: Cpf provided already exists.\n");
            return null;
        }
        return cpf;
    }

    public static LocalDate birthDate(LocalDate birthDate)
    {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (birthDate.isAfter(LocalDate.now())) {
            System.out.println("Error: Birth date cannot be in the future!\n");
            return null;
        }
        else if (age < 18) {
            System.out.println("Error: Customer must be at least 18 years old!\n");
            return null;
        }
        else {
            return birthDate;
        }
    }

    public static String email(String email)
    {
        email = email.trim();
        String regexPhoneNumber = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regexPhoneNumber);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return email;
        }
        else {
            System.out.println("Error: Invalid email, use the format (username@domain.com”)\n");
            return null;
        }
    }

    public static String phoneNumber(String phoneNumber)
    {
        String regexPhoneNumber = "[1-9][1-9]\\s9[1-9]{8}";
        Pattern pattern = Pattern.compile(regexPhoneNumber);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            return phoneNumber;
        }
        else {
            System.out.println("Error: Invalid phone number, use the format (XX 9XXXXXXXX)\n");
            return null;
        }
    }

    public static String state(String state)
    {
        String regexState = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$";
        Pattern pattern = Pattern.compile(regexState);
        Matcher matcher = pattern.matcher(state);

        if (matcher.matches()) {
            return state;
        }
        else {
            System.out.println("Error: Invalid state, use the state abbreviation (ex: SP)\n");
            return null;
        }
    }

    public static String city(String city)
    {
        String regexCity = "[A-Za-zÀ-ÿ\\s'-]{3,}$";
        Pattern pattern = Pattern.compile(regexCity);
        Matcher matcher = pattern.matcher(city);

        if (matcher.matches()) {
            return city;
        }
        else {
            System.out.println("Error: Invalid city, enter a valid city.\n");
            return null;
        }
    }

    public static String zipCode(String zipCode)
    {
        String regexzipCode = "[0-9]{5}-[0-9]{3}";
        Pattern pattern = Pattern.compile(regexzipCode);
        Matcher matcher = pattern.matcher(zipCode);

        if (matcher.matches()) {
            return zipCode;
        }
        else {
            System.out.println("Error: Invalid zipCode, use the format (XXXXX-XXX)\n");
            return null;
        }
    }

    public static double monthlyIncome(String string)
    {
        try {
            double value = Double.parseDouble(string);
            if (value > 0) {
                return value;
            }
            else if (value == 0) {
                System.out.println("Error: The number cannot be 0, Try again!\n");
            }
            else {
                System.out.println("Error: Negative number, Try again!\n");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Invalid number, Try again!\n");
        }
        return 0;
    }

    protected static boolean hasValidDigitsCpf(String cpfNumbers)
    {
        //First digit
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpfNumbers.charAt(i) - '0') * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        //Second digit
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpfNumbers.charAt(i) - '0') * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        //Verification
        return digito1 == (cpfNumbers.charAt(9) - '0') && digito2 == (cpfNumbers.charAt(10) - '0');
    }
}
