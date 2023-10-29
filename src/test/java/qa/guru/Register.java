package qa.guru;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Register {

    private String address = "London SW1A 0AA, Great Britain";
    private String[] subject = new String[] {"English", "Economics", "History"};
    private String state = "Uttar Pradesh";
    private String city = "Agra";
    private File image = new File("src/test/resources/hobbie.jpg");
    private String mobile = "798172012055";
    private String firstname = "Fedor";
    private String lastname = "Ivanov";
    private String email;
    private String gender;
    private String birthdate ;
    private String hobbies;

    public Register(String firstname, String lastname) {
        if (!firstname.isEmpty() && !lastname.isEmpty()) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        this.email = String.format("%s11@mail.ru", this.lastname.toLowerCase());
        this.gender = getGender();
        this.birthdate = getBirthDate();
        this.hobbies = getHobbies();
    }

    private String getBirthDate(){
        LocalDate birthDate = LocalDate.from(LocalDate.now().minusYears(10));
        return birthDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH));
    }


    public String getGender() {
         String[] gender = new String[] {"Male", "Female", "Other"};
         return this.gender = gender[new Random().nextInt(gender.length)];
    }

    public String getHobbies() {
         String[] hobbies = new String[] {"Sports", "Reading", "Music"};
         return this.hobbies = hobbies[new Random().nextInt(hobbies.length)];
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "qa.guru.Register{" +
                "subject=" + Arrays.toString(subject) +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}

