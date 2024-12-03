package hello.aop.exam;

import hello.aop.exam.aop.RetryAspect;
import hello.aop.exam.aop.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({TraceAspect.class, RetryAspect.class})
@SpringBootTest
public class ExamTest {

    @Autowired
    ApplicationContext ac;
    @Autowired
    ExamService examService;

    @BeforeEach
    void before() {
        ExamRepository examRepository = ac.getBean(ExamRepository.class);
        log.info("examRepository class={}", examRepository.getClass());
        log.info("examService class={}", examService.getClass());
//        ExamRepository getExamRepository = examService.getExamRepository();
//        log.info("getExamRepository class={}", getExamRepository.getClass());
    }

    @Test
    void test() {
        for (int i = 0; i < 5; i++) {
            log.info("client request {}", i);
            examService.request("data" + i);
        }
    }
}
