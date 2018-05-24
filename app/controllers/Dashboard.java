package controllers;

import models.Person;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Person person = Accounts.getLoggedInPerson();
    List<Assessment> assessmentlist = Assessment.findallByPersonId(person.id);
    render("dashboard.html", person, assessmentlist);
  }

  //--- add methods

    /**
     * Add Assessment passing person_id argument.
     *
     * @param person_id Primary key in Person DB
     * @param weight member's weight
     * @param chest  member's chese
     * @param thigh  thigh
     * @param arm   arm
     * @param waist waist
     * @param hips hips
     * @param comment trainer comment
     */
    public static void addAssessment(Long person_id, float weight, float chest, float thigh, float arm, float waist, float hips, String comment)
    {
        Assessment assessment = new Assessment(person_id, weight, chest, thigh, arm, waist, hips, comment);
        assessment.save();
        Logger.info("Added Assessment");
        redirect("/dashboard");
    }

    /**
     * Add Assessment getting person_id from session cookie
     */
    public static void addAssessment(float weight, float chest, float thigh, float arm, float waist, float hips, String comment)
    {
    Long person_id = Long.parseLong(session.get("logged_in_Personid"));

    Assessment assessment = new Assessment(person_id, weight, chest, thigh, arm, waist, hips, comment);
    assessment.save();
    Logger.info("Added Assessment");
    redirect("/dashboard");
    }

    //--- delete methods

    public static void deleteAssessment(Long id)
    {
    Assessment assessment = Assessment.findById(id);
    assessment.delete();
    Logger.info("Deleting Assessment " + id);
    redirect("/dashboard");
    }
}
