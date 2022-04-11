package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.ITransactionDAL;
import com.example.projectbackend.Model.Transaction;
import com.example.projectbackend.Model.request.TransactionRequest;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionDAL dal;

    public TransactionService(ITransactionDAL dal)
    {
        this.dal=dal;
    }

    @Override
    public ResponseEntity<List<Transaction>> ReturnAllSentTransactions(int senderId){
        if (dal.getAllSentTransactions(senderId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(dal.getAllSentTransactions(senderId));
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> ReturnAllReceivedTransactions(int receiverId){
        if (dal.getAllReceivedTransactions(receiverId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(dal.getAllReceivedTransactions(receiverId));
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> ReturnAllTransactionsByUser(int receiverId){
        if (dal.getAllTransactionsByUser(receiverId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(dal.getAllTransactionsByUser(receiverId));
        }
    }

    @Override
    public ResponseEntity<List<Transaction>> ReturnRecentTransactionsByUser(int receiverId){
        if (dal.getRecentTransactionsByUser(receiverId) == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(dal.getRecentTransactionsByUser(receiverId));
        }
    }


    @Override
    public void CreateTransaction(TransactionRequest transactionRequest, IAccount senderAccount, IAccount receiverAccount) {
        dal.CreateTransaction(transactionRequest,senderAccount.getId(),receiverAccount.getId());

        double amount = transactionRequest.getAmount();
        double oldBalanceOfReceiver = receiverAccount.getBalance();
        double oldBalanceOfSender = senderAccount.getBalance();

        double newBalanceOfReceiver = amount+oldBalanceOfReceiver;
        double newBalanceOfSender = oldBalanceOfSender-amount;


          dal.updateSenderBalance(senderAccount.getId(),newBalanceOfSender);
          dal.updateReceiverBalance(receiverAccount.getId(),newBalanceOfReceiver);
    }
}
