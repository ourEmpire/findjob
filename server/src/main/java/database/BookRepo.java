package database;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.ArrayList;


public interface BookRepo extends MongoRepository<Book,String> {
    ArrayList<Book> findAll();
}
