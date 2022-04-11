package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.ICharityDAL;
import com.example.projectbackend.Model.Charity;
import com.example.projectbackend.Model.Video;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.ICharityService;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CharityService implements ICharityService {


    @Autowired
    ICharityDAL dal;

    public CharityService(ICharityDAL dal)
    {
      this.dal=dal;
    }



    @Override
    public ResponseEntity<ICharity> ReturnCharityByID(int id)
    {
        ICharity charity = dal.getCharityById(id);
        if (charity == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(charity);
        }
    }

    @Override
    public CompletableFuture<ResponseEntity> ReturnAllCharities(){
        CompletableFuture<List<ICharity>> charities = CompletableFuture.completedFuture(dal.getAllCharities());

        if(charities != null) {

            return charities.thenApply(ResponseEntity::ok);
        } else {

            return (CompletableFuture) ResponseEntity.notFound();
        }
    }

    @Override
    public ResponseEntity<List<Video>> ReturnVideosOfCharityByID(int id)
    {
        List<Video> link = dal.getVideoByCharityId(id);
        if (link == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(link);
        }
    }

    @Override
    public String ReturnPhotoOfCharityByID(int id)
    {
        String path = dal.getPhotoByCharityId(id);
        if (path == "")
        {
            return path;
        }
        else
        {
            return path;
        }
    }

//    @Override
//    public ResponseEntity<List<ISubscription>> ReturnSubscriptionByID(int id)
//    {
//        List<ISubscription> subs = dal.getSubscriptionsByUserID(id);
//        if (subs == null)
//        {
//            return ResponseEntity.notFound().build();
//        }
//        else
//        {
//            return ResponseEntity.ok().body(subs);
//        }
//    }

}
