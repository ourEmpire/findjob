package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@ConfigurationProperties(prefix="crawler")
public class CrawlerConfig {
    private HashMap<String,String> job51;
    private ArrayList<String> douban;
    public CrawlerConfig() {
    }

    public CrawlerConfig(HashMap<String, String> job51,ArrayList<String> douban) {
        this.job51 = job51;
        this.douban = douban;
    }

    public HashMap<String, String> getJob51() {
        return job51;
    }

    public void setJob51(HashMap<String, String> job51) {
        this.job51 = job51;
    }

    public ArrayList<String> getDouban() {
        return douban;
    }

    public void setDouban(ArrayList<String> douban) {
        this.douban = douban;
    }
}
