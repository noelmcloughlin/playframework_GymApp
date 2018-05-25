package models;

import javax.persistence.Entity;

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

    //-------- Constructors -------//

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
}
