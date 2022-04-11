package com.example.projectbackend.CharityTests;

import com.example.projectbackend.Model.Charity;
import com.example.projectbackend.Model.Video;
import com.example.projectbackend.Repository.CharityDalJDBC;
import com.example.projectbackend.Service.CharityService;
import com.example.projectbackend.ServiceInterfaces.IAccount;
import com.example.projectbackend.ServiceInterfaces.ICharity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharityServiceUnitTest {

    @Autowired
    CharityService charityService;

    @MockBean
    CharityDalJDBC charityDalJDBC;

//    @Test
//    public void getCharityByIdTest(){
//        int id =1;
//        Charity charity = new Charity();
//
//        when(charityDalJDBC.getCharityById(id))
//                .thenReturn(charity);
//
//        Assertions.assertEquals(new ResponseEntity(charity, HttpStatus.OK),charityService.ReturnCharityByID(id));
//    }
//
//    @Test
//    public void getCharityByIdFailTest(){
//        int id =1;
//        Charity charity = new Charity();
//
//        when(charityDalJDBC.getCharityById(id))
//                .thenReturn(null);
//
//        Assertions.assertEquals(new ResponseEntity( HttpStatus.NOT_FOUND),charityService.ReturnCharityByID(id));
//    }
//
//    @Test
//    public void getAllCharitiesTest(){
//        List<ICharity> charities = new ArrayList<>();
//        Charity charity = new Charity();
//        charities.add(charity);
//
//        when(charityDalJDBC.getAllCharities())
//                .thenReturn(Stream.of(charity)
//                        .collect(Collectors.toList()));
//
//        Assertions.assertEquals(new ResponseEntity(charities,HttpStatus.OK),charityService.ReturnAllCharities());
//
//    }
//
//    @Test
//    public void getAllCharitiesFailTest()  {
//
//        when(charityDalJDBC.getAllCharities()).thenReturn(null);
//        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),charityService.ReturnAllCharities());
//
//    }
//
//    @Test
//    public void getVideosOfCharityByIdTest(){
//
//        int id =1;
//        List<Video> links = new ArrayList<>();
//        Video video = new Video();
//        links.add(video);
//
//        when(charityDalJDBC.getVideoByCharityId(1))
//                .thenReturn(Stream.of(video)
//                        .collect(Collectors.toList()));
//
//        Assertions.assertEquals(new ResponseEntity(links,HttpStatus.OK),charityService.ReturnVideosOfCharityByID(id));
//    }
//
//    @Test
//    public void getVideosOfCharityByIdFailTest()  {
//
//        int id = 1;
//        when(charityDalJDBC.getVideoByCharityId(id)).thenReturn(null);
//        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),charityService.ReturnVideosOfCharityByID(id));
//
//    }
//
//    @Test
//    public void getPhotoOfCharityByIdTest(){
//        int id =1;
//        String path ="string";
//
//        when(charityDalJDBC.getPhotoByCharityId(id))
//                .thenReturn(path);
//
//        Assertions.assertEquals(new ResponseEntity(path,HttpStatus.OK),charityService.ReturnPhotoOfCharityByID(id));
//    }
//
//    @Test
//    public void getPhotoOfCharityByIdFailTest(){
//        int id =1;
//        String path ="string";
//
//        when(charityDalJDBC.getPhotoByCharityId(id))
//                .thenReturn("");
//
//        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),charityService.ReturnPhotoOfCharityByID(id));
//    }

}
