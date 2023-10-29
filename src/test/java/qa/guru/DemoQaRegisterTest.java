package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaRegisterTest extends BaseTest {

    private final static SelenideElement inputFirstname = $x("//input[@placeholder='First Name']");
    private final static SelenideElement inputLastname = $x("//input[@placeholder='Last Name']");
    private final static SelenideElement inputEmail = $x("//input[@id='userEmail']");
    private final static ElementsCollection boxGender = $$("label[for*=gender-radio]");
    private final static SelenideElement inputPhone = $x("//input[@placeholder='Mobile Number']");
    private final static SelenideElement inputBirthdate = $("#dateOfBirthInput");
    private final static SelenideElement fieldMonth = $(".react-datepicker__month-select");
    private final static SelenideElement fieldYear = $(".react-datepicker__year-select");
    private final static ElementsCollection fieldDay = $$(".react-datepicker__day");
    private final static SelenideElement inputSubject = $("#subjectsInput");
    private final static ElementsCollection multiBoxHobbies = $$("label[for*=hobbies-checkbox]");
    private final static SelenideElement buttonImage = $("#uploadPicture");
    private final static SelenideElement textAddress = $("#currentAddress");

    private final static SelenideElement state = $("#state");
    private final static SelenideElement city = $("#city");
    private final static SelenideElement selectState = $("#react-select-3-input");
    private final static SelenideElement selectCity = $("#react-select-4-input");
    private final static SelenideElement btnSubmit = $("#submit");
    private final static SelenideElement tableResultForm = $(".table-responsive");


    @BeforeEach
    void setUp() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Test
    void registerStudentTest() {
        Register data = new Register("", "");
        inputFirstname.sendKeys(data.getFirstname());
        inputLastname.sendKeys(data.getLastname());
        inputEmail.sendKeys(data.getEmail());
        boxGender.findBy(Condition.text(data.getGender())).click();
        inputPhone.setValue(data.getMobile());
        setFieldDate(data.getBirthdate());
        setSubjects(data.getSubject());
        multiBoxHobbies.findBy(Condition.text(data.getHobbies())).click();
        buttonImage.uploadFile(data.getImage());
        textAddress.setValue(data.getAddress());
        state.scrollTo().click();
        selectState.val(data.getState()).pressEnter();
        city.click();
        selectCity.val(data.getCity()).pressEnter();
        btnSubmit.click();
        tableResultForm.shouldHave(text(String.join(" ", data.getFirstname(), data.getLastname())));
        tableResultForm.shouldHave(text(data.getEmail()));
        tableResultForm.shouldHave(Condition.matchText("[0-9]{2}.\\w*,[0-9]{4}"));
        for (String subject:data.getSubject()){
            tableResultForm.shouldHave(text(subject));
        }
        tableResultForm.shouldHave(text(data.getImage().getName()));
        tableResultForm.shouldHave(text(data.getAddress()));
        tableResultForm.shouldHave(text(String.join(" ", data.getState(), data.getCity())));
    }

    private void setFieldDate(String date) {
        String[] splitDate = date.split(" ");
        inputBirthdate.click();
        fieldMonth.selectOption(splitDate[1]);
        fieldYear.selectOption(splitDate[2]);
        fieldDay.findBy(Condition.anyOf(
                Condition.attribute("label", splitDate[1]),
                Condition.text(splitDate[0])))
                .click();
    }

    private void setSubjects(String[] subject){
        Arrays.stream(subject).forEach(e -> inputSubject.val(e).pressEnter());
    }

}
