package controller;

import database.UserSet;
import database.User;
import helper.JWTUtil;
import helper.EmptyPassword;
import helper.MailService;
import message.Messages.Message;
import message.Messages;
import message.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("/users")
@RestController
public class UserCtl implements Ctl{
    @Autowired
    private UserSet us;
    private Message[] messages = new Messages().getMessages();

    @PostMapping("/login")
    public Message login(@RequestParam String id,
                        @RequestParam String password){
        Message msg = messages[Messages.POST];
        User user = us.getUserMap().get(id);
        if(user == null || !user.getPassword().equals(password)){
            msg.setCode(StateCode.NOT_FOUND)
                    .setMsg("id or password error")
                    .setData(null);
            return msg;
        }
        String token = new JWTUtil().sign(id);
        msg.setCode(StateCode.OK)
                .setMsg("login successfully")
                .setData(token);
        return msg;
    }

    @GetMapping
    public Message view(@RequestParam String id){
        User user = us.getUserMap().get(id);
        Message message = messages[Messages.GET];
        if(user==null){
            return message.setMsg("the user isn't found")
                                            .setCode(StateCode.NOT_FOUND)
                                            .setData(null);
        }

        return message.setMsg("operation successfully")
                        .setCode(StateCode.OK)
                        .setData(EmptyPassword.getUpdateUser(user));

    }
    @PostMapping("/create")
    public Message create(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email,
                         @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String sex,
                         @RequestParam Integer verify) {
        Message message = messages[Messages.POST];
        EmailCtl emailCtl = new EmailCtl();
        if(!emailCtl.check(email,verify)){
            return message.setCode(StateCode.FORBIDDEN)
                            .setMsg("验证码失效或错误！")
                            .setData(null);
        }
        String id;
        while(true) {
            //产生一个9位的随机数作为id
            id = String.valueOf(new Random().nextInt(1000000000 - 100000000) + 100000000);
            if(us.getUserMap().get(id) == null)break;
        }
        User newUser = new User(id,username,password,email,phone,sex);
        us.getUserMap().put(id,newUser);
        us.getUserList().add(newUser);
        return message.setMsg("option successfully")
                .setCode(StateCode.CREATED)
                .setData(EmptyPassword.getUpdateUser(newUser));
    }

    @PutMapping
    public Message update(@RequestParam String  id,
                         @RequestParam(required = false) String username,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         @RequestParam(required = false) String sex){
        Message message = messages[Messages.PUT];
        User user = us.getUserMap().get(id);
        if(user == null){
            return message.setMsg("the user isn't found")
                                            .setCode(StateCode.NOT_FOUND)
                                            .setData(null);
        }
        //记录用户在UserList中的位置，用与修改UserList中的用户信息
        int index = us.getUserList().indexOf(user);
        if(username != null)user.setUsername(username);
        if(email != null) user.setEmail(email);
        if(phone != null) user.setPhone(phone);
        if(sex != null) user.setSex(sex);
        us.getUserList().set(index,user);
        return message.setMsg("option successfully")
                        .setCode(StateCode.OK)
                        .setData(EmptyPassword.getUpdateUser(user));
    }

    @PutMapping("/{id}/update_password")
    public Message updatePassword(@PathVariable String id,
                                 @RequestParam String password,
                                 @RequestParam Integer verify){
        Message message = messages[Messages.PUT];
        EmailCtl emailCtl = new EmailCtl();
        User user = us.getUserMap().get(id);
        if(emailCtl.check(user.getEmail(),verify)){
            user.setPassword(password);
            return message.setCode(StateCode.OK)
                            .setMsg("update password successfully")
                            .setData(null);
        }
        return message.setCode(StateCode.FORBIDDEN)
                        .setMsg("验证码失效或错误！")
                        .setData(null);

    }
    
    @DeleteMapping
    public Message delete(@RequestParam String id,
                         @RequestParam String password){
        Message message = messages[Messages.DELETE];
        User user = us.getUserMap().get(id);
        if(user == null || !user.getPassword().equals(password)){
            return message.setMsg("the user isn't existed or the password is error")
                                            .setCode(StateCode.FORBIDDEN)
                                            .setData(null);
        }
        us.getUserMap().remove(id);
        us.getUserList().remove(user);
        us.getDeleteByIdList().add(id);
        return message.setMsg("option successfully")
                        .setCode(StateCode.OK)
                        .setData(EmptyPassword.getUpdateUser(user));
    }




}



