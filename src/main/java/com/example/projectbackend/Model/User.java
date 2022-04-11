package com.example.projectbackend.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter @Setter
public abstract class User {

    protected int id;

    protected   String username;

    protected   String password;

    protected   String email;

    protected   String firstName;

    protected   String lastName;

    protected Double balance;

    protected  String iban;

    public  User() {

    }

    public User(int id,String username, String password,String email, String fName,String lName, Double balance, String iban)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=fName;
        this.lastName=lName;
        this.balance=balance;
        this.iban=iban;

    }


}
