package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ActiveProfiles("test")
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        // 기본 DriverManager 사용 (항상 새로운 커넥션 생성 및 획득)
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
//        repository = new MemberRepositoryV1(dataSource);

        // 커넥션풀 사용
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        repository = new MemberRepositoryV1(dataSource);
    }

    /*@BeforeEach
    void delete() throws SQLException {
        String sql = "DELETE FROM member";
        Connection con = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        repository.close(con, pstmt, null);
    }*/

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV0", 10000);
        repository.save(member);

        // findById
        Member findMember = repository.findById("memberV0");
        log.info("findMember={}", findMember);

        assertThat(member).isEqualTo(findMember);

        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById("memberV0");
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(updatedMember.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}