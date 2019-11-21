package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
@ConfigurationProperties(prefix="comment")
public class HarmonyWordConfig {
    private ArrayList<String> words;

    public HarmonyWordConfig(ArrayList<String> words) {
        this.words = words;
    }

    public HarmonyWordConfig() {
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}
