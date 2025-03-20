package in.shiblinux.learnspeing.controller;


import in.shiblinux.learnspeing.entity.JournalEntry;
import in.shiblinux.learnspeing.entity.User;
import in.shiblinux.learnspeing.services.JournalEntryService;
import in.shiblinux.learnspeing.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController

@RequestMapping("/journal") // this becomes necessary to use during API call http//:...../journal
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserServices userServices;

    @GetMapping()

    //now get all journal entries of user
    public ResponseEntity<?> getAllJournalEntriesOfUser(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User usr= userServices.findByUserName(authentication.getName());
        if (usr==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<JournalEntry> all= usr.getJournalEntries(); // user specific entries.
        if(all!=null && !all.isEmpty()){ // check whether all is null or empty
            return  new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    //get journal entry by id
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getByID(@PathVariable ObjectId myId){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= userServices.findByUserName(authentication.getName());

        // now get the list of journal entries

        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).toList();
        //   List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<JournalEntry> thisEntry= journalEntryService.findById(myId);

            if(thisEntry.isPresent()){
                return new ResponseEntity<JournalEntry>(thisEntry.get(), HttpStatus.OK);
            }
        }
        return  new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
// return thisEntry.map(journalEntry -> new ResponseEntity<>(journalEntry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




    //post with response entity
    @PostMapping()
    public ResponseEntity<JournalEntry> createEntries(@RequestBody JournalEntry myEntry){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        try{
            journalEntryService.saveEntry(myEntry,authentication.getName());
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }
    }


    @Transactional
    @PutMapping("/id/{myId}")
    public ResponseEntity<?> putByPath(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= userServices.findByUserName(authentication.getName());
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).toList();
//     collect will only contain the entry of the user with provided ID.

    if(!collect.isEmpty()) {
        JournalEntry oldEntry= journalEntryService.findById(myId).orElse(null);

        if (oldEntry != null) {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());

            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
        }
    }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Transactional
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> removeByID(@PathVariable ObjectId myId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       boolean removed= journalEntryService.deleteById(myId,authentication.getName());
       if (removed) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}




