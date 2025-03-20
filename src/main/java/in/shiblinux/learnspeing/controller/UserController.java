package in.shiblinux.learnspeing.controller;


import in.shiblinux.learnspeing.API.Response.WeatherResponse;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.services.UserServices;
import in.shiblinux.learnspeing.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController

@RequestMapping("/user")

public class UserController {

   @Autowired
   private UserServices userServices;

   @Autowired
   private WeatherService weatherService;

   @PutMapping()

    public ResponseEntity<?> updateUser(@RequestBody User usr){

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       // spring security saves user(authenticated user ) details in SecurityContextHolder so need of password

       User userInDB= userServices.findByUserName(authentication.getName());

           userInDB.setUserName(usr.getUserName());
           userInDB.setPassWord(usr.getPassWord()); // this password is already encoded as it is getting fetched form DB

           userServices.saveNewUser(userInDB);
       return new ResponseEntity<>(HttpStatus.CREATED);

   }


   @DeleteMapping()
    public ResponseEntity<?> deleteUser(){

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

       userServices.deleteByUserName(authentication.getName());
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

   }

    @GetMapping()
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse= weatherService.getWeather("Mumbai");
        String message="";
        if(weatherResponse !=null){
            message= "Temp feels like "+  weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("hi "+ message, HttpStatus.OK);
    }

}


