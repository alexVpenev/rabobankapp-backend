package com.example.projectbackend.Model;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter @Setter
public class Charity implements ICharity {


    private int id;


    private String title;


    private String description;


    private double currentDonation;


    private double goalDonation;

    private String iban;

    public Charity(int id, String title, String description, double currentDonation, double goalDonation, String iban)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.currentDonation = currentDonation;
        this.goalDonation = goalDonation;
        this.iban=iban;
    }
    public  Charity()
    {

    }


}
