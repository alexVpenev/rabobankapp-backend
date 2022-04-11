package com.example.projectbackend.ServiceInterfaces;

import com.example.projectbackend.Model.Video;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICharityService {

    ResponseEntity<ICharity> ReturnCharityByID(int id);
    CompletableFuture<ResponseEntity> ReturnAllCharities();
     ResponseEntity<List<Video>> ReturnVideosOfCharityByID(int id);
    String ReturnPhotoOfCharityByID(int id);
    //ResponseEntity<List<ISubscription>> ReturnSubscriptionByID(int id);

}
