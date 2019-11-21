package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Job {
    @Id
    private String id;
    private String jobName;
    private String[] tags;
    private String jobMsg;
    private String companyMsg;


    public Job() {
    }

    public Job(String id, String jobName, String[] tags, String jobMsg, String companyMsg) {
        this.id = id;
        this.jobName = jobName;
        this.tags = tags;
        this.jobMsg = jobMsg;
        this.companyMsg = companyMsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getJobMsg() {
        return jobMsg;
    }

    public void setJobMsg(String jobMsg) {
        this.jobMsg = jobMsg;
    }

    public String getCompanyMsg() {
        return companyMsg;
    }

    public void setCompanyMsg(String companyMsg) {
        this.companyMsg = companyMsg;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", jobName='" + jobName + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", jobMsg='" + jobMsg + '\'' +
                ", companyMsg='" + companyMsg + '\'' +
                '}';
    }
}
