package br.com.wallace.bank.model;

public class Address
{
    private String state;
    private String city;
    private String zipCode;

    public Address(String state, String city, String zipCode)
    {
        this.state   = state;
        this.city    = city;
        this.zipCode = zipCode;
    }

    public String getState()
    {
        return state;
    }

    public String getCity()
    {
        return city;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }
}
