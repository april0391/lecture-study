package hello.advanced.trace.threadlocal.code;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    @Getter
    private final ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("{} 저장 -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        String result = nameStore.get();
        log.info("{} 조회 nameStore={}", name, result);
//        nameStore.remove();
        return result;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
