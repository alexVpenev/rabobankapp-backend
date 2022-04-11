package com.example.projectbackend.DonationTests;

import com.example.projectbackend.Controller.DisplayAccountController;
import com.example.projectbackend.Controller.DonationController;
import com.example.projectbackend.Model.Donation;
import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.Model.User;
import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.Repository.DonationDalJDBC;
import com.example.projectbackend.Service.DisplayAccountService;
import com.example.projectbackend.Service.DonationService;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DonationControllerTest {
    @Autowired
    DonationController donationController;

    @MockBean
    DonationService donationService;

    @MockBean
    UserService userService;

    @Test
    void GetAllDonationsPerCharityIDTest() {
        List<IDonation> donations = new ArrayList<IDonation>();
        Donation donation = new Donation(1,2,3,40, Date.valueOf("2020-12-12"));
        donations.add(donation);

        when(donationService.ReturnAllDonationsByCharity(1)).thenReturn(ResponseEntity.ok().body(donations));

        Assertions.assertEquals(ResponseEntity.ok().body(donations),donationController.GetAllDonationsPerCharityId(1));

    }

    @Test
    void GetNumberOfDonationsPerCharityTest() {

        when(donationService.ReturnNumberOfDonationsPerCharity(1)).thenReturn(ResponseEntity.ok().body(1));

        Assertions.assertEquals(ResponseEntity.ok().body(1),donationController.GetNumberOfDonationsPerCharity(1));
    }

    //
    @Test
    @WithMockUser(username = "name", roles = {"USER"})
    void GetDonationsByAccount() {

        CompletableFuture<List<UserAccount>> accounts = CompletableFuture.completedFuture(new ArrayList<UserAccount>());
        UserAccount account = new UserAccount();
        account.setId(1);

        when(userService.GetAccountByUsername("name")).thenReturn(account);

        when(donationService.getAllDonationsByAccountId(account.getId())).thenReturn(accounts.thenApply(ResponseEntity::ok));

        Assertions.assertNotEquals(CompletableFuture.completedFuture(ResponseEntity.ok().body(accounts)),donationController.getDonationsByAccount());

    }


    @Test
    @WithMockUser(username = "name", roles = {"USER"})
    void GetRecentDonationsByAccountTest() {

        List<DonationInfo> donations = new ArrayList<DonationInfo>();
        UserAccount account = new UserAccount();
        account.setId(1);

        when(userService.GetAccountByUsername("name")).thenReturn(account);

        when(donationService.ReturnRecentDonationsByAccount(account.getId())).thenReturn(ResponseEntity.ok().body(donations));

        Assertions.assertEquals(ResponseEntity.ok().body(donations),donationController.getRecentDonationsByAccount());

    }


}
