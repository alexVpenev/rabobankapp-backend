package com.example.projectbackend.Controller;


import com.example.projectbackend.Model.request.DeleteSubscribeRequest;
import com.example.projectbackend.Model.request.DonationRequest;
import com.example.projectbackend.Model.request.SubscribeRequest;
import com.example.projectbackend.Repository.UserDalJDBC;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/subscribe")
public class SubscriptionController {

    @Autowired
    private ISubscriptionService subscriptionService;

    @Autowired
    private IUserService userService;


    @GetMapping("/personal")
    public ResponseEntity<List<ISubscription>> GetSubscriptionsById()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        IAccount account = userService.GetAccountByUsername(currentPrincipalName);

        return subscriptionService.ReturnSubscriptionByID(account.getId());

    }

    @PostMapping()
    public ResponseEntity Subscribe(@RequestBody SubscribeRequest subscribeRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        IAccount account = userService.GetAccountByUsername(currentPrincipalName);

        int accountId = account.getId();

        subscriptionService.Subscribe(subscribeRequest.getCharityID(), accountId, subscribeRequest.getVideoID(), subscribeRequest.getDescription());
        return ResponseEntity.ok().build();
    }

//    @PostMapping(value="/delete")
//    public ResponseEntity deleteSubscription(@RequestBody DeleteSubscribeRequest deleteSubscribeRequest) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        IAccount account = userService.GetAccountByUsername(currentPrincipalName);
//
//        int accountId = account.getId();
//
//        subscriptionService.DeleteSub(accountId, deleteSubscribeRequest.getSubID());
//
//        return ResponseEntity.ok().build();
//
//    }


}
