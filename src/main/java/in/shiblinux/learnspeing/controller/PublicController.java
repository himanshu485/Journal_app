package in.shiblinux.learnspeing.controller;

import in.shiblinux.learnspeing.Utils.JwtUtils;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.services.UserDetailsServiceImpl;
import in.shiblinux.learnspeing.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j

public class PublicController {


    @Autowired
    private UserServices userServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/health-check")
    public String health_Check(){  // all such function that returns anything will convert into JSON
        return "Every thing is FINE";
    }


    @PostMapping("/signup")
    public void signup(@RequestBody User usr){
        userServices.saveNewUser(usr);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User usr){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usr.getUserName(),usr.getPassWord()));
            UserDetails userDetails= userDetailsService.loadUserByUsername(usr.getUserName());
            // Generate JWT token
            String jwt = jwtUtils.generateToken(userDetails.getUsername());
            // Return JWT token with HTTP 200 (OK) status
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }catch (Exception e){
                log.error("incorrect username or password ");
                return new ResponseEntity<>("incorrect username and password",HttpStatus.NOT_FOUND);
        }
    }
}
