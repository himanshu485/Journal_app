package in.shiblinux.learnspeing.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users" )

@Data
@Builder // in case of builder remove NoArgsConstructor
@NoArgsConstructor // include for test
@AllArgsConstructor
    public class User {
        @Id
        public ObjectId id;

        @Indexed(unique = true)  //fast searching on username in DB.
        @NonNull //lombok
        private String userName;
        @NonNull
        private  String passWord;

        private String email;
        private boolean sentimentAnalysis;

        @DBRef
        private List<JournalEntry> journalEntries= new ArrayList<>();  // add an empty list of journalEntry
        private  List<String> roles;                                   // what kind of role user have
    }
