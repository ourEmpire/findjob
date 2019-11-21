package database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface NodeRepo extends MongoRepository<Node,String> {
    ArrayList<Node> findAll();
}
