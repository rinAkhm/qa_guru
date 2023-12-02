package qa.guru.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GitHubStep {

    @Step("Открываем '{url}'")
    public void openGitHub(String url) {
        open(url);
    }

    @Step("Вводим в строку поиска '{link}'")
    public void fillSearchInput(String link){
        $("[placeholder*='Search or jump to']").click();
        $("#query-builder-test").sendKeys(link);
        $("#query-builder-test").submit();
    }

    @Step("Переход по ссылке '{link}'")
    public void goToRepository(String link){
        $(By.linkText("qa-guru/qa_guru_14_10")).click();
    }

    @Step("Открываем вкладку 'Issues'")
    public void openSectionIssues(){
        $("#issues-tab").click();
    }

    @Step("Открываем вкладку '{issue}'")
    public void shouldVisibleIssue(String issue){
        $(withText(issue)).shouldBe(Condition.visible);
    }

}
