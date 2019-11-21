package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class BayesianSet {
    @Autowired
    private BayesianRepo bayesianRepo;
    private HashMap<String,Bayesian> bayesianHashMap = new HashMap<>();

    public BayesianSet() {
    }

    public BayesianSet(HashMap<String, Bayesian> bayesianHashMap) {
        this.bayesianHashMap = bayesianHashMap;
    }

    public HashMap<String, Bayesian> getBayesianHashMap() {
        return bayesianHashMap;
    }

    public void setBayesianHashMap(HashMap<String, Bayesian> bayesianHashMap) {
        this.bayesianHashMap = bayesianHashMap;
    }

    public void init(){
        for(Bayesian bayesian: bayesianRepo.findAll()){
            bayesianHashMap.put(bayesian.getType(),bayesian);
        }
    }

}
