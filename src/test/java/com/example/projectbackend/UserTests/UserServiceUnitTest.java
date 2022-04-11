package com.example.projectbackend.UserTests;


import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.Model.request.UserCreateRequest;
import com.example.projectbackend.Repository.UserDalJDBC;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Autowired
    UserService userService;

    @MockBean
    UserDalJDBC userDalJDBC;

    @Test
    public void getAllAccountsTest()  {

        List<IAccount> users = new ArrayList<>();
         UserAccount user =  new UserAccount(1,"nz","jsd","hshja","jsd","hds",10.2,"jr");
         users.add(user);
        when(userDalJDBC.getAllAccounts()).thenReturn(Stream.of(user)
                .collect(Collectors.toList()));
        Assertions.assertEquals(new ResponseEntity(users, HttpStatus.OK),userService.ReturnAllAccounts());

    }
    @Test
    public void getAllAccountsFailTest()  {

        when(userDalJDBC.getAllAccounts()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.ReturnAllAccounts());

    }

    @Test
    public void getAccountByIdTest(){

        int id = 10;
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds",10.2,"12");

        when(userDalJDBC.getAccountById(id))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.ReturnAccountById(id));

    }
    @Test
    public void getAccountByIdFailTest(){

        int id = 9;

        when(userDalJDBC.getAccountById(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.ReturnAccountById(id));


    }
    @Test
    public void getAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds",10.32,"f");

        when(userDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(user.getId(),userService.GetAccountByUsername(username).getId());

    }


    @Test
    public void returnAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds",10.3,"jas");

        when(userDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.ReturnAccountByUsername(username));

    }

    @Test
    public void getAccountByUsernameFailTest(){

        String username = "name";
        when(userDalJDBC.getAccountByUsername(username))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.ReturnAccountByUsername(username));

    }

    @Test
    public void UserRegistrationTest(){

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("secret");
        userCreateRequest.setPassword("taina");
        userCreateRequest.setEmail("com");
        userCreateRequest.setLastName("yo");
        userCreateRequest.setId(1);

        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds",103.3,"jdf");

        when(userDalJDBC.addAccount(user)).thenReturn(true);

        Assertions.assertEquals(true,userService.UserRegistration(userCreateRequest));
    }
}
