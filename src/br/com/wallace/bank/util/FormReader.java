package br.com.wallace.bank.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FormReader
{
    private static final File fileCustomer = new File("data/forms/customer_register_form.txt");
    private static final File fileAccount = new File("data/forms/account_register_form.txt");
    private static final List<String> questions = new ArrayList<>();

    public static List<String> readQuestionsCustomer() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileCustomer));
        String line;

        while ((line = reader.readLine()) != null){
                questions.add(line);
        }
        reader.close();
        return questions;
    }

    public static List<String> readQuestionsAccount() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileAccount));
        String line;

        while ((line = reader.readLine()) != null){
            questions.add(line);
        }
        reader.close();
        return questions;
    }
}
