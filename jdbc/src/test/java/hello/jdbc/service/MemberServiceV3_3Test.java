package hello.jdbc.service;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class MemberServiceV3_3Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "memberEx";

    @Autowired
    MemberRepositoryV3 memberRepository;
    @Autowired
    MemberServiceV3_3 memberService;
    @Autowired
    ApplicationContext ac;

    @AfterEach
    void afterEach() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상 이체")
    void transfer() throws SQLException {
        save();

        memberService.accountTransfer(MEMBER_A, MEMBER_B, 5000);

        Member findMemberA = memberRepository.findById(MEMBER_A);
        Member findMemberB = memberRepository.findById(MEMBER_B);
        assertThat(findMemberA.getMoney()).isEqualTo(5000);
        assertThat(findMemberB.getMoney()).isEqualTo(15000);
    }

    @Test
    void aopCheck() {
        log.info("memberRepository.getClass={}", memberRepository.getClass());
        log.info("memberService.getClass={}", memberService.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
        Assertions.assertThat(AopUtils.isAopProxy(memberService)).isTrue();
    }

    @Test
    void beanCheck() {
        HikariDataSource bean = (HikariDataSource) ac.getBean(DataSource.class);
        System.out.println("bean = " + bean);
        String jdbcUrl = bean.getJdbcUrl();
        System.out.println("jdbcUrl = " + jdbcUrl);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void transferEx() throws SQLException {
        save();
        assertThatThrownBy(() -> memberService.accountTransfer(MEMBER_A, MEMBER_EX, 5000))
                .isInstanceOf(IllegalStateException.class);

        Member findMemberA = memberRepository.findById(MEMBER_A);
        Member findMemberEx = memberRepository.findById(MEMBER_EX);

        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberEx.getMoney()).isEqualTo(10000);
    }

    private void save() throws SQLException {
        memberRepository.save(new Member(MEMBER_A, 10000));
        memberRepository.save(new Member(MEMBER_B, 10000));
        memberRepository.save(new Member(MEMBER_EX, 10000));
    }

    @TestConfiguration
    static class TestConfig {

        @Autowired
        DataSource dataSource;

//        @Bean
        public DataSource dataSource() {
            return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        }

//        @Bean
        PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }

        @Bean
        MemberRepositoryV3 memberRepositoryV3() {
            return new MemberRepositoryV3(dataSource);
        }

        @Bean
        MemberServiceV3_3 memberServiceV3_3() {
            return new MemberServiceV3_3(memberRepositoryV3());
        }

    }

}