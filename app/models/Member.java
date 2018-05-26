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

    //-------- Constructors -------//

    public Member(Long person_id, float startWeight, String chosenPackage) {
        this.person_id = person_id;
        this.startWeight = startWeight;
        this.chosenPackage = chosenPackage;
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
}
