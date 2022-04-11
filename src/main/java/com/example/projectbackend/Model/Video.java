package com.example.projectbackend.Model;

import lombok.Getter;
import lombok.Setter;

public class Video {

    @Getter @Setter
    int ID;

    @Getter @Setter
    int charityID;

    @Getter @Setter
    String videoLink;

    @Getter @Setter
    String description;

    public Video() {

    }

    public Video(int id, int charityID, String videoLink,  String description) {
        this.ID = id;
        this.charityID = charityID;
        this.videoLink = videoLink;
        this.description = description;
    }


}
