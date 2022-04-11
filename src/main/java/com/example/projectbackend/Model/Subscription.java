package com.example.projectbackend.Model;

import com.example.projectbackend.ServiceInterfaces.ISubscription;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Subscription implements ISubscription {

    @Getter @Setter
    int ID;

    @Getter @Setter
    int charity_id;

    @Getter @Setter
    String charity_name;

    @Getter @Setter
    int user_id;

    @Getter @Setter
    String description;

    @Getter @Setter
    String videoLink;

    public Subscription() {

    }

    public Subscription(int id, int charity_id, String charity_name, int user_id, String description, String videoLink) {
        this.ID = id;
        this.charity_id = charity_id;
        this.charity_name = charity_name;
        this.user_id = user_id;
        this.description = description;
        this.videoLink = videoLink;
    }

}
