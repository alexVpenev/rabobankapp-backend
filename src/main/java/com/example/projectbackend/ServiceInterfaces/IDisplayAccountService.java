package com.example.projectbackend.ServiceInterfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDisplayAccountService {

    ResponseEntity<List<IDisplayAccountInfo>> ReturnAllAccountsForDisplay();

}
