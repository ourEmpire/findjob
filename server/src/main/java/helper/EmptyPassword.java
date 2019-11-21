package helper;

import database.User;

import java.util.HashMap;

//将用户密码信息隐藏掉
public class EmptyPassword {
    public static User getUpdateUser(User user){
        return user.setPassword(null);
    }

    public static HashMap<String,User> getUserMap(HashMap<String,User> users){
        HashMap<String,User> retUser = new HashMap<>();
        for(User user : users.values()){
            user.setPassword(null);
            retUser.put(user.getId(),user);
        }
        return retUser;
    }


}
