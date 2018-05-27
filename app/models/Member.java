package models;

/**
 * Member class implements membership stuff. Extends Domain model class.
 **/

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
public class Member extends GymApp
{
    public Long person_id;
    public float startWeight;
    public String chosenPackage;


    //----- helpers -----

    /**
     *  Return a string representation of the Member data structure.
     *  Include inherited attributes from super class
     *
     * @return String instance
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();

        asString.append(asString).append("Member{'startWeight': ").append(startWeight).append("', ");
        asString.append(asString).append("'chosenPackage': '").append(chosenPackage).append("',} ");

        return asString.toString() + super.toString();
    }

    /**
     * Find member by person id
     *
     * @param person_id person id of member
     * @return Member Member
     */
    public static Member findByPersonId(Long person_id)
    {
        return find("person_id", person_id).first();
    }


    //-------- Constructors -------//

    public Member(Long person_id, float startWeight, String chosenPackage) {
        this.person_id = person_id;
        this.startWeight = startWeight;
        this.chosenPackage = chosenPackage;
    }

    //-------- Getter's and Setter's -------//

    /**
     * Get startWeight
     *
     * @return float startWeight
     */
    public float getStartWeight() {
        return startWeight;
    }

    /**
     * Set startWeight
     *
     * @param startWeight float
     */
    public void setStartWeight(float startWeight)
    {
        if (startWeight < Constants.MIN_WEIGHT)
            this.startWeight = Constants.MIN_WEIGHT;
        else if (startWeight > Constants.MAX_WEIGHT)
            this.startWeight = Constants.MAX_WEIGHT;
        else
            this.startWeight = startWeight;
    }

    /**
     * Get member's chosen gym package
     *
     * @return chosenPackage String
     */
    public String getChosenPackage() {
        return chosenPackage;
    }

    /**
     * Set member's chosen gym package
     *
     * @param chosenPackage String
     */
    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    //--------- Helpers -----
    // Moved from (retired) GymUtility due to issues/bugs ...
    // Unexpected error : Model models.GymUtility is not managed by any plugin

    /**
     * Return the BMI for member based on calculation of: weight divided by the square of the height.
     *
     * @param person    Members personal id
     * @return double
     */
    public static double calculateBMI(Person person)
    {
        Member member = Member.findByPersonId(person.id);
        Assessment assessment = member.latestAssessment(person.id);

        // BMI is weight divided by the square of the height.
        if (member == null || assessment == null)
            return 0.0f;

        return (assessment.getWeight() / Math.pow(person.getHeight(), 2)) - 0.04f;
    }


    /**
     * Return the category the BMI belongs to:
     * - SEVERELY UNDERWEIGHT, UNDERWEIGHT, NORMAL, OVERWEIGHT, MODERATELY OBESE, SEVERELY OBESE
     *
     * @param bmiValue BMI value id
     * @return BMI determination
     */
    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < Constants.SEVERELY_UNDERWEIGHT)
            return "SEVERELY UNDERWEIGHT";
        if (bmiValue < Constants.UNDERWEIGHT)
            return "UNDERWEIGHT";
        if (bmiValue < Constants.NORMAL)
            return "NORMAL";
        if (bmiValue < Constants.OVERWEIGHT)
            return "OVERWEIGHT";
        if (bmiValue < Constants.MODERATELY_OBESE)
            return "MODERATELY OBESE";
        else
            return "SEVERELY OBESE";
    }


    /**
     * Return ideal body weight, according to Devine, given gender & height.
     * <p>
     * Devine idealizes that ..
     * For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
     * If member is 5 feet or less, assume 50kg for male and 45.5kg for female.
     *
     * @param gender Male of Female only
     * @param height height in metres
     * @return Ideal body weight in kgs
     */
    public static float idealBodyWeight(Gender gender, double height)
    {
        // Calculate number of inches the users height exceeds five feet.
        double i = Math.max(0.0f, (height - Constants.METERS_IN_5FOOT) * Constants.INCHES_IN_METER);

        // Calculate weight idealized by Devine.
        switch (gender) {
            case Male:
                return Math.round(Constants.DEVINE_IDEAL_MALE + (i * Constants.DEVINE_INCR));
            default:
                return Math.round(Constants.DEVINE_IDEAL_FEMALE + (i * Constants.DEVINE_INCR));
        }
    }

    /**
     * a boolean to indicate if the member has an ideal body weight based on the "Devine" formula.
     *
     * @param person Person
     * @return true or false
     */
    public static boolean isIdealBodyWeight(Person person)
    {
        Member member = Member.findByPersonId(person.id);
        Assessment assessment = member.latestAssessment(person.id);
        if (assessment == null)
            return false;

        return Math.round(assessment.getWeight()) <= idealBodyWeight(person.getGender(), person.getHeight());
    }

    /**
     * Get assessments for member by person_id
     * @param person_id person id
     * @return List of assessments
     */
    public List<Assessment> getAssessmentList(Long person_id)
    {
        return Assessment.listAssessments(person_id);
    }



    /**
     * Returns the latest assessment based on last entry (by calendar date).
     * Return null if no assessments.
     *
     * @return Assessment The most recent assessment
     */
    public Assessment latestAssessment(Long person_id)
    {
        List<Assessment> assessmentList = getAssessmentList(person_id);
        if (assessmentList == null || assessmentList.size() == 0)
            return null;

        //Collections.sort( assessmentList );
        return assessmentList.get(assessmentList.size()-1);
    }
}
