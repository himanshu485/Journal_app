package in.shiblinux.learnspeing.services;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserServices {



    @Autowired
    private UserRepo userRepo; //repo


    @Autowired
    @Qualifier("passwordEncoderX")  //if ore than instances of password encoder exist.
    public PasswordEncoder passwordEncoder;

    private  static  final Logger logger= LoggerFactory.getLogger(UserServices.class);

    // this will encode the password and not be used with existing user
    public boolean saveNewUser(User usr) {
        try {
            usr.setPassWord(passwordEncoder.encode(usr.getPassWord()));
            usr.setRoles(Arrays.asList("USER"));
            userRepo.save(usr);
            return true;
        } catch (Exception e) {
//            logger.warn("User Already exists");
            logger.error("user already exists");
            log.error("user already exists {} {} ",usr.getUserName(),usr.getId(),e);
            return false;
        }
    }

    // should be used when user is exists in db
    public void saveUser (User usr){
        userRepo.save(usr);
    }

    // this will create a new admin user
    public void saveNewAdmin (User usr){
        usr.setPassWord(passwordEncoder.encode(usr.getPassWord()));
        usr.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(usr);
    }

    public Optional<User> findById(ObjectId id){
        return  userRepo.findById(id);
    }

    public User findByUserName(String userName){

       return userRepo.findByUserName(userName);
    };

    public  void deleteById (ObjectId id){
        userRepo.deleteById(id);

    }

    public  void deleteByUserName(String username){
        userRepo.deleteByUserName(username);
    }
    public List<User> getAll(){
        return  userRepo.findAll();
    }
}
