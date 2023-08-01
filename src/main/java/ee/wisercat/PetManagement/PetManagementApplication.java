package ee.wisercat.PetManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PetManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetManagementApplication.class, args);
    }

}
