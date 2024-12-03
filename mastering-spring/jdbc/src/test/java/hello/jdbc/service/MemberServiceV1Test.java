package hello.jdbc.service;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceV1Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "memberEx";

    MemberRepositoryV1 memberRepository;
    MemberServiceV1 memberService;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        memberRepository = new MemberRepositoryV1(dataSource);
        memberService = new MemberServiceV1(memberRepository);
    }

    @AfterEach
    void afterEach() throws SQLException {
        memberRepository.recovery();
    }

    @Test
    @DisplayName("정상 이체")
    void transfer() throws SQLException {
        memberService.accountTransfer(MEMBER_A, MEMBER_B, 5000);
        Member findMemberA = memberRepository.findById(MEMBER_A);
        Member findMemberB = memberRepository.findById(MEMBER_B);
        assertThat(findMemberA.getMoney()).isEqualTo(5000);
        assertThat(findMemberB.getMoney()).isEqualTo(15000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void transferEx() throws SQLException {
        assertThatThrownBy(() -> memberService.accountTransfer(MEMBER_A, MEMBER_EX, 5000))
                .isInstanceOf(IllegalStateException.class);
        Member findMemberA = memberRepository.findById(MEMBER_A);
        Member findMemberEx = memberRepository.findById(MEMBER_EX);

        assertThat(findMemberA.getMoney()).isEqualTo(5000);
        assertThat(findMemberEx.getMoney()).isEqualTo(10000);
    }
}