package com.example.projectbackend.Controller;


import com.example.projectbackend.Model.FileInfo;
import com.example.projectbackend.Model.request.UserCreateRequest;
import com.example.projectbackend.Model.request.UserEditDetailsRequest;
import com.example.projectbackend.Repository.UserDalJDBC;
import com.example.projectbackend.Service.FileStorageService;
import com.example.projectbackend.Service.UserService;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.IUserService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/account")
public class  AccountController {

    //private IUserService userService = new UserService(new UserDalJDBC());

    @Autowired
    private IUserService userService = new UserService(new UserDalJDBC());

    @Autowired
    FileStorageService storageService;

    @GetMapping
    public CompletableFuture<ResponseEntity> GetAllAccounts()
    {
        return userService.getAllAccounts();

    }

    @GetMapping("/{id}")
    public  ResponseEntity<IAccount> getAccountById(@PathVariable(value = "id") int id)
    {
        return  userService.ReturnAccountById(id);
    }


    @GetMapping("/user")
    public ResponseEntity<IAccount> GetAccountByUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return userService.ReturnAccountByUsername(currentPrincipalName);
    }

    @PostMapping("/register")
    public ResponseEntity UserRegistration(@RequestBody UserCreateRequest userCreateRequest) {

        userService.UserRegistration(userCreateRequest);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/upload/photo")
    public ResponseEntity UploadPhoto(@RequestParam("file") MultipartFile file)
    {
        try{
            storageService.save(file);
            return ResponseEntity.ok().body("File uploaded");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
         ByteArrayResource inputStream = null;
         Path root = Paths.get("photos");
         filename =  "C:\\Users\\Jordan\\Desktop\\Group project semester 3\\s05-group-3\\Project-back-end\\photos\\"+ filename;
     try{
       inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(
               filename)));
      }
catch (Exception e){}

        return ResponseEntity.ok()
                .contentLength(inputStream.contentLength())
                .body(inputStream);

     /*   File file = storageService.load(filename);
        return ResponseEntity.ok().body(file);*/
    }



    @GetMapping("/photos")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(AccountController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(fileInfos);
    }


    @PostMapping("/edit-details")
    public ResponseEntity EditUserDetails(@RequestBody UserEditDetailsRequest userEditDetailsRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        userService.EditUserDetails(currentPrincipalName, userEditDetailsRequest);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/login/restricted")
    public String restricted(){
        return "ura";
    }

    @GetMapping("/get-oauth-user")
    @ResponseBody
    public Principal user(Principal principal){
        return principal;
    }
}
