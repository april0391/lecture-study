package hello.advanced.aop.v1_basicTrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {

        TraceStatus status = trace.begin("OrderRepository.save()");
        try {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            getSleep(1000);
            trace.end(status);

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void getSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
