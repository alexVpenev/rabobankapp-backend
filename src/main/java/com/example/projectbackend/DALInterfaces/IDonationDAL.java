package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.ServiceInterfaces.IDonation;

import java.sql.Date;
import java.util.List;

public interface IDonationDAL {

    List<IDonation> getAllDonationsByCharity(int charityID);
    int getNumberOfDonationsPerCharity(int charityId);
    void DonateToCharity(double amount, int accountId, int charityId, Date date);
    double GetCurrentDonationByCharity(int charityId);
    void UpdateCurrentDonationByCharity(double amount, int charityId);
    List<DonationInfo> getAllDonationsByAccountID(int accountId);
    List<IDonation> getAllDonationsByUser(int userID);
    List<DonationInfo> getRecentDonationsByAccountID(int accountId);

}
