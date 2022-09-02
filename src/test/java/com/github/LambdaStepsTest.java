package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaStepsTest {

    private static final String mainUrl = "https://github.com/";
    private static final String searchClass = ".header-search-input";
    private static final String repo = "qa-guru/qa_guru_14_10";
    private static final String issue = "Issue for Autotest";
    private static final String tabIssueId = "#issues-tab";

    @Test
    public void issuesSearchWithLambdaStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open(mainUrl);
        });
        step("Ищем репозиторий " + repo, () -> {
            $(searchClass).click();
            $(searchClass).sendKeys(repo);
            $(searchClass).submit();
        } );
        step("Кликаем по ссылке репозитория " + repo, () -> {
            $(linkText(repo)).click();
        });
        step("Открываем вкладку Issues", () -> {
            $(tabIssueId).click();
        });
        step("Проверяем наличие Issue с названием " + issue, () ->{
            $(withText(issue)).should(Condition.exist);
        });
    }
}
