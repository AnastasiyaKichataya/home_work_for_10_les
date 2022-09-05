package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebStepsTest {

    private static final String mainUrl = "https://github.com/";
    private static final String searchClass = ".header-search-input";
    private static final String repo = "qa-guru/qa_guru_14_10";
    private static final String issue = "Issue for Autotest";
    private static final String tabIssueId = "#issues-tab";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(mainUrl);
    }


    @Step("Ищем репозиторий {repository}")
    public void searchForRepository(String repository) {
        $(searchClass).click();
        $(searchClass).sendKeys(repo);
        $(searchClass).submit();
    }

    @Step("Кликаем по ссылке репозитория {repository}")
    public void clickOnRepositoryLink(String repository) {
        $(linkText(repo)).click();
    }

    @Step("Открываем вкладку Issues")
    public void openIssuesTab() {
        $(tabIssueId).click();
    }

    @Step("Проверяем наличие Issue с названием {autotestIssue}")
    public void shouldSeeIssueWithName(String autotestIssue) {
        $(withText(issue)).should(Condition.exist);
    }

    @Test
    public void annotatedStepsTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebStepsTest steps = new WebStepsTest();

        steps.openMainPage();
        steps.searchForRepository(repo);
        steps.clickOnRepositoryLink(repo);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithName(issue);
    }


}
