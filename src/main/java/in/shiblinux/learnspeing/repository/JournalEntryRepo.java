package in.shiblinux.learnspeing.repository;

import in.shiblinux.learnspeing.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface JournalEntryRepo  extends MongoRepository<JournalEntry, ObjectId>{


}
