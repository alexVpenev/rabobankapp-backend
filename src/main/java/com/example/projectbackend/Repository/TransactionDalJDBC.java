package com.example.projectbackend.Repository;

import com.example.projectbackend.DALInterfaces.ITransactionDAL;
import com.example.projectbackend.Model.Transaction;
import com.example.projectbackend.Model.request.TransactionRequest;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Repository
public class
TransactionDalJDBC extends JDBCRepository implements ITransactionDAL {


    @Override
    public List<Transaction> getAllSentTransactions(int senderId) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT ID, sender_ID, receiver_ID, amount from transaction WHERE sender_ID =?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, senderId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int senderID = resultSet.getInt("sender_ID");
                int receiverID = resultSet.getInt("receiver_ID");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");

                Transaction transaction = new Transaction(id, senderID, receiverID, amount,description,date);
                transactions.add(transaction);
            }
        } catch (SQLException throwable) {System.out.println("Can't get all transactions by sender");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllReceivedTransactions(int receiverId) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT ID, sender_ID, receiver_ID, amount from transaction WHERE receiver_ID =?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, receiverId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int senderID = resultSet.getInt("sender_ID");
                int receiverID = resultSet.getInt("receiver_ID");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");

                Transaction transaction = new Transaction(id, senderID, receiverID, amount,description,date);
                transactions.add(transaction);
            }
        } catch (SQLException throwable) {System.out.println("Can't get all received transactions");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsByUser(int receiverId) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT transaction.ID, sender_ID, receiver_ID, amount, date, description, iban FROM transaction INNER JOIN user ON transaction.receiver_ID = user.ID WHERE receiver_ID = ? OR sender_ID = ? ORDER BY ID desc";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, receiverId);
            statement.setInt(2, receiverId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int senderID = resultSet.getInt("sender_ID");
                int receiverID = resultSet.getInt("receiver_ID");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                String iban = resultSet.getString("iban");

                Transaction transaction = new Transaction(id, senderID, receiverID, amount,description,date, iban);
                transactions.add(transaction);
            }
        } catch (SQLException throwable) {System.out.println("Can't get all received transactions");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return transactions;
    }

    @Override
    public void CreateTransaction(TransactionRequest transactionRequest, int senderId,int receiverId) {

        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO transaction ( `sender_ID`, `receiver_ID`, `amount`,`description`,`date`) VALUES ( ?, ?, ?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.setDouble(3, transactionRequest.getAmount());
            statement.setString(4,transactionRequest.getDescription());
            statement.setDate(5,transactionRequest.getDate());

            statement.executeUpdate();

        } catch (SQLException throwable) {}
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
    public void updateSenderBalance(int senderId, double newBalance) {

        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `user` SET `balance`= ?  WHERE ID =?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newBalance);
            statement.setInt(2, senderId);
            statement.executeUpdate();

        } catch (SQLException throwable) {}
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
    public void updateReceiverBalance(int receiverId, double newBalance) {

        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `user` SET `balance`= ?  WHERE ID =?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newBalance);
            statement.setInt(2, receiverId);
            statement.executeUpdate();

        } catch (SQLException throwable) {}
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
    public List<Transaction> getRecentTransactionsByUser(int receiverId) {

        List<Transaction> transactions = new ArrayList<Transaction>();
        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT transaction.ID, sender_ID, receiver_ID, amount, date, description, iban FROM transaction INNER JOIN user ON transaction.receiver_ID = user.ID WHERE receiver_ID = ? OR sender_ID = ? ORDER BY ID desc LIMIT 3";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, receiverId);
            statement.setInt(2, receiverId);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int senderID = resultSet.getInt("sender_ID");
                int receiverID = resultSet.getInt("receiver_ID");
                double amount = resultSet.getDouble("amount");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                String iban = resultSet.getString("iban");

                Transaction transaction = new Transaction(id, senderID, receiverID, amount,description,date, iban);
                transactions.add(transaction);
            }
        } catch (SQLException throwable) {System.out.println("Can't get all received transactions");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return transactions;
    }

}
