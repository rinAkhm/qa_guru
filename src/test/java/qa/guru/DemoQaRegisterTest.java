package qa.guru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qa.guru.dto.Register;
import qa.guru.pages.RegistrationPage;

import java.util.Arrays;

import static qa.guru.TableResultData.*;


public class DemoQaRegisterTest extends BaseTest {
    private RegistrationPage registrationPage;
    private Register data;

    @BeforeEach
    void setUp() {
        registrationPage = new RegistrationPage();
        registrationPage.openRegistrationPage("/automation-practice-form");
        data = new Register("", "");

    }

    @Test
    void registerStudentWriteInFullFieldTest() {
        data = new Register("", "");
        registrationPage
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
                .choiceCity(data.getCity(data.getState()))
                .submitForm();

        registrationPage
                .checkFormResult(STUDENT_NAME,
                        String.join(" ", data.getFirstname(), data.getLastname()))
                .checkFormResult(STUDENT_EMAIL, data.getEmail())
                .checkFormResult(GENDER, data.getGender())
                .checkFormResult(MOBILE, data.getMobile())
                .checkFormResult(SUBJECTS, Arrays.stream(data.getSubject())
                        .reduce((s1, s2) -> String.join(", ", s1, s2)).get())
                .checkFormResult(HOBBIES, data.getHobbies())
                .checkFormResult(PICTURE, data.getImage().getName())
                .checkFormResult(ADDRESS, data.getAddress())
                .checkFormResult(STATE_AND_CITY, String.join(" ", data.getState(), data.getCity(data.getState())));
    }

    @Test
    void registerStudentWriteInRequiredFieldTest() {
        data = new Register("", "");
        registrationPage
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
                .checkFormResult(MOBILE, data.getMobile());
    }

    @Test
    void registerStudentWriteInEmptyFiledTest() {
        registrationPage
                .submitForm()
                .checkNotOpenFormResult();
    }
}
