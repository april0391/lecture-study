package hello.jdbc.lambda;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface ConBizLogic {
    void execute(Connection con) throws SQLException;
}
