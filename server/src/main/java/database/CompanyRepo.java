package database;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface CompanyRepo extends MongoRepository<Company,String> {
    ArrayList<Company> findAllByJobName(String JobName);
    ArrayList<Company> findAll();
    ArrayList<Company> findAllByType(String type);
}
