package hello.jdbc.exception.translator;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class SpringExceptionTranslatorTest {

    HikariDataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:db");
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS member (" +
                             "member_id VARCHAR(255) PRIMARY KEY, " +
                             "money INT)")) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }

    @Test
    void sqlExceptionErrorCode() {
        String sql = "SELECT FRM member";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeQuery();
        } catch (SQLException e) {
            assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode = e.getErrorCode();
            log.info("errorCode={}", errorCode);
            log.info("error", e);
        }
    }

    @Test
    void exceptionTranslator() {
        String sql = "select bad grammar";

        try {
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeQuery();
        } catch (SQLException e) {
            assertThat(e.getErrorCode()).isEqualTo(42122);
            SQLErrorCodeSQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator();
            DataAccessException resultEx = translator.translate("select", sql, e);
            log.info("resultEx", resultEx);
            assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
        }

    }
}
