package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/?useSSL=FALSE&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    private static final String DATABASE_NAME = "livraria";
    private static final EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

    private static EntityManagerFactory createEntityManagerFactory() {
        createDatabaseIfNotExists();
        return Persistence.createEntityManagerFactory("DesafioLivraria");
    }

    private static void createDatabaseIfNotExists() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating database", e);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
