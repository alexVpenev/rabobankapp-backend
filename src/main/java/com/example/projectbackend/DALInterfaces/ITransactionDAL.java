package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.Model.Transaction;
import com.example.projectbackend.Model.request.TransactionRequest;

import java.util.List;

public interface ITransactionDAL {
    List<Transaction> getAllSentTransactions(int senderId);
    List<Transaction> getAllReceivedTransactions(int receiverId);
    void CreateTransaction(TransactionRequest transactionRequest, int senderId,int receiverId);
    void updateSenderBalance(int senderId, double newBalance);
    void updateReceiverBalance(int receiverId, double newBalance);
    List<Transaction> getAllTransactionsByUser(int receiverId);
    List<Transaction> getRecentTransactionsByUser(int receiverId);
}
