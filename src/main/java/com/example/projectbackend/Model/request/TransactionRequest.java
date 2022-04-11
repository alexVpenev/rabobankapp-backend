package com.example.projectbackend.Model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
public class TransactionRequest {

    protected int ID;

    protected String receiverIban;

    protected double amount;

    protected String description;

    protected Date date;

}
