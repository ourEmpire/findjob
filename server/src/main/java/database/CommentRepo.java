package database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface CommentRepo extends MongoRepository<Comment,String> {
    ArrayList<Comment> findAll();
    ArrayList<Comment> findAllById(String id);
}
