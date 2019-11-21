package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="mail")
public class MailConfig {
    private String encoding;
    private String host;
    private String username;
    private String password;
    private Integer port;
    private String subject;
    private Boolean auth;
    private Boolean ssl;
    private String type;

    public MailConfig() {
    }

    public MailConfig(String encoding, String host, String username, String password, Integer port, String subject, Boolean auth, Boolean ssl, String type) {
        this.encoding = encoding;
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.subject = subject;
        this.auth = auth;
        this.ssl = ssl;
        this.type = type;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Boolean getSsl() {
        return ssl;
    }

    public void setSsl(Boolean ssl) {
        this.ssl = ssl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
