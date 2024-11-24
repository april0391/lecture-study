package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void strategyV1() {
        ContextV2 context1 = new ContextV2();
        context1.execute(new StrategyLogic1());

        ContextV2 context2 = new ContextV2();
        context2.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context1 = new ContextV2();
        context1.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        ContextV2 context2 = new ContextV2();
        context2.execute(() -> log.info("비즈니스 로직2 실행"));
    }


    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
