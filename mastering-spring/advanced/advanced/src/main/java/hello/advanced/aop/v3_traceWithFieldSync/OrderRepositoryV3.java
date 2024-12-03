package hello.advanced.aop.v3_traceWithFieldSync;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {
        TraceStatus status = trace.begin("OrderRepository.save()");
        try {
            if ("ex".equals(itemId)) {
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
