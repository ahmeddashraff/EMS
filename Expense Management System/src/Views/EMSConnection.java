package Views;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleDriver;

public class EMSConnection implements Connections{
    
    public Connection getConnection() throws SQLException {
        String username = "ahmed";
        String password = "ahmed";
        String thinConn = "jdbc:oracle:thin:@localhost:1521:XE";
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(thinConn, username, password);
        conn.setAutoCommit(true);
        return conn;
    }
}
