package qa.guru.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import qa.guru.pages.components.TableBookComponent;

import static com.codeborne.selenide.Selenide.$;

public class StoreBookPage {
    private static final SelenideElement inputSearch = $("#searchBox"),
            buttonLogin = $("#login");

    public LoginPage clickButtonLogin(){
        buttonLogin.click();
        return new LoginPage();
    }
    public StoreBookPage fillInputSearch(String title) {
        inputSearch.setValue(title).shouldHave(Condition.visible);
        return this;
    }

    public StoreBookPage getFirstSearchResult(String text) {
        new TableBookComponent().getRow(1).checkTitle(text);
        return this;
    }


}
