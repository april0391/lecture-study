package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.*;

public class BasicTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);
        A a = ac.getBean(A.class);
        a.helloA();
        assertThatThrownBy(() -> ac.getBean(B.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    static class A {
        @PostConstruct
        public void test() {
            System.out.println("A.test");
        }

        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }

    @Configuration
    static class BasicConfig {
        @Bean
        public A a() {
            return new A();
        }
    }
}
