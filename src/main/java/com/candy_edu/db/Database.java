package com.candy_edu.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class Database {
    private static HikariDataSource ds;

    public static void init() {
        HikariConfig c = new HikariConfig();
        c.setJdbcUrl("jdbc:postgresql://localhost:5432/lab1demo");
        c.setUsername("myuser");
        c.setPassword("<your_password>");
        c.setDriverClassName("org.postgresql.Driver");
        c.setMaximumPoolSize(5);
        ds = new HikariDataSource(c);
    }

    public static void init(String url, String user, String pass) {
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(url);
        cfg.setUsername(user);
        cfg.setPassword(pass);
        cfg.setDriverClassName("org.postgresql.Driver");
        cfg.setMaximumPoolSize(5);
        ds = new HikariDataSource(cfg);
    }

    public static DataSource get() {
        if (ds == null) init();
        return ds;
    }

    public static void close() {
        if (ds != null) ds.close();
    }

    public static Connection getConnection() throws SQLException {
        return get().getConnection();
    }

}
