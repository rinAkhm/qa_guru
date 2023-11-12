package qa.guru;

import org.junit.jupiter.api.Test;
import qa.guru.testData.Register;
import qa.guru.pages.RegistrationPage;

import java.util.Arrays;

import static qa.guru.TableResultData.*;


public class DemoQaRegisterTest extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();
    Register data = new Register();

    @Test
    void registerStudentWriteInFullFieldTest() {

        registrationPage
                .openRegistrationPage("/automation-practice-form")
                .setFirstName(data.getFirstname())
                .setLastName(data.getLastname())
                .setEmail(data.getEmail())
                .choiceGender(data.getGender())
                .setMobile(data.getMobile())
                .setDateOfBirth(data.getBirthdate())
                .setSubjects(data.getSubject())
                .setMultiBoxHobbies(data.getHobbies())
                .uploadPicture(data.getImage())
                .setAddress(data.getAddress())
                .choiceState(data.getState())
                .choiceCity(data.getCity())
                .submitForm();

        registrationPage
                .checkFormResult(STUDENT_NAME,
                        String.join(" ", data.getFirstname(), data.getLastname()))
                .checkFormResult(STUDENT_EMAIL, data.getEmail())
                .checkFormResult(GENDER, data.getGender())
                .checkFormResult(MOBILE, data.getMobile())
                .checkFormResult(DATE_OF_BIRTH, Arrays.stream(data.getBirthdate().split("^[0-9]{2}.\\w*"))
                        .reduce((s1, s2) -> String.join(",", s1.trim(), s2.trim())).get())
                .checkFormResult(SUBJECTS, Arrays.stream(data.getSubject())
                        .reduce((s1, s2) -> String.join(", ", s1, s2)).get())
                .checkFormResult(HOBBIES, data.getHobbies())
                .checkFormResult(PICTURE, data.getImage().getName())
                .checkFormResult(ADDRESS, data.getAddress())
                .checkFormResult(STATE_AND_CITY, String.join(" ", data.getState(), data.getCity()));
    }

    @Test
    void registerStudentWriteInRequiredFieldTest() {
        registrationPage
                .openRegistrationPage("/automation-practice-form")
                .setFirstName(data.getFirstname())
                .setLastName(data.getLastname())
                .choiceGender(data.getGender())
                .setMobile(data.getMobile())
                .setDateOfBirth(data.getBirthdate())
                .submitForm();

        registrationPage
                .checkFormResult(STUDENT_NAME,
                        String.join(" ", data.getFirstname(), data.getLastname()))
                .checkFormResult(GENDER, data.getGender())
                .checkFormResult(MOBILE, data.getMobile())
                .checkFormResult(DATE_OF_BIRTH, Arrays.stream(data.getBirthdate().split("^[0-9]{2}.\\w*"))
                        .reduce((s1, s2) -> String.join(",", s1.trim(), s2.trim())).get());
    }

    @Test
    void registerStudentWriteInEmptyFiledTest() {
        registrationPage
                .openRegistrationPage("/automation-practice-form")
                .submitForm()
                .checkNotOpenFormResult();
    }
}
