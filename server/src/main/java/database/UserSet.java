package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class UserSet {

    @Autowired
    private UserRepo userRepo;

    private  HashMap<String,User> userMap = new HashMap<>();
    private ArrayList<User> userList = null;
    private ArrayList<String> deleteByIdList = new ArrayList<>();

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<String> getDeleteByIdList() {
        return deleteByIdList;
    }

    public void setDeleteList(ArrayList<String> deleteByIdList) {
        this.deleteByIdList = deleteByIdList;
    }

    public void init(){
        for(User user : userList = userRepo.findAll()){
            userMap.put(user.getId(),user);
        }
    }


}
