package com.example.projectbackend.DonationTests;


import com.example.projectbackend.Model.DisplayAccountInfo;
import com.example.projectbackend.Model.Donation;
import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.Model.request.DonationRequest;
import com.example.projectbackend.Repository.DonationDalJDBC;
import com.example.projectbackend.Service.DonationService;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DonationServiceUnitTest {
    @Autowired
    DonationService donationService;

    @MockBean
    DonationDalJDBC donationDalJDBC;

   @Test
    public void getAllDonationsByCharityTest(){

       List<IDonation> donations = new ArrayList<>();
       Donation donation = new Donation(1,2,3,40, Date.valueOf("2020-12-12"));
       donations.add(donation);
       int id = 1;

       when(donationDalJDBC.getAllDonationsByCharity(id))
               .thenReturn(Stream.of(donation)
                       .collect(Collectors.toList()));

       Assertions.assertEquals(new ResponseEntity(donations, HttpStatus.OK),donationService.ReturnAllDonationsByCharity(id));

   }

    @Test
    public void getAllDonationByCharityFailTest()  {

       int id =1;
        when(donationDalJDBC.getAllDonationsByCharity(id)).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),donationService.ReturnAllDonationsByCharity(id));

    }

    @Test
    public void getNumberOfDonationsPerCharityTest(){
       int id =1;

       when(donationDalJDBC.getNumberOfDonationsPerCharity(id))
               .thenReturn(10);

       Assertions.assertEquals(new ResponseEntity(10,HttpStatus.OK),donationService.ReturnNumberOfDonationsPerCharity(id));
    }


    @Test
    void returnRecentDonationsByAccountTest() {
        List<DonationInfo> donations = new ArrayList<DonationInfo>();

        when(donationDalJDBC.getRecentDonationsByAccountID(1)).thenReturn(donations);

        Assertions.assertEquals(ResponseEntity.ok().body(donations), donationService.ReturnRecentDonationsByAccount(1));
    }

    @Test
    void returnRecentDonationsByAccountFailTest() {

        when(donationDalJDBC.getRecentDonationsByAccountID(1)).thenReturn(null);

        Assertions.assertEquals(ResponseEntity.notFound().build(), donationService.ReturnRecentDonationsByAccount(1));
    }

    @Test
    void getAllDonationsByAccountIdTest() {

        List<DonationInfo> donations = new ArrayList<DonationInfo>();
        DonationInfo donation = new DonationInfo("asd", Date.valueOf("2020-12-12"), "äsdasd", 12.3);
        donations.add(donation);

        when(donationDalJDBC.getAllDonationsByAccountID(1))
                .thenReturn(Stream.of(donation)
                        .collect(Collectors.toList()));

        Assertions.assertNotEquals(CompletableFuture.completedFuture(ResponseEntity.ok().body(donations)),donationService.getAllDonationsByAccountId(1));

    }

    @Test
    void getAllDonationsByAccountIdFailTest() {
        when(donationDalJDBC.getAllDonationsByAccountID(1))
                .thenReturn(null);

        Assertions.assertNotEquals(CompletableFuture.completedFuture(ResponseEntity.notFound()),donationService.getAllDonationsByAccountId(1));

    }

    @Test
    void DonationModelTest() {
        Donation first = new Donation();

        first.setID(1);
        first.setCharityID(1);
        first.setAccountID(1);
        first.setAmount(12.4);
        first.setDate(Date.valueOf("2020-12-12"));

        Donation second = new Donation(first.getID(), first.getCharityID(), first.getAccountID(), first.getAmount(), first.getDate());

        Assertions.assertNotEquals(first, second);
    }

    @Test
    void DonationInfoModelTest() {
        DonationInfo first = new DonationInfo();

        first.setCharityName("asd");
        first.setIban("asd");
        first.setAmount(12.4);
        first.setDate(Date.valueOf("2020-12-12"));

        DonationInfo second = new DonationInfo(first.getCharityName(), first.getDate(), first.getIban(), first.getAmount());

        Assertions.assertNotEquals(first, second);
    }

    @Test
    void DonationRequestTest() {
        DonationRequest first = new DonationRequest();

        first.setID(1);
        first.setCharityId(1);
        first.setAmount(12.4);
        first.setDate(Date.valueOf("2020-12-12"));

        DonationRequest second = new DonationRequest();

        second.setID(first.getID());
        second.setCharityId(first.getCharityId());
        second.setAmount(first.getAmount());
        second.setDate(first.getDate());

        Assertions.assertEquals(first, second);
    }


}
