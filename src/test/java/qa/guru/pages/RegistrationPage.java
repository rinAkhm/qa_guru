package qa.guru.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import qa.guru.pages.components.CalendarComponent;
import qa.guru.pages.components.SubmittedFormComponent;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static qa.guru.tests.TableResultData.TITLE;

public class RegistrationPage {

    private final static SelenideElement inputFirstname = $("#firstName"),
            inputLastname = $("#lastName"),
            inputEmail = $("#userEmail"),
            inputPhone = $("#userNumber"),
            buttonImage = $("#uploadPicture"),
            textAddress = $("#currentAddress"),
            fieldState = $("#state"),
            fieldCity = $("#city"),
            selectState = $("#react-select-3-input"),
            selectCity = $("#react-select-4-input"),
            btnSubmit = $("#submit"),
            inputBirthdate = $("#dateOfBirthInput"),
            inputSubject = $("#subjectsInput"),

            titleFormResult = $(".modal-title");
    private final static ElementsCollection titleFormResultElements = $$(".modal-title");
    private final static ElementsCollection multiBoxHobbies = $$("label[for*=hobbies-checkbox]");
    private final static ElementsCollection boxGender = $$("label[for*=gender-radio]");

    @Step("Open page by address {path}")
    public RegistrationPage openRegistrationPage(String path){
        open(path);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Fill firstname '{firstName}'")
    public RegistrationPage setFirstName(String firstName) {
        inputFirstname.sendKeys(firstName);
        return this;
    }

    @Step("Fill lastname'{lastName}'")
    public RegistrationPage setLastName(String lastName) {
        inputLastname.sendKeys(lastName);
        return this;
    }

    @Step("Fill email ='{email}'")
    public RegistrationPage setEmail(String email) {
        inputEmail.sendKeys(email);
        return this;
    }

    @Step("Choice gender type '{gender}'")
    public RegistrationPage choiceGender(String gender) {
        boxGender.findBy(text(gender)).click();
        return this;
    }

    @Step("Fill mobile number '{mobile}'")
    public RegistrationPage setMobile(String mobile) {
        inputPhone.setValue(mobile);
        return this;
    }

    @Step("Fill birthdate '{birthdate}'")
    public RegistrationPage setDateOfBirth(String birthdate) {
        CalendarComponent calendar = new CalendarComponent();
        inputBirthdate.click();
        calendar.setCalendar(birthdate);
        return this;
    }

    @Step("Fill subjects '{subjects}'")
    public RegistrationPage setSubjects(String[] subjects) {
        Arrays.stream(subjects).forEach(e -> inputSubject.val(e).pressEnter());
        return this;
    }

    @Step("Fill hobbies '{hobbies}'")
    public RegistrationPage setMultiBoxHobbies(String hobbies) {
        multiBoxHobbies.findBy(Condition.text(hobbies)).click();
        return this;
    }

    @Step("Upload photo")
    public RegistrationPage uploadPicture(File file){
        buttonImage.uploadFile(file);
        return this;
    }

    @Step("Fill address registration '{address}'")
    public RegistrationPage setAddress(String address) {
        textAddress.setValue(address);
        return this;
    }

    @Step("Fill state '{state}'")
    public RegistrationPage choiceState(String state) {
        fieldState.click();
        selectState.val(state).pressEnter();
        return this;
    }

    @Step("Fill city '{city}'")
    public RegistrationPage choiceCity(String city) {
        fieldCity.click();
        selectCity.val(city).pressEnter();
        return this;
    }

    @Step("Click  button send form")
    public RegistrationPage submitForm() {
        btnSubmit.hover().click();
        return this;
    }

    @Step("Expected field {label} with value '{value}'")
    public RegistrationPage checkFormResult(String label, String value) {
        SubmittedFormComponent submittedFormComponent = new SubmittedFormComponent();
        titleFormResult.should(visible, text(TITLE));
        submittedFormComponent.checkSubmittedForm(label, value);
        return this;
    }

    @Step("Expected when fill required field should be open table result")
    public RegistrationPage checkNotOpenFormResult() {
        boolean isTableResult = titleFormResultElements.size() == 0;
        Assertions.assertTrue(isTableResult, "Open table result without input data");
        return this;
    }
}
