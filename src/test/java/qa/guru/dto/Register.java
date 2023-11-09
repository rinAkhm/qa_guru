package qa.guru.dto;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Register {

    private String address = "London SW1A 0AA, Great Britain";
    private String[] subject = new String[]{"English", "Economics", "History"};
    private String state;
    private String city;
    private File image = new File("src/test/resources/hobbie.jpg");
    private String mobile = "9178959922";
    private String firstname = "Fedor";
    private String lastname = "Ivanov";
    private String email;
    private String gender;
    private String birthdate;
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

    private String getBirthDate() {
        LocalDate birthDate = LocalDate.from(LocalDate.now().minusYears(10));
        return birthDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH));

    }

    public String getGender() {
        if (this.gender == null) {
            String[] gender = new String[]{"Male", "Female", "Other"};
            return this.gender = gender[new Random().nextInt(gender.length)];
        }
        return this.gender;
    }

    public String getHobbies() {
        if (this.hobbies == null) {
            String[] hobbies = new String[]{"Sports", "Reading", "Music"};
            return this.hobbies = hobbies[new Random().nextInt(hobbies.length)];
        }
        return this.hobbies;
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

    public String getState() {
        if (this.state == null) {
            State[] states = new State[]{State.NCR, State.HARYANA, State.RAJASTHAN, State.UTTAR_PRADESH};
            return this.state = states[new Random().nextInt(states.length)].getState();
        }
        return this.state;
    }

    public String getCity(String state) {
        if (this.city == null) {
            Map<String, String[]> citesOfState = Map.of(
                    "NCR", new String[]{"Delhi", "Gurgaon", "Noida"},
                    "Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"},
                    "Haryana", new String[]{"Panipat", "Karnal"},
                    "Rajasthan", new String[]{"Jaipur", "Jaiselmer"}
            );
            return this.city = citesOfState.get(state)[new Random().nextInt(citesOfState.get(state).length)];
        }
        return this.city;
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

    @Override
    public String toString() {
        return "qa.guru.dto.Register{" +
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

