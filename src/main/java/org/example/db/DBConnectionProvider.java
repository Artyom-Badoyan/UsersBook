package org.example.db;

import com.mysql.cj.jdbc.Driver;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionProvider { // Կապի մատակարար

    private static final DBConnectionProvider instance = new DBConnectionProvider();  // instance օրինակ

    private String driverName;
    private String dbUrl;
    private String username;
    private String password;

    private Connection connection; // Միացում sviaz otkrivaet podkliuchenie k baza dannix.


    @SneakyThrows
    private DBConnectionProvider() { // Կապի մատակարար
        loadProperties();
//        new Driver();
        Class.forName(driverName);   //classi anuny talov sarqum enq ayd classic object vor driverManagery manage ani
        // etot straka podkliuchet draiver mysql k nashmu java prilajeniu
    }

    @SneakyThrows
    private void loadProperties() { // բեռնման հատկություններ
        Properties properties = new Properties(); // Հատկություններ
        properties.load(new FileInputStream(
                "C:\\Users\\User\\IdeaProjects\\UsersBook\\src\\main\\resources\\db.properties"));
        driverName = properties.getProperty("db.driver.name");
        dbUrl = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
    }

    public static DBConnectionProvider getInstance() { // ստանալ օրինակ
        return instance;
    }

    @SneakyThrows
    public Connection getConnection() { // Միացում ստանալ օրինակ
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } //otkrivaet podkliuchenie k baza dannix.
        return connection;
    }


    @SneakyThrows
    public void closeConnection() { // փակել Կապը
        if (!connection.isClosed()) {
            connection.close();
        }
    }

}
