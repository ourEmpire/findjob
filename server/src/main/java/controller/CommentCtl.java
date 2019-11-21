package controller;

import config.HarmonyWordConfig;
import database.Comment;
import database.CommentRepo;
import message.Messages;
import message.Messages.Message;
import message.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/job-info/commits")
@RestController
public class CommentCtl implements Ctl {
    Messages messages = new Messages();
    @Autowired
    HarmonyWordConfig words;
    @Autowired
    CommentRepo commentRepo;
    @GetMapping
    public Message getAllComment(){
        Message message = messages.getMessages()[Messages.GET];
        return message.setCode(StateCode.OK)
                        .setMsg("option successfully")
                        .setData(commentRepo.findAll());

    }


    @PostMapping
    public Message postComment(@RequestParam String id,
                              @RequestParam String username,
                              @RequestParam String content){
        Message message = messages.getMessages()[Messages.POST];
        try {
            Long time = System.currentTimeMillis();
            Comment comment = new Comment(id, username, harmony(content), time);
            commentRepo.save(comment);
            return message.setCode(StateCode.OK)
                            .setMsg("option successfully")
                            .setData(null);
        }catch (Exception e){
            return message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                            .setMsg(e.getMessage())
                            .setData(null);
        }
    }


    //将不良评论和谐掉
    public String harmony(String content){
        for(String word : words.getWords()){
            int index = content.indexOf(word);
            while (index > 0) {
                StringBuffer buffer = new StringBuffer(content.substring(0, index));
                String right = content.substring(index + word.length());
                char[] chars = new char[word.length()];
                for (int i = 0; i < word.length(); i++) {
                    chars[i] = '*';
                }
                content = new String(buffer.append(chars).append(right));
                index = content.indexOf(word);
            }
        }
        return content;
    }



}

