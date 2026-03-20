package br.com.wallace.bank.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements Menu
{
    private final Menu customerMenu    = new CustomerMenu();
    private final Menu accountMenu     = new AccountMenu();
    private final Menu transactionMenu = new TransactionMenu();
    private final Menu reportMenu      = new ReportMenu();
    private Map <String, Runnable> options;

    public void showHeader()
    {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  BANKING SYSTEM - v1.0.0                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }
    @Override
    public void showOptions()
    {
        System.out.println("[1] Customer Management");
        System.out.println("[2] Account Management");
        System.out.println("[3] Transactions");
        System.out.println("[4] Reports & Auditing");
        System.out.println("[5] Exit");
    }

    @Override
    public void showMenu()
    {
        showHeader();
        showOptions();
    }

    @Override
    public void defOptions()
    {
        options = new HashMap<>();


    }

    @Override
    public void runOption()
    {
            System.out.print("\nEnter the option: ");

            switch ("1") {
                case "1":
                    customerMenu.showMenu();
                    break;
                case "2":
                    accountMenu.showMenu();
                    break;
                case "3":
                    transactionMenu.showMenu();
                    break;
                case "4":
                    reportMenu.showMenu();
                    break;
                case "0":
                    System.out.println("\nLeaving the system...");
                    return;
                default:
                    System.err.println("Wrong option, please type again!\n");
        }
    }
}
