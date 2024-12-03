package hello.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExceptionApplication {

	@Autowired
	ApplicationContext ac;

	public static void main(String[] args) {
		SpringApplication.run(ExceptionApplication.class, args);
	}

//	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			int beanDefinitionCount = ac.getBeanDefinitionCount();
			System.out.println("beanDefinitionCount = " + beanDefinitionCount);
			String[] beanDefinitionNames = ac.getBeanDefinitionNames();
			for (String beanDefinitionName : beanDefinitionNames) {
				System.out.println("beanDefinitionName = " + beanDefinitionName);
			}
		};
	}

//	@Bean
	ApplicationRunner applicationRunner2() {
		return args -> {
			BasicErrorController bean = ac.getBean(BasicErrorController.class);
			System.out.println("BasicErrorController.getClass = " + bean.getClass());
		};
	}

}
