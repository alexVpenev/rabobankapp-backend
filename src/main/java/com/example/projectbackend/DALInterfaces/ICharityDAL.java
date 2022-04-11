package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.Model.Video;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ICharityDAL {

    List<ICharity> getAllCharities();
    ICharity getCharityById(int id);
    List<Video> getVideoByCharityId(int id);
    String getPhotoByCharityId(int id);
    //List<ISubscription> getSubscriptionsByUserID(int id);
}
