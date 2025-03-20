package in.shiblinux.learnspeing.Cache;

import in.shiblinux.learnspeing.entity.ConfigJournalAppEntity;
import in.shiblinux.learnspeing.repository.ConfigJournalRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppCache {


//    good practice

    public enum keys{
        WEATHER_API
    }

    @Autowired
    private ConfigJournalRepo configJournalRepo;

    public Map<String,String> appChache;


    @PostConstruct
    public void init(){  // we can also set a cron job and can call this init method
        appChache=new HashMap<>(); // re-init if init method called.
        List<ConfigJournalAppEntity> all= configJournalRepo.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: all){
            appChache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
       }
    }

}
