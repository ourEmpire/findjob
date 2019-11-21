package controller;

import com.alibaba.fastjson.JSON;
import database.*;
import message.Messages;
import message.Messages.Message;
import message.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RestController
public class UserOpCtl {
    private static int MIN = 5;
    @Autowired
    CompanyRepo jcr;
    @Autowired
    JobRepo jr;
    @Autowired
    StudyWayRepo swr;
    @Autowired
    BookRepo br;
    @Autowired
    NodeRepo nr;

    private Message[] messages = new Messages().getMessages();


    @GetMapping("/jc")
    public String viewCompany(@RequestParam String jobName){

        Message message = messages[Messages.GET];
        //存储返回的页面信息
        ArrayList<Company> jcs = jcr.findAllByJobName(jobName);
        if(jcs == null){
            return JSON.toJSONString(message.setMsg("option successfully")
                                            .setCode(StateCode.NOT_FOUND)
                                            .setData(null));
        }
        ArrayList<Node> nodes = nr.findAll();
        Node retNode = null;
        for(Node node : nodes){
            if(node.getName().equals(jobName)){
                retNode = node;
                break;
            }
        }
        return JSON.toJSONString(message.setMsg("option successfully")
                                        .setCode(StateCode.NOT_FOUND)
                                        .setData(new Jc(retNode,jcs)));

    }

    @GetMapping("/job-info")
    public Message viewJobInfo(@RequestParam String id){
        Message message = messages[Messages.POST];
        ArrayList<Job> jobs;
        try {
            jobs = jr.findAllById(id);
            if(jobs == null){
                return message.setCode(StateCode.NOT_FOUND)
                            .setMsg("option failure")
                            .setData(null);
            }
            return message.setCode(StateCode.OK)
                        .setMsg("option successfully")
                        .setData(jobs);
          }catch (Exception e){
            return message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                        .setMsg("option failure")
                        .setData(e.getMessage());
        }
    }



    @GetMapping("/location")
    public Message getLocate() {
        Message message = messages[Messages.PUT];
        HashMap<String,Integer> count = new HashMap<>();
        ArrayList<Company> companies = jcr.findAll();
        for(Company company:companies){
            String locate = company.getLocate();
            String temp;
            //只得到省或直辖市名 如:杭州-余杭区  ----> 杭州
            int indexOf = locate.indexOf("-");
            if(indexOf > 0) {
                temp = locate.substring(0,indexOf);
            }else{
                temp = locate;
            }
            if(count.containsKey(temp)){
                count.put(temp,count.get(temp)+1);
            }else{
                count.put(temp,1);
            }
        }
        HashMap<String,Integer> ret = new HashMap<>();
        ret.put("其它",0);

        Iterable<String> it = count.keySet();
        for(String key : it){
            int mun = count.get(key);
            if(mun <= MIN){
                ret.put("其它",ret.get("其它")+mun);
                continue;
            }
            ret.put(key,mun);
        }
        System.out.println(ret);
        return message.setMsg("option successfully")
                    .setCode(StateCode.OK)
                    .setData(ret);
    }
    @GetMapping("/ways")
    public Message viewStudyWay(@RequestParam String jobName){
        Message message = messages[Messages.DELETE];
        ArrayList<StudyWay> studyWays = swr.findAll();
        for(StudyWay way : studyWays){
            if(way.getJobName().equals(jobName)){
                return message.setCode(StateCode.OK)
                                .setMsg("option successfully")
                                .setData(way);
            }
        }
        return message.setCode(StateCode.NOT_FOUND)
                        .setMsg(jobName + "is not find")
                        .setData(null);
    }

    //studyWay的测试版
    @GetMapping({"/study-ways"})
    public Message getNode(@RequestParam String jobName) {
        Message message = messages[Messages.GET];
        ArrayList<Node> nodes = nr.findAll();
        for(Node node : nodes){
            if(node.getName().equals(jobName)){
                return message.setCode(StateCode.OK)
                                .setMsg("option successfully")
                                .setData(node);
            }
        }
        return message.setCode(StateCode.NOT_FOUND)
                        .setMsg(jobName+"not find")
                        .setData(null);

    }
    @PostMapping("/books")
    public Message saveBook(@RequestBody String body){
        Message message = messages[Messages.POST];
        try {
            ArrayList<Book> books = (ArrayList<Book>) JSON.parseArray(body,Book.class);
            br.saveAll(books);
            return message.setMsg("option successfully")
                        .setCode(StateCode.OK)
                        .setData(null);
        }catch (Exception e){
            return message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                            .setMsg(e.getMessage())
                            .setData(null);
        }

    }
    //studyWay的测试版
    @PostMapping("/study-ways")
    public Message saveNode(@RequestBody String body){
        Message message = messages[Messages.POST];
        try {
            ArrayList<Node> nodes = (ArrayList<Node>) JSON.parseArray(body, Node.class);
            for (Node node : nodes) {
                System.out.println("node:" + node);
                nr.save(node);
            }
            return message.setCode(StateCode.OK)
                            .setMsg("option successfully")
                            .setData(null);
        }catch (Exception e) {
            return message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                            .setMsg(e.getMessage())
                            .setData(null);
        }
    }

    private class Jc{
        public Node node;
        public ArrayList<Company> companies;

        public Jc() {
        }

        public Jc(Node node, ArrayList<Company> companies) {
            this.node = node;
            this.companies = companies;
        }
    }

}
