package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@RequiredArgsConstructor
@Configuration
public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request*", "order*", "save*"};
    private final LogTrace logTrace;

    @Bean
    public OrderControllerV1 orderControllerV1() {
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderServiceV1());
        return (OrderControllerV1) Proxy.newProxyInstance(
                OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderController, logTrace, PATTERNS)
        );
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1());
        return (OrderServiceV1) Proxy.newProxyInstance(
                OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderService, logTrace, PATTERNS)
        );
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return (OrderRepositoryV1) Proxy.newProxyInstance(
                OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class},
                new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)
        );
    }
}
