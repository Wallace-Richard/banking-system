package br.com.wallace.bank.menu;

public class AccountMenu implements Menu
{
    @Override
    public void showHeader()
    {
        System.out.println("\n╔═══════════════════════╗");
        System.out.println("║   Account Management   ║");
        System.out.println("╚════════════════════════╝\n");
    }

    @Override
    public void showOptions()
    {
        System.out.println("[1] Create account");
        System.out.println("[2] Search accounts");
        System.out.println("[3] View account details");
        System.out.println("[4] Close account");
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
