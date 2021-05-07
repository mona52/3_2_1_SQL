package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;


public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement submitButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(String code) {
        codeField.sendKeys(SHIFT, ARROW_UP, DELETE);
        codeField.setValue(code);
        submitButton.click();
        return new DashboardPage();
    }
}

