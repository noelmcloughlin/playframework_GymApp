/**
 * Assessment class implements member assessment properties and methods. Extends Domain model class.
 **/

package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Assessment class implementation (JPA entity too).
 */
@Entity
public class Assessment extends GymApp
{
    public Long person_id;
    public float weight;
    public float height;
    public float chest;
    public float thigh;
    public float arm;
    public float waist;
    public float hips;
    public String comment;

    //----- helpers -----

    @Override
    public java.lang.String toString()
    {
        return "Assessment{" +
                "'person_id': " + person_id +
                ", 'weight=': " + weight +
                ", 'height=': " + height +
                ", 'chest': " + chest +
                ", 'thigh': " + thigh +
                ", 'arm': " + arm +
                ", 'waist': " + waist +
                ", 'hips': " + hips +
                ", 'comment': '" + comment + "'}" + super.toString();
    }

    @javax.persistence.OrderBy(value = "date")
    public static List<Assessment> findallByPersonId(Long person_id) {
        return find("person_id", person_id).fetch();
    }

    //---- constructors

    /**
     * Default constructor only exists for the sake of JPA (apparently ;-)
     * You won’t use it directly so its protected
     */
    protected Assessment() {
    }

    /**
     * Constructs Assessment instance from supplied arguments.
     *
     * @param weight  Assessed weight in kgs.
     * @param chest   Assessed chest in inches
     * @param thigh   Assessed thigh in inches
     * @param arm     Assessed arm in inches.
     * @param waist   Assessed waist in inches.
     * @param hips    Assessed hips in inches.
     * @param comment Assessment commentary.
     */
    public Assessment(Long person_id, float weight, float chest, float thigh, float arm, float waist, float hips, String comment)
    {
        this.person_id = person_id;
        this.dated = this.updated = new Date();
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.arm = arm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
        super.setDated(new Date());
    }


    //------ Getters and Setters ------//

    /**
     * Get assessment member id
     * @return Long person_id
     */
    public Long getMember_id() {
        return person_id;
    }

    /**
     * Set assessment member id
     * @param person_id Long
     */
    public void setMember_id(Long person_id) {
        this.person_id = person_id;
    }

    /**
     * Get assessed weight
     *
     * @return float weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Set assessed weight
     *
     * @param weight float
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * get assessed chest measurement
     *
     * @return float chest
     */
    public float getChest() {
        return chest;
    }

    /**
     * Set assessed chest measurement
     *
     * @param chest float
     */
    public void setChest(float chest) {
        this.chest = chest;
    }

    /**
     * Get assessed thigh measurement
     *
     * @return float thigh
     */
    public float getThigh() {
        return thigh;
    }

    /**
     * Set thigh measurement
     *
     * @param thigh float
     */
    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    /**
     * Get upper arm measurement
     *
     * @return float arm
     */
    public float getArm() {
        return arm;
    }

    /**
     * Set upper arm measurement
     *
     * @param arm float
     */
    public void setArm(float arm) {
        this.arm = arm;
    }

    /**
     * Get waist measurement
     *
     * @return waist float
     */
    public float getWaist() {
        return waist;
    }

    /**
     * Set waist measurement
     *
     * @param waist float
     */
    public void setWaist(float waist) {
        this.waist = waist;
    }

    /**
     * Get Hip measurement
     *
     * @return hips float
     */
    public float getHips() {
        return hips;
    }

    /**
     * Set Hip measurement
     *
     * @param hips float
     */
    public void setHips(float hips) {
        this.hips = hips;
    }

    /**
     * Get assessment comment
     *
     * @return String comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set assessment comment.
     *
     * @param comment String
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}
