package com.example.projectbackend.ServiceInterfaces;

import com.example.projectbackend.Model.DonationInfo;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDonationService {

    ResponseEntity<List<IDonation>> ReturnAllDonationsByCharity(int charityID);
    ResponseEntity<Integer> ReturnNumberOfDonationsPerCharity(int charityId);
    void DonateToCharity(double amount, IAccount account, int charityId, Date date);
    CompletableFuture<ResponseEntity> getAllDonationsByAccountId(int accountId);
    ResponseEntity<List<DonationInfo>> ReturnRecentDonationsByAccount(int accountID);
}
