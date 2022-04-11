package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.IAccountDAL;
import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.Model.request.UserCreateRequest;
import com.example.projectbackend.Model.request.UserEditDetailsRequest;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    public List<IAccount> userAccounts;
    private final IAccountDAL dal;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllAccounts() {
        CompletableFuture<List<IAccount>> accounts = CompletableFuture.completedFuture(dal.getAllAccounts());

        if(accounts != null) {

            return accounts.thenApply(ResponseEntity::ok);
        } else {

            return (CompletableFuture) ResponseEntity.notFound();
        }
    }

    @Override
    public IAccount GetAccountByUsername(String username)
    {
       return dal.getAccountByUsername(username);
    }


    @Override
    public IAccount getAccountByIban(String iban)
    {
        return dal.getAccountByIban(iban);
    }

    @Override
    public ResponseEntity<IAccount> ReturnAccountByUsername(String username)
    {
        IAccount account = dal.getAccountByUsername(username);
        if (account == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(account);
        }
    }

    /*@Override
    public IAccount GetAccountByUsername(String username)
    {
        IAccount account = dal.getAccountByUsername(username);
        if (account == null)
        {
            return null;
        }
        else
        {
            return account;
        }
    }*/
    @Override
    public ResponseEntity<IAccount> ReturnAccountById(int id)
    {
        IAccount account = dal.getAccountById(id);
        if (account == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(account);
        }
    }


    @Override
    public boolean UserRegistration(UserCreateRequest userCreateRequest) {
        IAccount user;
        Optional<IAccount> byUsername = Optional.ofNullable(dal.getAccountByUsername(userCreateRequest.getUsername()));
        if (byUsername.isPresent()) {

            throw new RuntimeException("User already registered. Please use different username.");

        }
        String iban = createIban();
        user = new UserAccount(userCreateRequest.getId(),
                userCreateRequest.getUsername(),
                passwordEncoder.encode(userCreateRequest.getPassword()),
                userCreateRequest.getEmail(),
                userCreateRequest.getFirstName(),
                userCreateRequest.getLastName(),
                0.00,
                iban);
            dal.addAccount(user);
            return true;
    }

    private String createIban()
    {
        String ibanFront = "NL10 RABO 0";

        int ibanBack = ThreadLocalRandom.current().nextInt(10000000,999999999);

        String iban = ibanFront+ ibanBack;
        System.out.println(iban);
        return iban;

    }

    @Override
    public void EditUserDetails(String username, UserEditDetailsRequest userEditDetailsRequest){
        IAccount user = GetAccountByUsername(username);
        int id = user.getId();

        if (user != null) {
            dal.editUserDetails(id, userEditDetailsRequest.getEmail(), userEditDetailsRequest.getFirstName(), userEditDetailsRequest.getLastName());
        } else {
            throw new RuntimeException("User not found.");
        }
    }

}
