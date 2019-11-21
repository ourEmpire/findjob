package database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    private String id; //用户的id 或 email
    private String username;
    private String content;
    private Long time;//发送评论的时间戳

    public Comment() {
    }

    public Comment(String id, String username, String content, Long time) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
