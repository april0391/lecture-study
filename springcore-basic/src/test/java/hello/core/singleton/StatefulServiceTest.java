package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                statefulService1.order("userA", 10000);
            }
        };
        System.out.println("thread1.getClass() = " + thread1.getClass());

        Thread thread2 = new Thread(() -> statefulService2.order("userB", 20000));
        System.out.println("thread2.getClass() = " + thread2.getClass());

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        // 사용자 A의 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // 기대: 사용자 A의 주문 금액이 10000원이지만, 상태를 공유하는 경우 잘못된 값이 나올 수 있음
        Assertions.assertThat(price).isNotEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}