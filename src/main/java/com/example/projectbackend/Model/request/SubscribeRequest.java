package com.example.projectbackend.Model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SubscribeRequest {

    @Getter @Setter
    int charityID;

    @Getter @Setter
    int videoID;

    @Getter @Setter
    String description;

}
