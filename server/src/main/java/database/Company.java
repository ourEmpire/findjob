package database;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    private String id;
    private String type;
    private String jobName;
    private String href;
    private String companyName;
    private String locate;
    private String salary;
    private String datetime;

    public Company() {
    }

    public Company(String id, String type, String jobName, String href, String companyName, String locate, String salary, String datetime) {
        this.type = type;
        this.id = id;
        this.jobName = jobName;
        this.href = href;
        this.companyName = companyName;
        this.locate = locate;
        this.salary = salary;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", jobName='" + jobName + '\'' +
                ", href='" + href + '\'' +
                ", companyName='" + companyName + '\'' +
                ", locate='" + locate + '\'' +
                ", salary='" + salary + '\'' +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
