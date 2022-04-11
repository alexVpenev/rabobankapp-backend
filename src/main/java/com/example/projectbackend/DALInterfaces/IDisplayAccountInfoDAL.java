package com.example.projectbackend.DALInterfaces;

import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;

import java.util.List;

public interface IDisplayAccountInfoDAL {

    List<IDisplayAccountInfo> getAllAccountsForDisplay();

}
