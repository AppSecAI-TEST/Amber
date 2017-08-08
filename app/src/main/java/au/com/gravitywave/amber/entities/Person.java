package au.com.gravitywave.amber.entities;

/**
 * Created by georg on 4/08/2017.
 */

public class Person extends EntityBase {

    private int personId;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private int rating;
    private String emailAddress;
    private String password;
    private String homeAddressPlaceId;
    private String status;

    public Person(int personId, String firstName, String lastName, String gender, int age, int rating, String emailAddress, String password, String homeAddressPlaceId, String status) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.rating = rating;
        this.emailAddress = emailAddress;
        this.password = password;
        this.homeAddressPlaceId = homeAddressPlaceId;
        this.status = status;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeAddressPlaceId() {
        return homeAddressPlaceId;
    }

    public void setHomeAddressPlaceId(String homeAddressPlaceId) {
        this.homeAddressPlaceId = homeAddressPlaceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
