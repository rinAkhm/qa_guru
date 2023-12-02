package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import qa.guru.steps.GitHubStep;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AllureTest extends BaseTest{

    @Test
    void listenerSearchIssueByNameTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("/");
        $("[placeholder*='Search or jump to']").click();
        $("#query-builder-test").sendKeys("qa-guru/qa_guru_14_10");
        $("#query-builder-test").submit();
        $(By.linkText("qa-guru/qa_guru_14_10")).click();
        $("#issues-tab").click();
        $(withText("Issue for Autotest")).shouldBe(Condition.visible);
    }

    @Test
    void lambdaSearchIssueByNameTest() {
        Allure.step("Открываем https://github.com", () -> open("/"));
        Allure.step("Вводим в строку поиска qa-guru/qa_guru_14_10", () -> {
            $("[placeholder*='Search or jump to']").click();
            $("#query-builder-test").sendKeys("qa-guru/qa_guru_14_10");
            $("#query-builder-test").submit();
        });
        Allure.step("Переходим по ссылке", () -> $(By.linkText("qa-guru/qa_guru_14_10")).click());
        Allure.step("Нажимаем на вкладку 'Issues'", () -> $("#issues-tab").click());
        Allure.step("Ищем issue с названием 'Issue for Autotest'", ()-> $(withText("Issue for Autotest")).shouldBe(Condition.visible));
    }

    @Test
    void stepSearchIssueByNameTest(){
        GitHubStep step = new GitHubStep();
        step.openGitHub("/");
        step.fillSearchInput("qa-guru/qa_guru_14_10");
        step.goToRepository("qa-guru/qa_guru_14_10");
        step.openSectionIssues();
        step.shouldVisibleIssue("Issue for Autotest");
    }
}
