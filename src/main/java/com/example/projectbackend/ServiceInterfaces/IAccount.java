package com.example.projectbackend.ServiceInterfaces;

public interface IAccount {

    public int getId();
    public void setId(int id);

     public String getUsername();
    public void setUsername(String username);

    public String getPassword();
    public void setPassword(String username);

    public String getEmail();
    public void setEmail(String email);

    public String getFirstName();
    public void setFirstName(String firstName);

    public String getLastName();
    public void setLastName(String lastName);

    public String getIban();

    public Double getBalance();
}
