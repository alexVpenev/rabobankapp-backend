package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.ServiceInterfaces.IAccount;

import java.util.List;

public interface IAccountDAL {
    List<IAccount> getAllAccounts();
    IAccount getAccountById(int id);
    IAccount getAccountByUsername(String username);
    boolean addAccount(IAccount account);
    IAccount getAccountByIban(String iban);
    void editUserDetails(int id, String email, String firstName, String lastName);
}
