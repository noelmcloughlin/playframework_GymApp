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
public class Assessment extends GymApp implements Comparable<Assessment>
{
    public Long person_id;
    public Long trainer_id;     // for future use
    public float weight;
    public float height;        // for future use
    public float chest;
    public float thigh;
    public float arm;
    public float waist;
    public float hips;
    public String comment;

    //----- helpers -----

    @Override
    public int compareTo(Assessment another_assessment) {
        return getDated().compareTo(another_assessment.getDated());
    }

    @Override
    public java.lang.String toString()
    {
        return "Assessment{" +
                "'person_id': " + person_id +
                "'trainer_id': " + trainer_id +
                ", 'weight=': " + weight +
                ", 'height=': " + height +
                ", 'chest': " + chest +
                ", 'thigh': " + thigh +
                ", 'arm': " + arm +
                ", 'waist': " + waist +
                ", 'hips': " + hips +
                ", 'comment': '" + comment + "'}" + super.toString();
    }

    /**
     * List assessments by person id
     * @param person_id
     * @return List of assessments
     */
    public static List<Assessment> listAssessments(Long person_id) {
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
     * @param person_id person id
     * @param trainer_id trainer id
     * @param weight  Assessed weight in kgs.
     * @param chest   Assessed chest in inches
     * @param thigh   Assessed thigh in inches
     * @param arm     Assessed arm in inches.
     * @param waist   Assessed waist in inches.
     * @param hips    Assessed hips in inches.
     * @param comment Assessment commentary.
     */
    public Assessment(Long person_id, Long trainer_id, float weight, float chest, float thigh, float arm,
                      float waist, float hips, String comment)
    {
        this.person_id = person_id;
        this.trainer_id = trainer_id;
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

    public float getHeight() {
        return height;
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
