package com.example.projectbackend.ServiceInterfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISubscriptionService {

    ResponseEntity<List<ISubscription>> ReturnSubscriptionByID(int id);
    void Subscribe(int charityId, int userId, int videoId, String description);
//    void DeleteSub(int userId, int subId);

}
