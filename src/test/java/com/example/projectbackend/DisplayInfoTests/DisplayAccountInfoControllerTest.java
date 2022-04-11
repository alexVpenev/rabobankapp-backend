package com.example.projectbackend.DisplayInfoTests;

import com.example.projectbackend.Controller.DisplayAccountController;
import com.example.projectbackend.Repository.DisplayAccountInfoDalJDBC;
import com.example.projectbackend.Service.DisplayAccountService;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DisplayAccountInfoControllerTest {
    @Autowired
    DisplayAccountController displayAccountController;

    @MockBean
    DisplayAccountService displayAccountService;

    @Test
    void DisplayAllAccountsTest() {

        List<IDisplayAccountInfo> accounts = new ArrayList<IDisplayAccountInfo>();

        when(displayAccountService.ReturnAllAccountsForDisplay()).thenReturn(ResponseEntity.ok().body(accounts));

        Assertions.assertEquals(ResponseEntity.ok().body(accounts), displayAccountController.DisplayAllAccounts());
    }

    @Test
    void DisplayAllAccountsFailTest() {

        when(displayAccountService.ReturnAllAccountsForDisplay()).thenReturn(ResponseEntity.notFound().build());

        Assertions.assertEquals(ResponseEntity.notFound().build(), displayAccountController.DisplayAllAccounts());
    }
}
