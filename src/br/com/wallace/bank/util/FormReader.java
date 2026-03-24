package br.com.wallace.bank.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormReader
{
    private static final File fileCustomer = new File("data/forms/customer_register_form.txt");
    private static final File fileAccount = new File("data/forms/account_register_form.txt");
    private static final List<String> questions = new ArrayList<>();

    private FormReader()
    {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static List<String> readQuestionsCustomer()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileCustomer))) {
            String line;

            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: Form could not be read!" + e.getMessage(), e);
        }
        return questions;
    }

    public static List<String> readQuestionsAccount()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileAccount))) {
            String line;

            while ((line = reader.readLine()) != null) {
                questions.add(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Error: Form could not be read!" + e.getMessage(), e);
        }
        return questions;
    }
}
