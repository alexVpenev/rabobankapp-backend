package com.example.projectbackend.Repository;

import com.example.projectbackend.DALInterfaces.ICharityDAL;
import com.example.projectbackend.Model.Charity;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.Model.Video;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class  CharityDalJDBC extends JDBCRepository implements ICharityDAL {

    @Override
    public List<ICharity> getAllCharities() {

        List<ICharity> charities = new ArrayList<ICharity>();
        Connection connection = this.getDatabaseConnection();

        String sql = "SELECT * from charity";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);


            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                double currentDonation = resultSet.getDouble("current_donation");
                double goalDonation = resultSet.getDouble("goal_donation");
                String iban = resultSet.getString("iban");

                ICharity charity = new Charity(id,title, description,currentDonation,goalDonation,iban);
                charities.add(charity);

            }

        }

        catch (SQLException throwable) {

            System.out.println("Can't get all charities");
        }

        finally {
             try{
                 connection.commit();
                 connection.close();
             }
             catch (SQLException throwable){
                 System.out.println("Can't close connection");
             }
        }

        return charities;
    }
    @Override
    public ICharity getCharityById(int id) {

        String sql = "SELECT * from charity WHERE ID = ?";
        Connection connection = this.getDatabaseConnection();
        ICharity charity = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            int charityId = resultSet.getInt("ID");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            double currentDonation = resultSet.getDouble("current_donation");
            double goalDonation = resultSet.getDouble("goal_donation");
            String iban = resultSet.getString("iban");

            charity = new Charity(charityId,title,description,currentDonation,goalDonation,iban);



        } catch (SQLException throwable) {
            System.out.println("Can't get charity by id");

        }
        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }
        return charity;
    }
    @Override
    public List<Video> getVideoByCharityId(int id) {

        String sql = "SELECT * from charity_videos WHERE Charity_ID = ?";
        Connection connection = this.getDatabaseConnection();
        List<Video> videos = new ArrayList<Video>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
//                System.out.println(resultSet.getString("link"));

                int videoId = resultSet.getInt("ID");
                int charityId = resultSet.getInt("Charity_ID");
                String link = resultSet.getString("link");
                String description = resultSet.getString("description");

                videos.add(new Video(videoId, charityId, link, description));
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

        return videos;
    }

    @Override
    public String getPhotoByCharityId(int id) {

        String sql = "SELECT * from charity_photos WHERE charity_ID = ?";
        Connection connection = this.getDatabaseConnection();
        String path = "";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            path = resultSet.getString("path");


        }
        catch (SQLException throwable) {System.out.println("Can't get photo of charity");}

        finally {
            try{
                connection.commit();
                connection.close();
            }
            catch (SQLException throwable){
                System.out.println("Can't close connection");
            }
        }

        return path;
    }


}
