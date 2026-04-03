package br.com.wallace.bank.util;

import br.com.wallace.bank.repository.AccountRepository;

import java.io.*;

public class NumberAccount
{
    public static String generator()
    {
        String numberAccount = AccountRepository.addAccountNumber();

        int soma = 0;
        for (int i = 0; i < numberAccount.length(); i++) {
            soma += numberAccount.charAt(i) - '0';
        }

        int checkDigit = (soma + 4) % 10;

        return numberAccount + "-" + checkDigit;
    }
}
