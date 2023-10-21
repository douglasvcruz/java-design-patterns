package one.digitalinnovation.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JavaDesignPatternsApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaDesignPatternsApplication.class, args);
    }
}
