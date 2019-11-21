package tool;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import database.Company;
import database.Job;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
@Component
public class JobCrawler {
    public ArrayList<Company> getCompanies(String url,String type)throws Exception {
        ArrayList<Company> companies = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        doc.outputSettings().charset("UTF-8");
        Elements elements = doc.select(".dw_table .el");
        elements.remove(0);
        elements.first().text();//抛空指针异常，控制页面不在爬取
        for (Element element : elements) {
            Elements els = element.select(".t1>span>a[title]");
            String jobName = els.attr("title");
            String href = els.attr("href");
            int index = href.lastIndexOf(".html");
            if(index < 0)continue;
            String id = href.substring(href.lastIndexOf("/") + 1,index);
            String companyName = element.select("span.t2>a[title]").attr("title");
            String locate = element.select("span.t3").text();
            int indexOf = locate.indexOf("-");
            if (indexOf > 0) {
                locate = locate.substring(0, indexOf);
            }
            String salary = element.select("span.t4").text();
            String datetime = element.select("span.t5").text();
            companies.add(new Company(id, type, jobName, href, companyName, locate, salary, datetime));
        }
        return companies;
    }
    //hrefs: job's href
    public ArrayList<Job> getJobs(ArrayList<Company> companies) {
        ArrayList<Job> jobs = new ArrayList<>();
        for (Company company : companies) {
            try {
                String url = company.getHref();
                String id = company.getId();
                String jobName = company.getJobName();
                Document doc = Jsoup.connect(url).get();
                String tag = doc.select(".tHeader .in .cn .msg[title]").attr("title");
                String[] tags = tag.split("[|]");
                String jobMsg = doc.select(".tBorderTop_box .job_msg").text();
                String companyMsg = doc.select(".tBorderTop_box .tmsg").text();
                jobs.add(new Job(id,jobName,tags,jobMsg,companyMsg));
                System.out.println("running");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for(Job job:jobs){
            System.out.println("job: "+job.toString());
        }
        return jobs;
    }

}
