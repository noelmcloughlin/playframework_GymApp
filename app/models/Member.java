package models;

/**
* Member class implements membership stuff. Extends Domain model class.
**/

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.*;

@Entity
public class Member extends GymModel
{
    public Long person_id;
    public String gender;

    public float startWeight;
    public float height;
    public String chosenPackage;
    public HashMap<String, Assessment> Assessments = new HashMap<>();
    private static final Role ROLE = Role.Member;

    //----- helpers -----//

    public static Role getRole() {
        return ROLE;
    }

    /**
     *  Return a string representation of the Member data structure.
     *  Include inherited attributes from super class
     *
     * @return String instance
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();

        asString.append(asString).append("Member{'person_id': ").append(person_id).append("', ");
        asString.append(asString).append("'startWeight': '").append(startWeight).append("', ");
        asString.append(asString).append("'height': '").append(height).append("', ");
        asString.append(asString).append("'chosenPackage': '").append(chosenPackage).append("',} ");

        return asString.toString() + super.toString();
    }

    /**
     * Find the Member  this member represents.
     *
     * @param person_id Person id (primary key in PErson DB)
     * @return Member Instance
     */
    public static Member findByPersonId(Long person_id)
    {
        return find("person_id", person_id).first();
    }

    public static Member findById(Long person_id)
    {
        return find("username", person_id).first();
    }


    /**
     * Returns the latest assessment based on last entry (by calendar date).
     * It uses a TreeMap to extract keys in ascending order
     * Return null if no assessments.
     *
     * @return Assessment The most recent assessment
     */
    public Assessment latestAssessment()
    {
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
    public SortedSet sortedAssessmentDates()
    {
        // return keys sorted in ascending order
        return new TreeSet<>(getAssessments().keySet());
    }


    //-------- Constructors -------//

    /**
     * Default constructor for Member
     * @param person_id person id (entity in person database)
     * @param startWeight members staring weight
     * @param height members height
     * @param chosenPackage chosen gym package
     */
    public Member(Long person_id, float startWeight, float height, String chosenPackage)
    {
        this.person_id = person_id;
        this.startWeight = startWeight;
        this.height = height;
        this.chosenPackage = chosenPackage;
    }

    //-------- Getter's and Setter's -------//

    /**
     * Get person id (index in person DB)
     *
     * @return Long personId
     */
    public Long getPerson_id() {
        return person_id;
    }

    /**
     * Set person id (index in person DB)
     *
     * @param person_id Long
     */
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    /**
     *  Get members gender
     * @return String gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set members gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * Get start weight
     *
     * @return float weight
     */
    public float getStartWeight() {
        return startWeight;
    }

    /**
     * Set start weight
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
     * Get members height
     *
     * @return float height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Set members height
     *
     * @param height float
     */
    public void setHeight(float height) {
        if (height < Constants.MIN_HEIGHT)
            this.height = Constants.MIN_HEIGHT;
        else if (height > Constants.MAX_HEIGHT)
            this.height = Constants.MAX_HEIGHT;
        else
            this.height = height;
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




    //--------- Helpers --------//

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
}
