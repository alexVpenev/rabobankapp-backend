package com.example.projectbackend;

import com.example.projectbackend.Controller.AccountController;
import com.example.projectbackend.Service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;

@SpringBootApplication
public class ProjectBackEndApplication  {

   /* @Resource
    FileStorageService storageService;*/

    public static void main(String[] args) {

        SpringApplication.run(ProjectBackEndApplication.class, args);

    }

    /*@Override
    public void run(String... arg) throws Exception {
     //   storageService.deleteAll();
       // storageService.init();
    }*/
}
