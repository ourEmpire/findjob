package server;

import kotlin.Suppress;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"controller","database","init","helper","config","tool"})
@EntityScan({"database","config"})
@Suppress(names = "UNCHECKED_CAST")
@EnableScheduling
@EnableMongoRepositories("database")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
