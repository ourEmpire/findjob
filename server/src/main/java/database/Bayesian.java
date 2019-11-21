package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Bayesian {
    @Id
    //以company中的type作为id便于搜索
    private String type;
    //存储熟练度的分类
    private ArrayList<String> proficiencies;
    //存储技术
    private ArrayList<String> technologies;

    public Bayesian() {
    }

    public Bayesian(String type, ArrayList<String> proficiencies, ArrayList<String> technologies) {
        this.type = type;
        this.proficiencies = proficiencies;
        this.technologies = technologies;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(ArrayList<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public ArrayList<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(ArrayList<String> technologies) {
        this.technologies = technologies;
    }

    @Override
    public String toString() {
        return "Bayesian{" +
                "type='" + type + '\'' +
                ", proficiencies=" + proficiencies +
                ", technologies=" + technologies +
                '}';
    }
}
