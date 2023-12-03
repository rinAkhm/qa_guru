package qa.guru.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SubmittedFormComponent {
    public void checkSubmittedForm(String label, String value) {
        $(".table-responsive").find(byText(label)).parent().shouldHave(text(value));
    }
 }
