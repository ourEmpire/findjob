package database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface StudyWayRepo extends MongoRepository<StudyWay,String> {
    ArrayList<StudyWay> findAll();
}
