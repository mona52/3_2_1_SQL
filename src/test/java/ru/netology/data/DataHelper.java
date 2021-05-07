package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

@Data
public class DataHelper {

    @Value
    public static class AuthInfo {
        private String id;
        private String login;
        private String password;

    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("58f830f0-51ff-4121-8b5b-3f2a76c281f7", "vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("d7c962b2-1141-41b2-b5e7-caf5ec1d4af4", "petya", "123qwerty");
    }
    public static AuthInfo getWrongAuthInfo() {
        return new AuthInfo("3", "ivan", "123qwerty");
    }

    public static AuthInfo getRandomAuthInfo() {
        var faker = new Faker();
        var id = faker.idNumber().valid();
        var name = faker.name().username();
        var password = "$2a$10$UvF1b6JIdHKpe0Wk8nILz.S4iTbBDSXrfDThlY2PKw5tUHGAHwo62"; //== "123qwerty"
        SqlQuery.addUser(id, name, password);
        return new AuthInfo(id, name, "123qwerty");
    }
}
