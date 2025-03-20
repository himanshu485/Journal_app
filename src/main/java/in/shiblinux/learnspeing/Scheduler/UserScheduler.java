package in.shiblinux.learnspeing.Scheduler;

import in.shiblinux.learnspeing.entity.JournalEntry;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.enums.Sentiments;
import in.shiblinux.learnspeing.repository.UserRepoImpl;
import in.shiblinux.learnspeing.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;


    @Autowired
    private UserRepoImpl userRepoImpl;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendMail(){

       List<User> users =userRepoImpl.getUserForSA();  // get the users opted for sentiment analysis

        for (User user:users){
            List<JournalEntry> journalEntries= user.getJournalEntries();

            List<Sentiments> sentiments= journalEntries.stream().
                    filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS)))
                    .map(x->x.getSentiments()).collect(Collectors.toList());

            Map<Sentiments,Integer> sentimentCounts=new HashMap<>();

            for (Sentiments sentiment : sentiments){
                if(sentiment != null){
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }

            }

            Sentiments mostFrequentSentiment =null;
            int maxCount =0;

            for (Map.Entry<Sentiments,Integer> entry :  sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequentSentiment=entry.getKey();
                }
            }

            if(mostFrequentSentiment!=null){
                emailService.sendEmail(user.getEmail(),"Sentiment of last week",mostFrequentSentiment.toString());
            }
        }
    }
}
