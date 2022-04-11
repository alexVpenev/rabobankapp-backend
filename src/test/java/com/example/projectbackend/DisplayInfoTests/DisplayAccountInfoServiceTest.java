package com.example.projectbackend.DisplayInfoTests;

import com.example.projectbackend.Model.DisplayAccountInfo;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.Repository.DisplayAccountInfoDalJDBC;
import com.example.projectbackend.Repository.DonationDalJDBC;
import com.example.projectbackend.Service.DisplayAccountService;
import com.example.projectbackend.Service.DonationService;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
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
public class DisplayAccountInfoServiceTest {
    @Autowired
    DisplayAccountService displayAccountService;

    @MockBean
    DisplayAccountInfoDalJDBC displayAccountInfoDalJDBC;

    @Test
    void ReturnAllAccountsForDisplayTest() {

        List<IDisplayAccountInfo> accounts = new ArrayList<IDisplayAccountInfo>();

        when(displayAccountInfoDalJDBC.getAllAccountsForDisplay()).thenReturn(accounts);

        Assertions.assertEquals(ResponseEntity.ok().body(accounts), displayAccountService.ReturnAllAccountsForDisplay());
    }

    @Test
    void ReturnAllAccountsForDisplayFailTest() {

        when(displayAccountInfoDalJDBC.getAllAccountsForDisplay()).thenReturn(null);

        Assertions.assertEquals(ResponseEntity.notFound().build(), displayAccountService.ReturnAllAccountsForDisplay());
    }

    @Test
    void DisplayAccountInfoModelTest() {
        DisplayAccountInfo first = new DisplayAccountInfo();

        first.setID(1);
        first.setUsername("asd");
        first.setIban("asd");
        first.setSubs(new ArrayList<ISubscription>());

        first.addSub(new Subscription());

        DisplayAccountInfo second = new DisplayAccountInfo(first.getID(), first.getUsername(), first.getIban(), first.getSubs());

        Assertions.assertEquals(first, second);



    }
}
