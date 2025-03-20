package in.shiblinux.learnspeing.services;

import in.shiblinux.learnspeing.entity.JournalEntry;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo; //repo

    @Autowired
    private UserServices userServices;



    // this will be used when there already exist an entry for the user.
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userServices.findByUserName(userName); //get user

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(savedEntry); //now set entry to the user as well
            userServices.saveUser(user);             // check for its importance (saving the get user)
        } catch (Exception e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

// overridden saveEntry

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    ;

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }


    public boolean deleteById(ObjectId id, String userName) {

        boolean removed=false;
        try {
            User user = userServices.findByUserName(userName); //this username is got by Spring Security
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));// journalEntry ki list me se remove kar do ek entry when entry.getId == id

            if (removed) {   // it will check if the user has the journal entry of
                userServices.saveUser(user); // save updated user. not saveNewUser
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
    return removed;
    }
}