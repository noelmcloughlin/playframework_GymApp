package models;

/**
 * Member class implements personal stuff, extends Domain model class.
 **/

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Person extends GymApp
{
    public String firstname;    // personal attrs
    public String lastname;
    public String dob;
    public String address;
    public float height;
    public Gender gender;

    public String username;     // digital attrs
    public String passwordHash;
    public String email;
    public Role role;

    //----- helpers -----//

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
        asString.append(asString).append("'email': ").append(email).append("'}, ");
        asString.append(asString).append("'role': '").append(role).append("'}");

        return asString.toString() + super.toString();
    }

    /**
     * Find person by username
     *
     * @param username digital username
     * @return Person
     */
    public static Person findByUsername(String username)
    {
        return find("username", username).first();
    }

    /**
     * Find Person by Id
     * @param id primary key
     * @return Person
     */
    public static Person findById(Long id)
    {
        return find("id", id).first();
    }

    /**
     * list all people with Role 'role'.
     * @param role Role
     * @return List<> List of people with role
     */
    public static List<Person> listPeopleByRole(Role role)
    {
        return find("Role", role).fetch();
    }

    //-------- Constructor --------//


    /**
     * Minimal Constructor for Person (i.e. new Person registration)
     *
     * @param firstname
     * @param lastname
     * @param username
     * @param password
     * @param email
     */
    public Person(String firstname, String lastname, String username, String password, String email, Role role)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        setPasswordHash(password);
        this.email = email;
        this.role = role;
        this.dated = this.updated = new Date();
    }

    //-------- Getters and Setters -----

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get users first name
     *
     * @return String users first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set users first name within constraints
     *
     * @param firstname String
     */
    public void setFirstname(String firstname)
    {
        if (firstname.trim().length() == 0)
            this.firstname = "unspecified";
        else
            this.firstname = firstname.trim();
    }

    /**
     * Get users surname
     *
     * @return lastname String
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set users surname within boundaries
     *
     * @param lastname String
     */
    public void setLastname(String lastname)
    {
        lastname.trim();
        if (lastname.length() > Constants.MAX_SNAME_LENGTH)
            this.lastname = lastname.substring(0, Constants.MAX_SNAME_LENGTH);
        else if (lastname.length() == 0)
            this.lastname = "unspecified";
        else
            this.lastname = lastname;
    }

    /**
     * Get personal height
     * @return float height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set personal height
     * @param height float
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Get users date of birth
     *
     * @return String dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * Set users date of birth ('ddMMyy' format)
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
     * Get users postal address (one liner)
     *
     * @return String address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set users postal address (one liner).
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
     * Get users gender (Male or Female)
     *
     * @return Constants.Gender gender
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * Set personal gender (enum Gender), default female.
     *
     * @param gender gender
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * Get users role
     * @return
     */
    public Role getRole()
    {
        return this.role;
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
     * Set users's digital username
     *
     * @param username String
     */
    public void setUsername(String username)
    {
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
     * Set users's passwordHash. Hash using 'BCrypt->hashpw' method if necessary.
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
     * Get users email address
     *
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set users email address
     * @param email email
     */
    public void setEmail(String email)
    {
        if (email.trim().length() == 0)
            this.email = "unspecified";
        else
            this.email = email;
    }


}
