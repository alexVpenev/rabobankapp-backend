package com.example.projectbackend.ServiceInterfaces;

import com.example.projectbackend.Model.request.UserCreateRequest;
import com.example.projectbackend.Model.request.UserEditDetailsRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface IUserService {

     ResponseEntity<IAccount> ReturnAccountByUsername(String username);
     ResponseEntity<IAccount> ReturnAccountById(int id);
     boolean UserRegistration(UserCreateRequest userCreateRequest);
     CompletableFuture<ResponseEntity> getAllAccounts();
     IAccount GetAccountByUsername(String username);
     IAccount getAccountByIban(String iban);
     void EditUserDetails(String username, UserEditDetailsRequest userEditDetailsRequest);
}
