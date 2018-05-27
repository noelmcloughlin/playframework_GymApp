package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.Collections;
import java.util.List;

public class Dashboard extends Controller
{
    public static void dashboard()
    {
        Logger.info("Rendering Dashboard");
        Person person = Accounts.getLoggedInPerson();
        Member member = Member.findByPersonId(person.id);
        if (person == null)
            redirect("/login");
        else
            switch (person.getRole())
            {
                case Member:
                    List<Assessment> assessmentlist = member.getAssessmentList(person.id);
                    //Collections.sort(assessmentlist);
                    render("person/dashboard.html", person, member, assessmentlist);
                    break;

                case Trainer:
                    Trainer trainer = Trainer.findById(person.id);
                    List<Person> memberlist = Person.listPeopleByRole(GymApp.Role.Member);
                    render("trainer/dashboard.html", person, trainer, memberlist);
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
     * @param person_id Primary id
     * @param trainer_id Author id
     * @param weight    member's weight
     * @param chest     member's chest
     * @param thigh     thigh
     * @param arm       arm
     * @param waist     waist
     * @param hips      hips
     * @param comment   trainer comment
     */
    public static void addAssessment(Long person_id, Long trainer_id, float weight, float chest, float thigh,
                                     float arm, float waist, float hips, String comment)
    {
        Assessment assessment = new Assessment(person_id, trainer_id, weight, chest, thigh, arm, waist, hips, comment);
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

    public static void deleteMember(Long id)
    {
        Person person = Person.findById(id);
        if (person != null)
        {
            Logger.info("Deleting Person " + id);
            person.delete();
            Member member = Member.findByPersonId(id);
            if (member != null)
                Logger.info("Deleting Member " + id);
                member.delete();

            List<Assessment> assessmentlist = Assessment.listAssessments(id);
            for (Assessment assessment: assessmentlist)
            {
                assessment.delete();
                Logger.info("Deleting Assessment " + assessment.id);
            }

        }
        redirect("/dashboard");
    }

    public static void showMember(Long id)
    {
        Logger.info("Rendering showMember " + id);
        Person person = Person.findById(id);
        Member member = Member.findByPersonId(person.id);
        List<Assessment> assessmentlist = member.getAssessmentList(person.id);
        //Collections.sort(assessmentlist);
        render("trainer/member.html", person, member, assessmentlist);
    }
}
