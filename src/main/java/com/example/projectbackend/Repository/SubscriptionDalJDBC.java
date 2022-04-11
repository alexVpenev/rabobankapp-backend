package com.example.projectbackend.Repository;

import com.example.projectbackend.DALInterfaces.ISubscriptionDAL;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriptionDalJDBC extends JDBCRepository implements ISubscriptionDAL {

    @Override
    public List<ISubscription> getSubscriptionsByUserID(int id) {

        String sql = "SELECT charity_subscribe.ID, charity_subscribe.charity_id, charity.title, charity_subscribe.description, charity_videos.link " +
                "FROM \"charity_subscribe\" " +
                "INNER JOIN \"charity_videos\" ON charity_subscribe.video_id = charity_videos.ID " +
                "INNER JOIN \"charity\" ON charity_subscribe.charity_id = charity.ID " +
                "WHERE charity_subscribe.user_id = ?";


        //List<ISubscription> subs = new ArrayList<Subscription>();
        List<ISubscription> subs = new ArrayList<ISubscription>();
        Connection connection = this.getDatabaseConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                int subscription_id = resultSet.getInt("ID");
                int charity_id = resultSet.getInt("charity_id");
                String charity_name = resultSet.getString("title");
                //userID
                String description = resultSet.getString("description");
                String link = resultSet.getString("link");

                subs.add(new Subscription(subscription_id, charity_id, charity_name, id, description, link));
            }

        }
        catch (SQLException throwable) {System.out.println("Can't get video of charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return subs;
    }

    @Override
    public void Subscribe(int charityId, int userId, int videoId, String description)
    {
        Connection connection = this.getDatabaseConnection();
        String sql = "INSERT INTO \"charity_subscribe\" (\"charity_id\", \"user_id\", \"video_id\", description) VALUES (?,?,?,?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, charityId);
            statement.setInt(2, userId);
            statement.setInt(3, videoId);
            statement.setString(4, description);

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


}
