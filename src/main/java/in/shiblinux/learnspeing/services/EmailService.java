package in.shiblinux.learnspeing.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    //we will be using java mail sender.
    @Autowired
    private JavaMailSender javaMailSender; // interface and auto implemented



    public  void sendEmail(String to , String sub, String body){

      try{
          SimpleMailMessage mail =new SimpleMailMessage();
          mail.setTo(to);
          mail.setSubject(sub);
          mail.setText(body);
          javaMailSender.send(mail);
      }
      catch(Exception e) {
        log.error("Exception while sending mails", e.getCause());
        }


    }
}
