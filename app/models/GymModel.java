package models;

/**
 * Gym Domain class implements common 'Play domain' stuff. Extends Generic model class.
 **/

import javax.persistence.*;

import play.db.jpa.GenericModel;

@MappedSuperclass
public abstract class GymModel extends GenericModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * Enumerate the Gym roles for derived classes
     */
    public enum Role {
        Guest, Member, Trainer;
    }

    //--- helpers --

    /**
     * Find record by id for derived classes.
     * @param id primary key
     * @return GymModel record
     */
    protected static GymModel findById(Long id) {
        return find("id", id).first();
    }

    /**
     * String representation of class for derived classes;
     * @return String instance
     */
    @Override
    public String toString() {
        return "GymModel{'id': '" + id +
                "',} " + super.toString();
    }

    //---- constructors ----

    /**
     * Default constructor of instance
     */
    public GymModel() {
    }
}
