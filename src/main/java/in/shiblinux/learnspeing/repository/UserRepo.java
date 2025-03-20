package in.shiblinux.learnspeing.repository;

import in.shiblinux.learnspeing.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// spring writes the implementation of this class at run time.
public interface UserRepo extends MongoRepository<User, ObjectId> {

//    @Query("{ 'userName' : ?0 }")
    User findByUserName(String userName); // declare this method

    void deleteByUserName(String userName);

}


