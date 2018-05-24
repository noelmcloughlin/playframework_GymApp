package models;

/**
 * Trainer class implements trainership stuff. Extends Domain model class.
 **/

import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.*;

@Entity
public class Trainer extends GymModel {
    public Long person_id;
    public String speciality;
    private static final Role ROLE = Role.Trainer;

    //----- helpers -----//

    public static Role getRole() {
        return ROLE;
    }

    /**
     *  Return a string representation of the Trainer.
     * @return String instance
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();

        asString.append(asString).append("Trainer{'person_id': '").append(person_id).append("', ");
        asString.append(asString).append("'speciality': '").append(speciality).append("',}");

        return asString.toString() + super.toString();
    }

    //-------- Constructors -------//

    /**
     * Default constructor for Trainer
     * @param person_id person id (entity in person database)
     * @param speciality trainers staring weight
     */
    public Trainer(Long person_id, String speciality) {
        this.person_id = person_id;
        this.speciality = speciality;
    }

    //-------- Getter's and Setter's -------//

    /**
     * Get the trainers speciality
     * @return String myspeciality
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     * Set the Trainers speciality
     * @param speciality String
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * Get person id (primary key in person DB)
     * @return Long personId
     */
    public Long getPerson_id() {
        return person_id;
    }

    /**
     * Set person id (primary key in person DB)
     * @param person_id Long
     */
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }


}
