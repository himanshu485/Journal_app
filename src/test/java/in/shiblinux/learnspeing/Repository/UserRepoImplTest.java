package in.shiblinux.learnspeing.Repository;

import in.shiblinux.learnspeing.repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Test
    public void testNewUser(){
        userRepoImpl.getUserForSA();
    }
}
