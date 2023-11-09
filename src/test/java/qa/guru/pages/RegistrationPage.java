package qa.guru.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import qa.guru.pages.components.CalendarComponent;
import qa.guru.pages.components.SubmittedFormComponent;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static qa.guru.TableResultData.TITLE;

public class RegistrationPage {

    private final static SelenideElement inputFirstname = $x("//input[@placeholder='First Name']"),
            inputLastname = $x("//input[@placeholder='Last Name']"),
            inputEmail = $x("//input[@id='userEmail']"),
            inputPhone = $x("//input[@placeholder='Mobile Number']"),
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

    public RegistrationPage openRegistrationPage(String path){
        open(path);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }
    public RegistrationPage setFirstName(String firstName) {
        inputFirstname.sendKeys(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        inputLastname.sendKeys(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        inputEmail.sendKeys(email);
        return this;
    }

    public RegistrationPage choiceGender(String gender) {
        boxGender.findBy(text(gender)).click();
        return this;
    }

    public RegistrationPage setMobile(String mobile) {
        inputPhone.setValue(mobile);
        return this;
    }

    public RegistrationPage setDateOfBirth(String birthdate) {
        CalendarComponent calendar = new CalendarComponent();
        inputBirthdate.click();
        calendar.setCalendar(birthdate);
        return this;
    }

    public RegistrationPage setSubjects(String[] subjects) {
        Arrays.stream(subjects).forEach(e -> inputSubject.val(e).pressEnter());
        return this;
    }

    public RegistrationPage setMultiBoxHobbies(String hobbies) {
        multiBoxHobbies.findBy(Condition.text(hobbies)).click();
        return this;
    }

    public RegistrationPage uploadPicture(File file){
        buttonImage.uploadFile(file);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        textAddress.setValue(address);
        return this;
    }

    public RegistrationPage choiceState(String state) {
        fieldState.click();
        selectState.val(state).pressEnter();
        return this;
    }

    public RegistrationPage choiceCity(String city) {
        fieldCity.click();
        selectCity.val(city).pressEnter();
        return this;
    }

    public RegistrationPage submitForm() {
        btnSubmit.hover().click();
        return this;
    }

    public RegistrationPage checkFormResult(String label, String value) {
        SubmittedFormComponent submittedFormComponent = new SubmittedFormComponent();
        titleFormResult.should(visible, text(TITLE));
        submittedFormComponent.checkSubmittedForm(label, value);
        return this;
    }

    public RegistrationPage checkNotOpenFormResult() {
        boolean isTableResult = titleFormResultElements.size() == 0;
        Assertions.assertTrue(isTableResult, "Open table result without input data");
        return this;
    }
}
