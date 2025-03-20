package in.shiblinux.learnspeing.services;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*; // static import to import static diractilty


@SpringBootTest
public class UserServicesTest {

    @Autowired
    public UserRepo userRepo;

//    @Test // normal assertion
    @Disabled
    @ParameterizedTest
    @ValueSource(strings ={
            "mohit",
            "manoj",
            "tara"
    })

    public void  testFindByUserName(String name){
        assertNotNull(userRepo.findByUserName(name),"user not found "+ name);
    }

    @Autowired
    public  UserServices userServices;


    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewEntry(User user){
        assertTrue(userServices.saveNewUser(user));
    }

}