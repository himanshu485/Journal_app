package in.shiblinux.learnspeing.controller;
import in.shiblinux.learnspeing.Cache.AppCache;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private AppCache appCache;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userServices.getAll();
        if (all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-new-admin")
    public String createAdminUser(@RequestBody User usr){
        userServices.saveNewAdmin(usr);
        return ("new admin created : Name " + usr.getUserName());
    }

    @GetMapping("clear-app-cache")
    public  void clearAppCache(){
        appCache.init();;
    }

}
