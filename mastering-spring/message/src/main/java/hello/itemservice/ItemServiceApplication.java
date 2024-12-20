package hello.itemservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(ApplicationContext ac) {
		return args -> {
			int beanDefinitionCount = ac.getBeanDefinitionCount();
			System.out.println("beanDefinitionCount = " + beanDefinitionCount);
		};
	};

}
