package hello.advanced.trace.threadlocal;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    void threadLocal() throws InterruptedException {
        // 스레드 풀 생성
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 첫 번째 작업: ThreadLocal 값 설정
        pool.submit(() -> {
            threadLocal.set("Task1");
            System.out.println(Thread.currentThread().getName() + " set value: " + threadLocal.get());
        });

        // 두 번째 작업: ThreadLocal 값 확인
        pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " retrieved value: " + threadLocal.get());
//            threadLocal.remove();
        });

        // 스레드 풀 종료
        pool.shutdown();
    }
}
