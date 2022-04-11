package com.example.projectbackend.Repository;

import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.DALInterfaces.IAccountDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDalJDBC extends JDBCRepository implements IAccountDAL {


    @Override
    public List<IAccount> getAllAccounts() {

        ArrayList<IAccount> accounts = new ArrayList<IAccount>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from user";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String iban = resultSet.getString("iban");
                Double balance = resultSet.getDouble("balance");

                IAccount account = new UserAccount(id, username, password,email,firstName,lastName,balance,iban);
                accounts.add(account);
            }


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

        return accounts;
    }
    @Override
    public IAccount getAccountById(int id) {

        String sql = "SELECT * from user WHERE ID = ?" ;
        Connection connection = this.getDatabaseConnection();
        IAccount account = null;
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

                int accountId = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
            String iban = resultSet.getString("iban");
            Double balance = resultSet.getDouble("balance");

                account = new UserAccount(accountId, username, password,email,firstName,lastName,balance,iban);




        } catch (SQLException throwable) {System.out.println("Can't get account by id");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return account;
    }

    @Override
    public IAccount getAccountByUsername(String username) {

        String sql = "SELECT * from user WHERE username = ?" ;
        Connection connection = this.getDatabaseConnection();
        IAccount account = null;
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int accountId = resultSet.getInt("ID");
            String accountName = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String iban = resultSet.getString("iban");
            Double balance = resultSet.getDouble("balance");

            account = new UserAccount(accountId, accountName, password,email,firstName,lastName,balance,iban);



        } catch (SQLException throwable) {System.out.println("Can't get account by username");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return account;
    }
    @Override
    public IAccount getAccountByIban(String iban) {

        String sql = "SELECT * from user WHERE iban = ?" ;
        Connection connection = this.getDatabaseConnection();
        IAccount account = null;
        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, iban);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int accountId = resultSet.getInt("ID");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Double balance = resultSet.getDouble("balance");

            account = new UserAccount(accountId, username, password,email,firstName,lastName,balance,iban);




        } catch (SQLException throwable) {System.out.println("Can't get account by id");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return account;
    }

    @Override
    public boolean addAccount(IAccount account) {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO user (`ID`, `first_name`, `last_name`, `username`, `password`, `email`,`balance`,`iban`) VALUES (NULL, ?, ?, ?, ?, ?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, account.getFirstName());
            statement.setString(2, account.getLastName());
            statement.setString(3, account.getUsername());
            statement.setString(4, account.getPassword());
            statement.setString(5, account.getEmail());
            statement.setDouble(6,account.getBalance());
            statement.setString(7,account.getIban());



            statement.executeUpdate();
             return true;


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
        return false;
    }

    @Override
    public void editUserDetails(int id, String email, String firstName, String lastName) {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `user` SET `email` = ?, `first_name` = ?, `last_name` = ? WHERE `user`.`ID` = ?";


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, firstName);
            statement.setString(3, lastName);

            statement.setInt(4, id);

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



}
