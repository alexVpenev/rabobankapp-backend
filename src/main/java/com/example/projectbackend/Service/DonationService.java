package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.ICharityDAL;
import com.example.projectbackend.DALInterfaces.IDonationDAL;
import com.example.projectbackend.DALInterfaces.ITransactionDAL;
import com.example.projectbackend.Model.Donation;
import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import com.example.projectbackend.ServiceInterfaces.IDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class DonationService implements IDonationService {

    @Autowired
    IDonationDAL dal;

    @Autowired
    ITransactionDAL transactionDal;
    public DonationService(IDonationDAL dal, ITransactionDAL transactionDal)
    {
        this.dal=dal;
        this.transactionDal = transactionDal;
    }
    @Override
    public ResponseEntity<List<IDonation>> ReturnAllDonationsByCharity(int charityID){
        List<IDonation> donations = dal.getAllDonationsByCharity(charityID);

        if (donations == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(donations);
        }

    }
    @Override
    public ResponseEntity<List<DonationInfo>> ReturnRecentDonationsByAccount(int accountID){
        List<DonationInfo> donations = dal.getRecentDonationsByAccountID(accountID);

        if (donations == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(donations);
        }

    }
    @Override
    public ResponseEntity<Integer> ReturnNumberOfDonationsPerCharity(int charityId)
    {
        return ResponseEntity.ok().body(dal.getNumberOfDonationsPerCharity(charityId)) ;
    }

    @Override
    public void DonateToCharity(double amount, IAccount account, int charityId, Date date)
    {
        dal.DonateToCharity( amount, account.getId(),charityId,date);
        double oldAmount = account.getBalance();
        double newAmount = oldAmount - amount;
        dal.UpdateCurrentDonationByCharity(amount,charityId);
        transactionDal.updateSenderBalance(account.getId(),newAmount);
    }
    @Override
    @Async("asyncExecutor")
    public CompletableFuture<ResponseEntity> getAllDonationsByAccountId(int accountId) {
        CompletableFuture<List<DonationInfo>> donations = CompletableFuture.completedFuture(dal.getAllDonationsByAccountID(accountId));

        if(donations != null) {

            return donations.thenApply(ResponseEntity::ok);
        } else {

            return (CompletableFuture) ResponseEntity.notFound();
        }
    }

}
