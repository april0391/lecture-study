package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @BeforeEach
    void delete() throws SQLException {
        String sql = "DELETE FROM member";
        Connection con = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.executeUpdate();
        repository.close(con, pstmt, null);
    }

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