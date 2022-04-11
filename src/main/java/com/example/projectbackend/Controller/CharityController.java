package com.example.projectbackend.Controller;

import com.example.projectbackend.Model.Video;
import com.example.projectbackend.Repository.CharityDalJDBC;
import com.example.projectbackend.Service.CharityService;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import com.example.projectbackend.ServiceInterfaces.ICharityService;
import com.example.projectbackend.ServiceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/charity")
public class CharityController {

    @Autowired
    private ICharityService charityService;


    @GetMapping("/{id}")
    public ResponseEntity<ICharity> GetCharityById(@PathVariable(value = "id") int id)
    {
        return charityService.ReturnCharityByID(id);
    }

    @GetMapping
    public CompletableFuture<ResponseEntity> GetAllCharities()
    {
        return charityService.ReturnAllCharities();
    }

    @GetMapping("/video/{id}")
    public ResponseEntity<List<Video>> GetVideoCharityById(@PathVariable(value = "id") int id)
    {
        return charityService.ReturnVideosOfCharityByID(id);
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> GetPhotoCharityById(@PathVariable(value = "id") int id)
    {
        String filename =  charityService.ReturnPhotoOfCharityByID(id);

        ByteArrayResource inputStream = null;
        //Path root = Paths.get("photos");

       // filename =  "C:\\Users\\Jordan\\Desktop\\Group project semester 3\\s05-group-3\\Project-back-end\\photos\\"+ filename;
     //   filename =  "..\\..\\..\\..\\..\\..\\Project-back-end\\photos\\"+ filename;
        try{
            String direcotry = new File("./" ).getCanonicalPath() + "/photos/" + filename;
           ;
            inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
                    direcotry)));
        }
        catch (Exception e){}

        return ResponseEntity.ok()
                .contentLength(inputStream.contentLength())
                .body(inputStream);
    }


}
