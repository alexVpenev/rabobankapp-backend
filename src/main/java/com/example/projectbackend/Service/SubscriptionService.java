package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.ISubscriptionDAL;
import com.example.projectbackend.Model.Subscription;
import com.example.projectbackend.Repository.SubscriptionDalJDBC;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import com.example.projectbackend.ServiceInterfaces.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    ISubscriptionDAL dal;

    SubscriptionService(SubscriptionDalJDBC dal) { this.dal = dal; }

    @Override
    public ResponseEntity<List<ISubscription>> ReturnSubscriptionByID(int id)
    {
        List<ISubscription> subs = dal.getSubscriptionsByUserID(id);
        if (subs == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok().body(subs);
        }
    }

    @Override
    public void Subscribe(int charityId, int userId, int videoId, String description)
    {
        dal.Subscribe(charityId, userId, videoId, description);
    }

//    @Override
//    public void DeleteSub(int userId, int subId) {
//
//        dal.deleteSubscriptionWithCheck(userId, subId);
//
//    }

}
