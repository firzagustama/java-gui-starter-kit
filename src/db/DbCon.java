/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All right reserved.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB Connection
 * @author Muhammad Firza Gustama (muhammad.gustama@dana.id)
 * @version $Id: DbCon.java, v 0.1 2021-12-19 17.18 Muhammad Firza Gustama Exp $$
 */
public class DbCon {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/DATABSE_NAME?serverTimezone=Asia/Jakarta", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get connection");
        }
    }
}
