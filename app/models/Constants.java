
package models;

/**
 * Declare Gym Utility related common constants.
 **/

public interface Constants
{
    float MAX_WEIGHT = 250.0f;      /* 250 kgs */
    float MIN_WEIGHT = 35.0f;       /* 35 kgs */
    short MAX_SNAME_LENGTH = 20;
    short MAX_UNAME_LENGTH = 8;

    double SEVERELY_UNDERWEIGHT = 16.0f;
    double UNDERWEIGHT = 18.5f;
    double NORMAL = 25.0f;
    double OVERWEIGHT = 30.0f;
    double MODERATELY_OBESE = 35.0f;

    double DEVINE_IDEAL_MALE = 50.0f;
    double DEVINE_IDEAL_FEMALE = 45.5f;
    double DEVINE_INCR = 2.3f;

    float INCHES_IN_METER = 39.5f;
    float METERS_IN_5FOOT = 1.524f;

    /* from https://www.geeksforgeeks.org/check-email-address-valid-not-java  */
    String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
}
