package qa.guru.dto;

import com.github.javafaker.Faker;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class Register {

    private final Faker faker = new Faker(Locale.ENGLISH);
    private final String address;
    private final String[] subject = new String[]{"English", "Economics", "History"};
    private final String state;
    private String city;
    private final File image;
    private final String mobile;
    private final String birthdate;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String gender;
    private final String hobbies;

    public Register() {
        this.firstname = faker.name().firstName();
        this.lastname = faker.name().lastName();
        this.email = faker.internet().emailAddress(this.firstname);
        this.birthdate = getBirthDate();
        this.hobbies = faker.options().nextElement(new String[]{"Sports", "Reading", "Music"});
        this.address = faker.address().streetAddress();
        this.image = new File("src/test/resources/hobbie.jpg");
        this.mobile = String.format("9%s", faker.phoneNumber().subscriberNumber(9));
        this.gender = faker.options().nextElement(new String[]{"Male", "Female", "Other"});
        this.state = faker.options().nextElement(new String[]{"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"});
        this.city = choiceCityByState(state);
    }

    private String getBirthDate() {
        var date = faker.date().birthday();
        return new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(date);
    }

    public String getGender() {
        return gender;
    }

    public String[] getSubject() {
        return subject;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHobbies(){
        return hobbies;
    }

    public String getState() {
        return state;
    }

    public String getCity(){
        return city;
    }

    public String choiceCityByState(String state) {
        Map<String, String[]> citesOfState = Map.of(
                    "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
                    "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
                    "Haryana", new String[]{"Panipat", "Karnal"},
                    "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
        );
        return this.city = faker.options().nextElement(citesOfState.get(state));
    }


    public File getImage() {
        return image;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthdate() {
        return birthdate;
    }
}

