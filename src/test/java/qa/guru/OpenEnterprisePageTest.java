package qa.guru;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class OpenEnterprisePageTest extends BaseTest {
    @Test
    void choiceSolutionsEnterpriseWithHover() {
        open("");
        var e = Condition.textCaseSensitive("solutions");
        $(".header-menu-wrapper").$$(".HeaderMenu-item")
                .findBy(Condition.text("Solutions")).shouldBe(visible).hover();
        $$(".HeaderMenu-dropdown ul li a")
                .findBy(text("Enterprise")).should(visible).hover().click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\n" +
                "developer platform."));
    }
}
