package qa.guru;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import qa.guru.data.Widget;
import qa.guru.pages.StoreBookPage;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StoreBookTest extends BaseTest {
    private static final String INVALID_CREDENTIALS = "Invalid username or password!";
    private final StoreBookPage storeBookPage = new StoreBookPage();

    @ValueSource(strings = {
            "Git Pocket Guide",
            "Speaking JavaScript"
    })
    @ParameterizedTest
    void shouldSearchBookByTitleTest(String title) {
        open("/books");
        storeBookPage.fillInputSearch(title).getFirstSearchResult(title);
    }


    static Stream<Arguments> methodSourceWithCredentials() {
        return Stream.of(
                Arguments.of("InvalidLogin1", "InvalidPassword1"),
                Arguments.of("InvalidLogin2", "InvalidPassword2")
        );
    }

    @MethodSource("methodSourceWithCredentials")
    @ParameterizedTest
    void theLoginPageShouldReturnAlertWithInvalidCredentialsTest(String login, String password) {
        open("/books");
        storeBookPage.clickButtonLogin().fillFormLogin(login, password).checkAlertInfo(INVALID_CREDENTIALS);

    }

    @CsvFileSource(resources = "/elementsPage.csv")
    @ParameterizedTest()
    void thePagesShouldHaveMainTitleTest(String page, String title) {
        open(page);
        $(".main-header").shouldHave(text(title));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Elements",
            "Forms",
            "Alerts, Frame & Windows"
    })
    void theMainPageShouldHasAccessibleWidgetsTest(String widgetName) {
        open("/");
        $$(".card-body")
                .find(text(widgetName))
                .hover().shouldBe(enabled);
    }

    @EnumSource(Widget.class)
    @ParameterizedTest
    void theMainPageShouldHasAccessibleWidgetsFromEnumTest(Widget widgetName) {
        open("/");
        $$(".card-body")
                .find(text(widgetName.getWidget()))
                .hover().shouldBe(enabled);
    }

}
