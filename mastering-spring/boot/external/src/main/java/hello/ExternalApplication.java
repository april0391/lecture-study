package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalApplication.class, args);
        for (String arg : args) {
            System.out.println("arg = " + arg);
        }
//        JavaSystem.print();
    }

}
