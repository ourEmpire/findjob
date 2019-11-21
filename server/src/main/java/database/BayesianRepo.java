package database;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface BayesianRepo extends MongoRepository<Bayesian,String> {
    ArrayList<Bayesian> findAll();
    ArrayList<Bayesian> findAllByType(String type);
}
