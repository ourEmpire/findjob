package database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface JobRepo extends MongoRepository<Job,String> {
    ArrayList<Job> findAllById(String id);
    ArrayList<Job> findAll();
    ArrayList<Job> findAllByJobName(String jobName);
}
