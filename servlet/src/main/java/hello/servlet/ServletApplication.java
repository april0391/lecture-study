package hello.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	@Autowired
	ApplicationContext ac;

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			String[] beanDefinitionNames = ac.getBeanDefinitionNames();
			for (String beanDefinitionName : beanDefinitionNames) {
				Object bean = ac.getBean(beanDefinitionName);
				System.out.println("bean = " + bean);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

}
