package models;

/**
 * Gym Domain model. Extends Generic model class.
 **/

import play.db.jpa.GenericModel;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class GymApp extends GenericModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date dated;
    public Date updated;

    // Enumerate Roles
    public enum Role {
        Guest, Member, Trainer;
    }

    // Enumerate Genders
    public enum Gender {
        Female, Male;
    }

    //--- helpers --

    /**
     * Find record by id for derived classes.
     * @param id primary key
     * @return GymApp record
     */
    protected static GymApp findById(Long id) {
        return find("id", id).first();
    }

    /**
     * String representation of class for derived classes;
     * @return String instance
     */
    @java.lang.Override
    public java.lang.String toString() {
        return "GymApp{" +
                "id=" + id +
                '}' + super.toString();
    }
    //---- constructors ----

    /**
     * Default constructor of instance
     */
    public GymApp() {
    }

    //---- Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }
}
