package com.example.projectbackend.Controller;

import com.example.projectbackend.Repository.DisplayAccountInfoDalJDBC;
import com.example.projectbackend.Repository.UserDalJDBC;
import com.example.projectbackend.Service.DisplayAccountService;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountService;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import com.example.projectbackend.ServiceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/search")
public class DisplayAccountController {

    @Autowired
    private IDisplayAccountService displayAccountService = new DisplayAccountService(new DisplayAccountInfoDalJDBC());

    @GetMapping()
    public ResponseEntity<List<IDisplayAccountInfo>> DisplayAllAccounts()
    {
        return displayAccountService.ReturnAllAccountsForDisplay();
    }

}
