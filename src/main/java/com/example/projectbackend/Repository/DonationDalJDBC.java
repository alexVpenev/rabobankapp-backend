package com.example.projectbackend.Repository;

import com.example.projectbackend.DALInterfaces.ICharityDAL;
import com.example.projectbackend.DALInterfaces.IDonationDAL;
import com.example.projectbackend.Model.Charity;
import com.example.projectbackend.Model.Donation;
import com.example.projectbackend.Model.DonationInfo;
import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.WebRowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonationDalJDBC extends JDBCRepository implements IDonationDAL {

    @Override
    public List<IDonation> getAllDonationsByCharity(int charityID) {

        List<IDonation> donations = new ArrayList<IDonation>();
        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT ID, user_ID, amount, date from donation WHERE charity_ID =?";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, charityID);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int userID = resultSet.getInt("user_ID");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

                IDonation donation = new Donation(id, charityID, userID, amount, date);
                donations.add(donation);
            }



        } catch (SQLException throwable) {System.out.println("Can't get all donations by charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return donations;
    }

    @Override
    public List<IDonation> getAllDonationsByUser(int userID){
        List<IDonation> donations = new ArrayList<IDonation>();
        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT ID, charity_ID, amount, date from donation WHERE user_ID =?";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int charityID = resultSet.getInt("charity_ID");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

                IDonation donation = new Donation(id, charityID, userID, amount, date);
                donations.add(donation);
            }



        } catch (SQLException throwable) {System.out.println("Can't get all donations by charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return donations;
    }

    @Override
    public int getNumberOfDonationsPerCharity(int charityId)
    {
        Connection connection = this.getDatabaseConnection();
        int numberOfDonations = 0;

        String sql = "SELECT COUNT(amount)  FROM donation WHERE charity_ID = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, charityId);

            ResultSet resultSet = statement.executeQuery();

             resultSet.next();
             numberOfDonations = resultSet.getInt("COUNT(amount)");


        } catch (SQLException throwable) {System.out.println("Cant get number of donations");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return numberOfDonations;
    }
    @Override
    public void DonateToCharity(double amount,int accountId,int charityId,Date date)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO donation (`charity_ID`, `user_ID`, `amount`,`date`) VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, charityId);
            statement.setInt(2, accountId);
            statement.setDouble(3, amount);
            statement.setDate(4,date);

            statement.executeUpdate();

        } catch (SQLException throwable) {throwable.toString();}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
    }

    @Override
    public double GetCurrentDonationByCharity(int charityId)
    {
        Connection connection = this.getDatabaseConnection();
        double currentDonation = 0;

        String sql = "SELECT current_donation FROM charity WHERE ID = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, charityId);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            currentDonation = resultSet.getInt("current_donation");


        } catch (SQLException throwable) {System.out.println("Ne sum swyrzan");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return currentDonation;
    }

    @Override
    public void UpdateCurrentDonationByCharity(double amount, int charityId)
    {
        Connection connection = this.getDatabaseConnection();
        double oldAmount = GetCurrentDonationByCharity(charityId);
        double newAmount = oldAmount += amount;

        String sql = "UPDATE `charity` SET `current_donation`= ?  WHERE ID = ?";
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newAmount);
            statement.setInt(2,charityId);

            statement.executeUpdate();
        }

        catch (SQLException throwable) {System.out.println("Ne sum swyrzan11");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
    }

    @Override
    public List<DonationInfo> getAllDonationsByAccountID(int accountId) {

        List<DonationInfo> donations = new ArrayList<DonationInfo>();
        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT a.ID, title, amount,date,iban FROM donation a, charity b WHERE a.charity_id=b.ID and user_ID = ? ORDER BY ID desc";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String charityName = resultSet.getString("title");
                String iban = resultSet.getString("iban");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

                DonationInfo donation = new DonationInfo(charityName,date,iban, amount);
                donations.add(donation);
            }



        } catch (SQLException throwable) {System.out.println("Can't get all donations by charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return donations;
    }

    @Override
    public List<DonationInfo> getRecentDonationsByAccountID(int accountId) {

        List<DonationInfo> donations = new ArrayList<DonationInfo>();
        Connection connection = this.getDatabaseConnection();


        String sql = "SELECT a.ID, title, amount,date,iban FROM donation a, charity b WHERE a.charity_id=b.ID and user_ID = ? ORDER BY a.ID desc LIMIT 3";


        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, accountId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String charityName = resultSet.getString("title");
                String iban = resultSet.getString("iban");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");

                DonationInfo donation = new DonationInfo(charityName,date,iban, amount);
                donations.add(donation);
            }



        } catch (SQLException throwable) {System.out.println("Can't get all donations by charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return donations;
    }

}
