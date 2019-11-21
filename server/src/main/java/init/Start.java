package init;

import database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tool.JobInfoHandle;


@Order(1)
@Component
public class Start implements CommandLineRunner {
    @Autowired
    private UserSet us;
    //@Autowired
   //private BayesianSet bs;
    @Autowired
    JobInfoHandle jih;
    @Override
    public void run(String... args) {
        us.init();
        //bs.init();
        jih.test();
    }
}
