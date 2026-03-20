package br.com.wallace.bank.menu;

public class CustomerMenu implements Menu
{
    @Override
    public void showHeader()
    {
        System.out.println("\n╔═════════════════════════╗");
        System.out.println("║   Customer Management   ║");
        System.out.println("╚═════════════════════════╝\n");
    }

    @Override
    public void showOptions()
    {
        System.out.println("[1] Register new customer");
        System.out.println("[3] Search customer");
        System.out.println("[2] Update customer data");
        System.out.println("[3] View customer details");
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
