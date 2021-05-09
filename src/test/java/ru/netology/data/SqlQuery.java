package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class SqlQuery {

    private final static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    public static String getVerificationCodeFor(String user) {
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );
        var idSql = "SELECT id FROM users WHERE login = '" + user + "'";
        String id = runner.query(conn, idSql, new ScalarHandler<>());
        var code = "SELECT code FROM auth_codes WHERE user_id = '" + id + "' ORDER BY created DESC";
        return runner.query(conn, code, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void addUser(String id, String user, String password) {
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );

        var addSql = "INSERT INTO users(id, login, password) VALUES (?, ?, ?);";
        runner.update(conn, addSql, id, user, password);

    }

    @SneakyThrows
    public static String getStatusFor(String user) {
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );

        var statusSql = "SELECT status FROM users WHERE login = '" + user + "'";
        return runner.query(conn, statusSql, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void clearAllTables(){
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/appdb", "user", "pass"
        );
        runner.execute(conn, "DELETE from appdb.card_transactions");
        runner.execute(conn, "DELETE from appdb.auth_codes");
        runner.execute(conn, "DELETE from appdb.cards");
        runner.execute(conn, "DELETE from appdb.users");

    }

}


