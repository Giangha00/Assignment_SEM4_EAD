package org.example.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static String url = "jdbc:mysql://localhost:3306/sis?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
    private static String username = "root";
    private static String password = "";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    static {
        try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is != null) {
                Properties props = new Properties();
                props.load(is);
                url = props.getProperty("db.url", url);
                username = props.getProperty("db.username", username);
                password = props.getProperty("db.password", password);
                driver = props.getProperty("db.driver", driver);
            }
            Class.forName(driver);
        } catch (Exception e) {
            System.err.println("Error initializing DBUtil: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Thu ket noi voi thong tin tu db.properties
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // Neu loi xac thuc (thuong gap khi chuyen giua XAMPP password='' va MySQL local password='root')
            if (e.getErrorCode() == 1045) {
                try {
                    // Thu fallback sang password rong (chuan XAMPP mac dinh)
                    return DriverManager.getConnection(url, username, "");
                } catch (SQLException e2) {
                    try {
                        // Hoac thu fallback sang password 'root'
                        return DriverManager.getConnection(url, username, "root");
                    } catch (SQLException ignored) {
                    }
                }
            }
            throw e;
        }
    }

    public static void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
}

