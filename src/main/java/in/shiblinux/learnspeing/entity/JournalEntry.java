package in.shiblinux.learnspeing.entity;

//till we don't have DB so create a POJO

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import in.shiblinux.learnspeing.enums.Sentiments;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document (collection = "Journal_Entry") //to map  ths pojo with a collection
//
//@Getter
//@Setter

@Data
@NoArgsConstructor // to convert json to pojo no args constructor are required
//if we don't provide collection name so spring boot will search the collection with class name
public class JournalEntry {

    @Id private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiments sentiments;

}
