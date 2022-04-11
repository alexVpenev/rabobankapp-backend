package com.example.projectbackend.Controller;


import com.example.projectbackend.DALInterfaces.IDonationDAL;
import com.example.projectbackend.DALInterfaces.ITransactionDAL;
import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.Model.request.DonationRequest;
import com.example.projectbackend.Repository.DonationDalJDBC;
import com.example.projectbackend.Service.DonationService;
import com.example.projectbackend.ServiceInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private IDonationService donationService;
    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionDAL transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<List<IDonation>> GetAllDonationsPerCharityId(@PathVariable(value = "id") int id)
    {
        return donationService.ReturnAllDonationsByCharity(id);
    }
    @GetMapping("/nrOfDonations/{id}")
    public ResponseEntity<Integer> GetNumberOfDonationsPerCharity(@PathVariable(value = "id") int id)
    {
        return donationService.ReturnNumberOfDonationsPerCharity(id);
    }
    @GetMapping("/byAccount")
    public CompletableFuture<ResponseEntity> getDonationsByAccount()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        IAccount account = userService.GetAccountByUsername(currentPrincipalName);
        return donationService.getAllDonationsByAccountId(account.getId());

    }
    @GetMapping("/recent")
    public ResponseEntity<List<DonationInfo>> getRecentDonationsByAccount()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        IAccount account = userService.GetAccountByUsername(currentPrincipalName);
        return donationService.ReturnRecentDonationsByAccount(account.getId());
    }
    

    @PostMapping("/donate")
    public ResponseEntity DonateToCharity(@RequestBody DonationRequest donationRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        IAccount account = userService.GetAccountByUsername(currentPrincipalName);


        donationService.DonateToCharity(donationRequest.getAmount(),account,donationRequest.getCharityId(),donationRequest.getDate());
        return ResponseEntity.ok().build();
    }

}
