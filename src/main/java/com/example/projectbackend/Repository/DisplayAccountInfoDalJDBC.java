package com.example.projectbackend.Repository;

import com.example.projectbackend.DALInterfaces.IDisplayAccountInfoDAL;
import com.example.projectbackend.Model.DisplayAccountInfo;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.Model.UserAccount;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DisplayAccountInfoDalJDBC extends JDBCRepository implements IDisplayAccountInfoDAL {


    private ArrayList<IDisplayAccountInfo> getAllEmptyDisplayUsers() {

        ArrayList<IDisplayAccountInfo> emptyDisplayAccounts = new ArrayList<IDisplayAccountInfo>();
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from user";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String iban = resultSet.getString("iban");

                IDisplayAccountInfo account = new DisplayAccountInfo(id, username, iban, firstName, lastName, email, new ArrayList<ISubscription>());



                emptyDisplayAccounts.add(account);
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

        return emptyDisplayAccounts;
    }



    private ArrayList<IDisplayAccountInfo> fillAllDisplayAccounts() {

        ArrayList<IDisplayAccountInfo> emptyDisplayAccounts = getAllEmptyDisplayUsers();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * from charity_subscribe "+
                "INNER JOIN charity ON charity_subscribe.charity_id = charity.ID " +
                "INNER JOIN charity_videos ON charity_subscribe.video_id = charity_videos.ID ";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                for (int i = 0; i < emptyDisplayAccounts.size(); i++) {


                    if(emptyDisplayAccounts.get(i).getID() == resultSet.getInt("user_id")) {

                        int subscription_id = resultSet.getInt("ID");
                        int charity_id = resultSet.getInt("charity_id");
                        String charity_name = resultSet.getString("title");
//                        //userID
                        String description = resultSet.getString("description");
                        String link = resultSet.getString("link");


                        emptyDisplayAccounts.get(i).addSub(new Subscription(subscription_id, charity_id, charity_name, emptyDisplayAccounts.get(i).getID(), description, link));


                    }

                }

            }


        } catch (SQLException throwable) {System.out.println("az li ne sym vyrzan");}
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return emptyDisplayAccounts;
    }

    @Override
    public List<IDisplayAccountInfo> getAllAccountsForDisplay() {

        return fillAllDisplayAccounts();

    }


}
