package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.app.LaunchApp;
import ru.netology.data.SqlQuery;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SqlQuery.getStatusFor;
import static ru.netology.data.SqlQuery.getVerificationCodeFor;


public class SqlTest {
    private DashboardPage dashboardPage;
    private LoginPage loginPage;

    @BeforeAll
    static void launchApp() {
        LaunchApp.launch();
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clear() {
        SqlQuery.clearAllTables();
    }


    @Test
    void shouldLogin() {
        var user = getAuthInfo();
        var login = new LoginPage().validLogin(user);
        dashboardPage = login.validVerify(getVerificationCodeFor(user.getLogin()));
    }

    @Test
    void shouldNotLogin() {
        var user = getWrongAuthInfo();
        loginPage = new LoginPage();
        loginPage.login(user);
        loginPage.error();
    }

    @Test
    void shouldAddUserAndLogin() {
        var newUser = getRandomAuthInfo();
        var login = new LoginPage().validLogin(newUser);
        dashboardPage = login.validVerify(getVerificationCodeFor(newUser.getLogin()));
    }

    @Test
    void shouldBlockIncorrectPassword() {
        var userPassword = new AuthInfo(getAuthInfo().getId(), getAuthInfo().getLogin(), "yuiop");
        loginPage = new LoginPage();
        loginPage.login(userPassword);
        loginPage.error();
        loginPage.login(userPassword);
        loginPage.error();
        loginPage.login(userPassword);
        loginPage.error();
        String actual = getStatusFor(getAuthInfo().getLogin());
        System.out.println(actual);
        assertEquals("blocked", actual);
    }
}


