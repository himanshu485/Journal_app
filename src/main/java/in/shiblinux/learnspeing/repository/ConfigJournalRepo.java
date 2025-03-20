package in.shiblinux.learnspeing.repository;

import in.shiblinux.learnspeing.entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// spring writes the implementation of this class at run time.
public interface ConfigJournalRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {


}


