package in.shiblinux.learnspeing.services;


import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;

import static  org.mockito.Mockito.*;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserServices userServices;

    @BeforeEach
    void doThis(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void loadUserByUserNameTest(){
            when(userServices.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").passWord("ram").roles(Arrays.asList("USER")).build());
        UserDetails UserX=userDetailsServiceImpl.loadUserByUsername("Ram"); //provide the same name here.
        Assertions.assertNotNull(UserX);  //not an static import so used Assertions.assert
    }
}

/*

package in.shiblinux.learnspeing.services;


import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Arrays;

import static  org.mockito.Mockito.*;

 @SpringBootTest
public class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
// @Autowired
    private UserServices userServices;

//    @BeforeEach
//    void doThis(){
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void loadUserByUserNameTest(){
            when(userServices.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").passWord("ram").roles(Arrays.asList("USER")).build());
        UserDetails UserX=userDetailsServiceImpl.loadUserByUsername("Ram"); //provide the same name here.
        Assertions.assertNotNull(UserX);  //not an static import so used Assertions.assert
    }
}*/
