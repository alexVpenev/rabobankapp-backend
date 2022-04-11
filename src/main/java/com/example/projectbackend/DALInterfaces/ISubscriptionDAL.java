package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.ServiceInterfaces.ISubscription;

import java.util.List;

public interface ISubscriptionDAL {

    List<ISubscription> getSubscriptionsByUserID(int id);
    void Subscribe(int charityId, int userId, int videoId, String description);
//    void deleteSubscriptionWithCheck(int userId, int subId);

}
