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

    private static final String mainUrl = "https://github.com/";
    private static final String searchClass = ".header-search-input";
    private static final String repo = "qa-guru/qa_guru_14_10";
    private static final String issue = "Issue for Autotest";
    private static final String tabIssueId = "#issues-tab";

    @Test
    public void issuesSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(mainUrl);
        $(searchClass).click();
        $(searchClass).sendKeys(repo);
        $(searchClass).submit();
        $(linkText(repo)).click();
        $(tabIssueId).click();
        $(withText(issue)).should(Condition.exist);
    }
}
