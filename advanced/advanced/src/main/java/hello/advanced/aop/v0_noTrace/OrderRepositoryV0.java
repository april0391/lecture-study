package hello.advanced.aop.v0_noTrace;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV0 {

    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생");
        }
        getSleep(1000);
    }

    private void getSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
