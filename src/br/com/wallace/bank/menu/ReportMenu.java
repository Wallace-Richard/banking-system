package br.com.wallace.bank.menu;

public class ReportMenu implements Menu
{
    @Override
    public void showHeader()
    {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║    Reports & Auditing    ║");
        System.out.println("╚══════════════════════════╝\n");
    }

    @Override
    public void showOptions()
    {
        System.out.println("[1] Audit report");
        System.out.println("[2] Customer report");
        System.out.println("[3] Compliance alerts");
        System.out.println("[4] Back");
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
