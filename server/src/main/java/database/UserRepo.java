package database;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface UserRepo extends MongoRepository<User,String> {
    ArrayList<User> findAll();

}
