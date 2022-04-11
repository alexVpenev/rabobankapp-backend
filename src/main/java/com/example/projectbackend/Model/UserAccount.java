package com.example.projectbackend.Model;

import com.example.projectbackend.ServiceInterfaces.IAccount;

public class UserAccount extends User implements IAccount {

    public UserAccount(int id,String username, String password,String email, String firstName, String lastName,Double balance, String iban){
        super(id,username,password,email,firstName,lastName,balance,iban);

    }

    public UserAccount(){}

}
