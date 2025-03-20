package in.shiblinux.learnspeing;


import in.shiblinux.learnspeing.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSerciceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public  void sendEmailForSATest(){

        emailService.sendEmail("mohitpandey22504@gmail.com","testmail","hello bahi");
    }
}
