package models;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Trainer class implements trainership stuff. Extends Domain model class.
 **/

@Entity
public class Trainer extends GymApp
{
    public Long person_id;
    public String speciality;

    //----- helpers -----//

    /**
     *  Return a string representation of the Trainer.
     * @return String instance
     */
    @Override
    public String toString() {
        StringBuilder asString = new StringBuilder();

        asString.append(asString).append("'speciality': '").append(speciality).append("'}");

        return asString.toString() + super.toString();
    }

    /**
     * Find the Trainer record by id
     * @param person_id Person primary key in member DB
     * @return Trainer Instance
     */
    public static Trainer findById(Long person_id) {
        return find("person_id", person_id).first();
    }

    //-------- Constructors -------//

    public Trainer(Long person_id, String speciality) {
        this.person_id = person_id;
        this.speciality = speciality;
        this.dated = this.updated = new Date();
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
}
