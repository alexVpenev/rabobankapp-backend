package com.example.projectbackend.Service;

import com.example.projectbackend.DALInterfaces.IDisplayAccountInfoDAL;
import com.example.projectbackend.DALInterfaces.IDonationDAL;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.IDisplayAccountService;
import com.example.projectbackend.ServiceInterfaces.IDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayAccountService implements IDisplayAccountService {

    @Autowired
    IDisplayAccountInfoDAL dal;

    public DisplayAccountService(IDisplayAccountInfoDAL dal)
    {
        this.dal=dal;
    }

    @Override
    public ResponseEntity<List<IDisplayAccountInfo>> ReturnAllAccountsForDisplay(){
        List<IDisplayAccountInfo> displayAccountInfos = dal.getAllAccountsForDisplay();

        if (displayAccountInfos == null){
            return ResponseEntity.notFound().build();
        }
        else{
            return ResponseEntity.ok().body(displayAccountInfos);
        }

    }

}
