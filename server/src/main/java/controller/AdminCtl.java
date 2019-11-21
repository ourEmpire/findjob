package controller;

import database.UserSet;
import database.User;
import helper.EmptyPassword;
import message.Messages.Message;
import message.Messages;
import message.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/users/admin")
@RestController
public class AdminCtl {
    @Autowired
    UserSet us;

    Messages.Message[] messages = new Messages().getMessages();
    @GetMapping("/all")
    public Message all(){
        Message msg = messages[Messages.GET];

        msg.setMsg("operation successfully")
                .setData(EmptyPassword.getUserMap(us.getUserMap()))
                .setCode(StateCode.OK);
        return msg;
    }

    @PostMapping()
    public Message createNewUser(@RequestParam String id,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email,
                                @RequestParam(required = false) String phone,
                                @RequestParam String sex){
        Message message = messages[Messages.POST];
        HashMap<String,User> userMap = us.getUserMap();
        User user = userMap.get(id);
        if(user != null){
            return message.setCode(StateCode.FORBIDDEN)
                            .setMsg("the user"+id+"is existed")
                            .setData(null);
        }
        user = new User(id,username,password,email,phone,sex);
        try{
            return message.setCode(StateCode.OK)
                            .setMsg("option successfully")
                            .setData(EmptyPassword.getUpdateUser(user));
        }catch (Exception e){
            return message.setCode(StateCode.INTERNAL_SERVER_ERROR)
                            .setMsg("option failure")
                            .setData(null);
        }
    }

    @PutMapping
    public Message updateUserPassword(@RequestParam String id,
                                     @RequestParam(name = "password") String newPwd){
        Message message = messages[Messages.PUT];
        HashMap<String,User> users = us.getUserMap();
        User user = users.get(id);
        if(user == null){
            return message.setCode(StateCode.NOT_FOUND)
                            .setMsg("the user" + id + "not find")
                            .setData(null);
        }

        return message.setCode(StateCode.OK)
                        .setMsg("option successfully")
                        .setData(EmptyPassword.getUpdateUser(user));
    }



}
