package com.example.projectbackend.ServiceInterfaces;

import com.example.projectbackend.Model.Transaction;
import com.example.projectbackend.Model.request.TransactionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITransactionService {
    ResponseEntity<List<Transaction>> ReturnAllSentTransactions(int senderId);
    ResponseEntity<List<Transaction>> ReturnAllReceivedTransactions(int receiverId);
    void CreateTransaction(TransactionRequest transactionRequest, IAccount senderAccount, IAccount receiverAccount);
    ResponseEntity<List<Transaction>> ReturnAllTransactionsByUser(int receiverId);
    ResponseEntity<List<Transaction>> ReturnRecentTransactionsByUser(int receiverId);
}