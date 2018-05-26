package models;

/**
 * Member class implements membership stuff. Extends Domain model class.
 **/

import javax.persistence.*;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
public class Member extends GymApp
{
    public Long person_id;
    public float startWeight;
    public String chosenPackage;
    public HashMap<String, Assessment> Assessments = new HashMap<>();


    //-------- Constructors -------//

    public Member(Long person_id, float startWeight, String chosenPackage, HashMap<String, Assessment> assessments) {
        this.person_id = person_id;
        this.startWeight = startWeight;
        this.chosenPackage = chosenPackage;
        Assessments = assessments;
    }


    //-------- Getter's and Setter's -------//

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }
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



    //----- helpers -----//

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
     * Find the Member  this member represents.
     *
     * @param person_id Person id (primary key in user DB)
     * @return Member Instance
     */
    public static Member findById(Long person_id) {
        return find("person_id", person_id).first();
    }


    public static Member findByUsername(Long person_id) {
        return find("username", person_id).first();
    }

    /**
     * Get Assessment records
     *
     * @return Hashmap Assessments
     */
    public HashMap<String, Assessment> getAssessments() {
        return Assessments;
    }

    /**
     * Set Assessments record
     *
     * @param assessments Hashmap
     */
    public void setAssessments(HashMap<String, Assessment> assessments) {
        Assessments = assessments;
    }

    /**
     * Returns the latest assessment based on last entry (by calendar date).
     * It uses a TreeMap to extract keys in ascending order
     * Return null if no assessments.
     *
     * @return Assessment The most recent assessment
     */
    public Assessment latestAssessment() {
        SortedSet<String> dates = new TreeSet<>(getAssessments().keySet());
        if (getAssessments().isEmpty())
            return null;
        return getAssessments().get(dates.last());
    }

    /**
     * Returns the assessments dates (as strings) sorted in date order.
     * Return empty set if no assessments.
     *
     * @return SortedSet Sorted assessment dates.
     */
    public SortedSet sortedAssessmentDates() {
        // return keys sorted in ascending order
        return new TreeSet<>(getAssessments().keySet());
    }

}
