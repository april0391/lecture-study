package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.order.OrderServiceImpl2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = SpringApplication.run(CoreApplication.class, args);
		OrderServiceImpl2 bean = ac.getBean(OrderServiceImpl2.class);
		Map<String, DiscountPolicy> discountPolicyMap = bean.getDiscountPolicyMap();
		for (String key : discountPolicyMap.keySet()) {
			System.out.print("key = " + key + ", value = ");
			DiscountPolicy discountPolicy = discountPolicyMap.get(key);
			System.out.println(discountPolicy.getClass());
		}
	}

}
