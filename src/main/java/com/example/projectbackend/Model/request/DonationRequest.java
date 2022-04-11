package com.example.projectbackend.Model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Data
@Getter
@Setter
public class DonationRequest {


    protected int ID;


    protected int charityId;


    protected double amount;

    protected Date date;
}
