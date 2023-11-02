package qa.guru;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class SearchSoftAssertionsTest extends BaseTest {

    private final static String expected = String.join("@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "  @Test\n" +
            "  void test() {\n" +
            "    Configuration.assertionMode = SOFT;\n" +
            "    open(\"page.html\");\n" +
            "\n" +
            "    $(\"#first\").should(visible).click();\n" +
            "    $(\"#second\").should(visible).click();\n" +
            "  }\n" +
            "}");

    @Test
    void findExampleSoftAssertionsWithJunit5Test() {
        open("");
        $("[placeholder='Search or jump to...']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $("[data-testid='results-list']").find("a").click();
        $("#repository-container-header").find("#wiki-tab").click();
        var pages = $$(".markdown-body li a").find(Condition.href("SoftAssertions"));
        pages.should(exist).click();
        $$("[id*=junit5]").shouldHave(texts(expected));
    }
}
