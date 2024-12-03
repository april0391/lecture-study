package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callA();
        log.info("result={}", result1);
        // 공통 로직2 종료
    }

    @Test
    void lambda() {
        Common common = new Common();
        Hello hello = new Hello();
        common.call(() -> hello.callA());
        common.call(hello::callB);
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        // callA 메서드 정보
        Method callA = classHello.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("result1={}", result1);

        // callB 메서드 정보
        Method callB = classHello.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method callA = classHello.getMethod("callA");
        dynamicCall(callA, target);

        Method callB = classHello.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    static class Common {
        public void call(Supplier<String> supplier) {
            // 공통 로직1 시작
            log.info("start");
            String result = supplier.get();
            log.info("result={}", result);
            // 공통 로직1 종료
        }
    }
}
