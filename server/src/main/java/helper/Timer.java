package helper;

import config.CrawlerConfig;
import database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tool.JobCrawler;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Timer {
    @Autowired
    private UserSet us;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private  JobRepo jobRepo;
    @Autowired
    private JobCrawler crawler;
    @Autowired
    private CrawlerConfig crawlerConfig;


    //每隔1h将缓存数据写入数据库
    @Scheduled(initialDelay = 1000 * 60 * 60L,fixedRate = 1000* 60 * 60L)
    public void UserInfoUpdate(){
        ArrayList<User> userList = us.getUserList();
        userList.clear();
        for(User user : us.getUserMap().values()){
            userList.add(user);
        }
        userRepo.saveAll(userList);
        ArrayList<String> deleteByIdList = us.getDeleteByIdList();
        for(String id : deleteByIdList){
            userRepo.deleteById(id);
        }
        deleteByIdList.clear();
    }



    @Scheduled(cron = "0 0 3 * * ?")
    public void CompanyInfoUpdate(){
        HashMap<String,String> job51 = crawlerConfig.getJob51();
        int maxPages = 10;
        for(String type: job51.keySet()) {
            System.out.println("type: " + type);
            if(type.equals("url"))continue;
            for(int i = 1;i < maxPages;i++) {
                try {
                    //拼接url
                    StringBuffer urlTemp = new StringBuffer(job51.get("url")).append(job51.get(type))
                            .append(",2,").append(i).append(".html");
                    String url = new String(urlTemp);
                    urlTemp.delete(0,urlTemp.capacity());
                    System.out.println("url: "+ url);
                    ArrayList<Company>companies = crawler.getCompanies(url,type);
                    companyRepo.saveAll(companies);
                }catch (Exception e){
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    @Scheduled(cron = "0 30 3 * * ?")
    public void JobInfoUpdate() {
        ArrayList<Company> companies = companyRepo.findAll();
        jobRepo.saveAll(crawler.getJobs(companies));
    }

}
