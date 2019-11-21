package tool;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import database.Bayesian;
import database.BayesianSet;
import database.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class JobInfoHandle {
    @Autowired
    JobRepo jobRepo;
    @Autowired
    BayesianSet bayesianSet;


    public void test(){

    }


    public void as(String jobInfo,String type){
        ArrayList<String> proficiencies = bayesianSet.getBayesianHashMap().get(type).getProficiencies();
        ArrayList<String> technologies = bayesianSet.getBayesianHashMap().get(type).getTechnologies();
        for(String proficiency: proficiencies) {
            while(true){
                int index = jobInfo.indexOf(proficiency);
                if(index < 0) break;

            }
        }
    }






}
