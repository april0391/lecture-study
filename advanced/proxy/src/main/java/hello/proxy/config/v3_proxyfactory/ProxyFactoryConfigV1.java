package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.v1.*;
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
public class ProxyFactoryConfigV1 {

    private final LogTrace logTrace;

    @Bean
    public OrderControllerV1 orderControllerV1() {
        OrderControllerV1 target = new OrderControllerV1Impl(orderServiceV1());
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor());
        OrderControllerV1 proxy = (OrderControllerV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        OrderServiceV1 target = new OrderServiceV1Impl(orderRepositoryV1());
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor());
        OrderServiceV1 proxy = (OrderServiceV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        OrderRepositoryV1 target = new OrderRepositoryV1Impl();
        ProxyFactory factory = new ProxyFactory(target);
        factory.addAdvisor(getAdvisor());
        OrderRepositoryV1 proxy = (OrderRepositoryV1) factory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), target.getClass());
        return proxy;
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
