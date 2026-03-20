package br.com.wallace.bank.menu;

public class TransactionMenu implements Menu
{
    @Override
    public void showHeader()
    {
        System.out.println("\n╔═════════════════════════╗");
        System.out.println("║       Transaction       ║");
        System.out.println("╚═════════════════════════╝\n");
    }

    @Override
    public void showOptions()
    {
        System.out.println("[1] Deposit");
        System.out.println("[2] Withdraw");
        System.out.println("[3] Transfer");
        System.out.println("[4] View statement");
        System.out.println("[5] Back");
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

    }

    @Override
    public void runOption()
    {

    }
}
