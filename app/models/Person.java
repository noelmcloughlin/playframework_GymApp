package models;

/**
 * Member class implements personal stuff, extends Domain model class.
 **/

// downloaded from maven in IntelliJ

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;

import play.cache.Cache;

@Entity
public class Person extends GymModel {
    // Personal attributes
    public String firstname;
    public String lastname;
    public String dob;
    public String address;
    public String gender;

    // Digital attributes
    public String username;
    public String passwordHash;
    public String email;

    private static final Role ROLE = Role.Guest;

    //----- helpers -----//

    public static Role getRole() {
        return ROLE;
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.passwordHash);
    }

    /**
     * Return a string representation of the Member data structure.
     * Include values of inherited attributes.
     *
     * @return String string representation of object.
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();

        asString.append(asString).append("Person{'firstname': '").append(firstname).append("', ");
        asString.append(asString).append("'lastname': '").append(lastname).append("', ");
        asString.append(asString).append("'dob': '").append(dob).append("', ");
        asString.append(asString).append("'address': '").append(address).append("', ");
        asString.append(asString).append("'gender': '").append(gender).append("', ");
        asString.append(asString).append("'username': '").append(username).append("', ");
        asString.append(asString).append("'passwordHash': '").append(passwordHash).append("',} ");
        asString.append(asString).append("'email': ").append(email).append("} ");

        return asString.toString() + super.toString();
    }

    /**
     * Find person by username
     *
     * @param username digital username
     * @return Person
     */
    public static Person findByUsername(String username) {
        return find("username", username).first();
    }

    //-------- Constructor --------//


    /**
     * Minimal Constructor for Person (i.e. new user registration)
     *
     * @param firstname
     * @param lastname
     * @param username
     * @param password
     * @param email
     */
    public Person(String firstname, String lastname, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        setPasswordHash(password);
        this.email = email;
    }

    /**
     * Default Constructor, returning a complete Person instance.
     *
     * @param firstname persons first name
     * @param lastname  persons surname
     * @param dob       Date of Birth
     * @param address   personal address
     * @param gender    personal gender
     * @param username  persons system username
     * @param password  persons system passwordHash
     * @param email     email address
     **/
    public Person(String firstname, String lastname, String dob, String address, String gender, String username, String password, String email) {
        this(firstname, lastname, username, password, email);
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }

    //-------- Getters and Setters -----

    /**
     * Get person's first name
     *
     * @return String persons first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set person's first name within constraints
     *
     * @param firstname String
     */
    public void setFirstname(String firstname) {
        if (firstname.trim().length() == 0)
            this.firstname = "unspecified";
        else
            this.firstname = firstname.trim();
    }

    /**
     * Get person's surname
     *
     * @return lastname String
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set person's surname within boundaries
     *
     * @param lastname String
     */
    public void setLastname(String lastname) {
        lastname.trim();
        if (lastname.length() > Constants.MAX_SNAME_LENGTH)
            this.lastname = lastname.substring(0, Constants.MAX_SNAME_LENGTH);
        else if (lastname.length() == 0)
            this.lastname = "unspecified";
        else
            this.lastname = lastname;
    }


    /**
     * Get person's date of birth
     *
     * @return String dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * Set person's date of birth ('ddMMyy' format)
     *
     * @param dob String
     */
    public void setDob(String dob) {
        if (dob.trim().length() == 0)
            this.dob = "unspecified";
        else
            this.dob = dob.trim();
    }

    /**
     * Get person's postal address (one liner)
     *
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set person's postal address (one liner).
     *
     * @param address address
     */
    public void setAddress(String address) {
        if (address.length() == 0)
            this.address = "Unspecified";
        else
            this.address = address;
    }

    /**
     * Get person's gender (Male or Female)
     *
     * @return String gender
     */
    public String getGender() {
        return gender;
    }


    /**
     * Set person's gender (Male or Female)
     *
     * @param gender gender
     */
    public void setGender(String gender) {
        if (gender.trim().length() == 0)
            this.gender = "unspecified";
        else
            switch (gender.toUpperCase().trim().charAt(0)) {
                case 'M':
                    this.gender = gender;
                    break;
                default:
                    this.gender = "F";
                    break;
            }
    }

    /**
     * Get member's digital username
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set persons's digital username
     *
     * @param username String
     */
    public void setUsername(String username) {
        username.trim();
        if (username.length() > Constants.MAX_UNAME_LENGTH)
            this.username = username.substring(0, Constants.MAX_UNAME_LENGTH);
        else if (username.length() == 0)
            this.username = "fixme";
        else
            this.username = username;
    }

    /**
     * Get (hashed) member's passwordHash
     *
     * @return String passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Set persons's passwordHash. Hash using 'BCrypt->hashpw' method if necessary.
     * <p>
     * Idea from MRIdb play application.
     *
     * @param passwordHash String
     */
    public void setPasswordHash(String passwordHash) {
        if (passwordHash.trim().length() == 0)
            this.passwordHash = "password";

        // password hashed
        if (passwordHash.startsWith("$2a$"))
            this.passwordHash = passwordHash;
        else
            this.passwordHash = BCrypt.hashpw(passwordHash, BCrypt.gensalt());
    }

    /**
     * Get person's email address
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set person's email address
     *
     * @param email email
     */
    public void setEmail(String email) {
        if (email.trim().length() == 0)
            this.email = "unspecified";
        else
            this.email = email;
    }


    /**
     * Clear user cache after updating person instance

     @PostUpdate public void postUpdate() {
     Cache.delete(username);
     } */

}
