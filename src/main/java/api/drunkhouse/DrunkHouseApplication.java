package api.drunkhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DrunkHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrunkHouseApplication.class, args);
    }

}
