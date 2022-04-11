package com.example.projectbackend.Model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Transaction {

    private int id;

    private int senderId;

    private int receiverId;

    private double amount;

    private String description;

    private Date date;

    private String iban;

    public Transaction(int id, int senderId, int receiverId, double amount,String description, Date date, String iban) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.description=description;
        this.date=date;
        this.iban=iban;
    }

    public Transaction(int id, int senderId, int receiverId, double amount,String description, Date date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Transaction() {
    }
}

