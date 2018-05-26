package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Person person = Accounts.getLoggedInPerson();
        if (person == null)
            redirect("/login");
        else
            switch (person.getRole()) {
                case Member:
                    List<Assessment> assessmentlist = Assessment.findByPersonId(person.id);
                    render("dashboard.html", person, assessmentlist);
                    break;

                case Trainer:
                    Trainer trainer = Trainer.findById(person.id);
                    List<Person> memberlist = Person.listPeopleByRole(GymApp.Role.Member);
                    render("trainerboard.html", person, trainer, memberlist);
                    break;

                default:
                    redirect("/signup");
                    break;
            }
    }

    //--- add methods

    /**
     * Add Assessment passing person_id argument.
     *
     * @param person_id Primary key in Person DB
     * @param weight    member's weight
     * @param chest     member's chest
     * @param thigh     thigh
     * @param arm       arm
     * @param waist     waist
     * @param hips      hips
     * @param comment   trainer comment
     */
    public static void addAssessment(Long person_id, float weight, float chest, float thigh, float arm, float waist, float hips, String comment) {
        Assessment assessment = new Assessment(person_id, weight, chest, thigh, arm, waist, hips, comment);
        assessment.save();
        Logger.info("Added Assessment");
        redirect("/dashboard");
    }

    /**
     * Add Assessment getting person_id from session cookie
     */
    public static void addAssessment(float weight, float chest, float thigh, float arm, float waist, float hips, String comment) {
        Long person_id = Long.parseLong(session.get("logged_in_Personid"));

        Assessment assessment = new Assessment(person_id, weight, chest, thigh, arm, waist, hips, comment);
        assessment.save();
        Logger.info("Added Assessment");
        redirect("/dashboard");
    }

    //--- delete methods

    public static void deleteAssessment(Long id) {
        Assessment assessment = Assessment.findById(id);
        assessment.delete();
        Logger.info("Deleting Assessment " + id);
        redirect("/dashboard");
    }

    public static void deleteMember(Long id) {
        Person person = Person.findById(id);
        if (person != null)
        {
            Logger.info("Deleting Member " + id);
            person.delete();
            Member member = Member.findById(id);
            if (member != null)
                member.delete();

            List<Assessment> assessmentlist = Assessment.findByPersonId(id);
            for (Assessment assessment: assessmentlist)
            {
                assessment.delete();
                Logger.info("Deleting Assessment " + assessment.id);
            }

        }
        redirect("/dashboard");
    }
}