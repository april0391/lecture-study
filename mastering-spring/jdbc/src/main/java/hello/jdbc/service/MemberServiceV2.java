package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.lambda.ConBizLogic;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void transactionProxy(ConBizLogic conBizLogic) throws SQLException {
        log.info("START TX");
        Connection con = dataSource.getConnection();
        try {
            // 트랜잭션 시작
            con.setAutoCommit(false);
            // 비즈니스 로직
            conBizLogic.execute(con);
            // 성공 시 커밋
            con.commit();
        } catch (Exception e) {
            log.info("문제 발생!! 롤백합니다.");
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            release(con);
            log.info("END TX");
        }
    }

    public void transferLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);
        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private void release(Connection con) {
        if (con != null) {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e) {
                log.info("error", e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("memberEx")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
