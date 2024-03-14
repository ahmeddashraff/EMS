package Views;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleDriver;

public interface Connections {
    public Connection getConnection() throws SQLException;
}