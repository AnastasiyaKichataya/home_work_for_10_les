package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    public void issuesSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("qa-guru/qa_guru_14_10");
        $(".header-search-input").submit();
        $(linkText("qa-guru/qa_guru_14_10")).click();
        $("#issues-tab").click();
        $(withText("Issue for Autotest")).should(Condition.exist);
    }
}
