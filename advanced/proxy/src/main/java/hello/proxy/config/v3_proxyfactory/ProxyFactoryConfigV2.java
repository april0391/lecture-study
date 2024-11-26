package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ProxyFactoryConfigV2 {

    private final LogTrace logTrace;

    @Bean
    public OrderControllerV2 orderControllerV2() {
        OrderControllerV2 target = new OrderControllerV2(orderServiceV2());
        OrderControllerV2 proxy = (OrderControllerV2) createProxy(target);
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        OrderServiceV2 target = new OrderServiceV2(orderRepositoryV2());
        OrderServiceV2 proxy = (OrderServiceV2) createProxy(target);
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {
        OrderRepositoryV2 target = new OrderRepositoryV2();
        OrderRepositoryV2 proxy = (OrderRepositoryV2) createProxy(target);
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    private Object createProxy(Object target) {
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor());
        return factory.getProxy();
    }

    private Advisor getAdvisor() {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");
        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
