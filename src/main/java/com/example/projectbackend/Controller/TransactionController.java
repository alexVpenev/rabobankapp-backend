package com.example.projectbackend.Controller;

import com.example.projectbackend.Model.Transaction;
import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.Model.request.TransactionRequest;
import com.example.projectbackend.Service.TransactionService;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ITransactionService;
import com.example.projectbackend.ServiceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/sent")
    public ResponseEntity<List<Transaction>> GetSentTransactions()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int id = userService.GetAccountByUsername(currentPrincipalName).getId();

        return transactionService.ReturnAllSentTransactions(id);
    }

    @GetMapping("/received")
    public ResponseEntity<List<Transaction>> GetReceivedTransactions()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int id = userService.GetAccountByUsername(currentPrincipalName).getId();

        return transactionService.ReturnAllReceivedTransactions(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> GetAllTransactionsByUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int id = userService.GetAccountByUsername(currentPrincipalName).getId();

        return transactionService.ReturnAllTransactionsByUser(id);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Transaction>> GetRecentTransactionsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int id = userService.GetAccountByUsername(currentPrincipalName).getId();

        return transactionService.ReturnRecentTransactionsByUser(id);
    }

    @PostMapping("/send")
    public ResponseEntity SendTransaction(@RequestBody TransactionRequest transactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        IAccount senderAccount = null;
        senderAccount = userService.GetAccountByUsername(currentPrincipalName);

        IAccount receiverAccount = null ;
        receiverAccount = userService.getAccountByIban(transactionRequest.getReceiverIban());

        transactionService.CreateTransaction(transactionRequest, senderAccount,receiverAccount);

        return ResponseEntity.ok().build();
    }
}
