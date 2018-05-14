package com.wtu.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

        String jdbcUrl = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8";
        Connection conn = null;

        try {
             conn= DriverManager.getConnection(jdbcUrl, "root", "abc123_");
        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void setAutoCommit(Connection conn, boolean isAutoCommit) {
        try {
            conn.setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void commit(Connection conn) {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
