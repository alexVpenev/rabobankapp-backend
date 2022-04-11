package com.example.projectbackend.Model;


import com.example.projectbackend.ServiceInterfaces.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class Donation implements IDonation{


    private int ID;


    private int charityID;


    private int accountID;


    private double amount;

    protected Date date;

    public Donation() {

    }

    public Donation(int id, int charityID, int accountID, double amount,Date date) {
        this.ID = id;
        this.charityID = charityID;
        this.accountID = accountID;
        this.amount = amount;
        this.date=date;
    }




}
