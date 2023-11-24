package qa.guru.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static final SelenideElement inputUsername = $("#userName"),
            inputPassword = $("#password"),
            buttonLogin = $("#login"),
            alertInfo = $("#name");

    public LoginPage fillFormLogin(String login, String password) {
        inputUsername.setValue(login);
        inputPassword.setValue(password);
        buttonLogin.click();
        return this;
    }

    public LoginPage checkAlertInfo(String info) {
        alertInfo.shouldBe(visible).shouldHave(text(info));
        return this;
    }


}
