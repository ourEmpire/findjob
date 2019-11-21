package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
@Entity
public class StudyWay {
    @Id
    private String jobName;
    private HashMap<Integer,Book> ways;
    private HashMap<String,String> courses;


    public StudyWay() {
    }


    public StudyWay(String jobName, HashMap<Integer, Book> ways,HashMap<String,String>courses) {
        this.jobName = jobName;
        this.ways = ways;
        this.courses = courses;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public HashMap<Integer, Book> getWays() {
        return ways;
    }

    public void setWays(HashMap<Integer, Book> ways) {
        this.ways = ways;
    }

    public HashMap<String, String> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<String, String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudyWay{" +
                "jobName='" + jobName + '\'' +
                ", ways=" + ways +
                ", courses=" + courses +
                '}';
    }
}
