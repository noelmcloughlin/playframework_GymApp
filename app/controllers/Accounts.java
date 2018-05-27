package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller
{
    public static void signup() {
        render("person/signup.html");
    }

    public static void login() {
        render("person/login.html");
    }

    public static void register(String firstname, String lastname, String username, String password,
                                String email, GymApp.Gender gender, float height, GymApp.Role role)
    {
        Logger.info("Registering new person " + username);
        Person person = new Person(firstname, lastname, username, password, email, gender, height, role);
        person.save();
        switch (person.getRole())
        {
            case Member:
                Member member = new Member(person.id, 0, "Standard");
                member.save();
                break;

            case Trainer:
                Trainer trainer = new Trainer(person.id, "Fitness");
                trainer.save();
                break;
        }
        System.out.println("gender =" + gender);
        redirect("/login");
    }

    public static void showMe() {
        Person person = getLoggedInPerson();
        render("person/account.html", person);
    }

    public static void editMe(String firstname, String lastname, String email, String username, String password,
                              GymApp.Gender gender, String dob, String address)
    {
        Person person = getLoggedInPerson();

        Logger.info("Updating profile " + username);
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setDob(dob);
        person.setAddress(address);

        person.setEmail(email);
        person.setUsername(username);
        // person.setPasswordHash(password);    TODO hash handling ..
        person.setGender(gender);
        person.save();
        redirect("/dashboard");
    }

    public static void authenticate(String username, String password)
    {
        Logger.info("Authenticating " + username);

        Person person = Person.findByUsername(username);
        if ((person != null) && (person.checkPassword(password))) {
            Logger.info("Authentication successful");
            session.put("logged_in_Personid", person.id);
            redirect("/dashboard");
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Person getLoggedInPerson()
    {
        Person person = null;
        if (session.contains("logged_in_Personid")) {
            String person_id = session.get("logged_in_Personid");
            person = Person.findById(Long.parseLong(person_id));
        } else {
            login();
        }
        return person;
    }
}
