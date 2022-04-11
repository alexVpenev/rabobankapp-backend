package com.example.projectbackend.Model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class DonationInfo {

    private String charityName;
    private Date date;
    private String iban;
    private Double amount;

    public DonationInfo() {

    }

    public DonationInfo(String charityName,Date date,String iban, double amount)
    {
        this.charityName=charityName;
        this.date=date;
        this.iban=iban;
        this.amount=amount;
    }

}
