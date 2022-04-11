package com.example.projectbackend.Model;

import com.example.projectbackend.ServiceInterfaces.IDisplayAccountInfo;
import com.example.projectbackend.ServiceInterfaces.ISubscription;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class DisplayAccountInfo implements IDisplayAccountInfo {

    @Getter @Setter
    int ID;
    @Getter @Setter
    String username;
    @Getter @Setter
    String iban;
    @Getter @Setter
    String firstName;
    @Getter @Setter
    String lastName;
    @Getter @Setter
    String email;
    @Getter @Setter
    List<ISubscription> subs;

    public DisplayAccountInfo() {

    }

    public DisplayAccountInfo(int user_id, String username, String iban, String firstName, String lastName, String email, List<ISubscription> subs) {

        this.ID = user_id;
        this.username = username;
        this.iban = iban;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.subs = subs;

    }

    @Override
    public void addSub(ISubscription sub) {
        subs.add(sub);
    }

}
