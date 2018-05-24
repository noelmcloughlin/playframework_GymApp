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
    List<Assessment> assessmentlist = Assessment.findAll();
    render("dashboard.html", person, assessmentlist);
  }

  public static void addAssessment(float weight, float chest, float thigh, float arm, float waist, float hips, String comment)
  {
    Assessment assessment = new Assessment(weight, chest, thigh, arm, waist, hips, comment);
    assessment.save();
    Logger.info("Added Assessment");
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id)
  {
    Assessment assessment = Assessment.findById(id);
    assessment.delete();
    Logger.info("Deleting Assessment " + id);
    redirect("/dashboard");
  }
}
