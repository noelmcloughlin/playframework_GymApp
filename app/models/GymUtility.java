package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class GymUtility extends GymApp
{

    /**
     * Return the BMI for member based on calculation of: weight divided by the square of the height.
     *
     * @param person    Members personal id
     * @return double
     */
    public static double calculateBMI(Person person, Assessment assessment)
    {
        // BMI is weight divided by the square of the height.
        return (assessment.getWeight() / Math.pow(person.getHeight(), 2)) - 0.04f;
    }


    /**
     * Return the category the BMI belongs to:
     * - SEVERELY UNDERWEIGHT, UNDERWEIGHT, NORMAL, OVERWEIGHT, MODERATELY OBESE, SEVERELY OBESE
     *
     * @param bmiValue BMI value id
     * @return BMI determination
     */
    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < Constants.SEVERELY_UNDERWEIGHT)
            return "SEVERELY UNDERWEIGHT";
        if (bmiValue < Constants.UNDERWEIGHT)
            return "UNDERWEIGHT";
        if (bmiValue < Constants.NORMAL)
            return "NORMAL";
        if (bmiValue < Constants.OVERWEIGHT)
            return "OVERWEIGHT";
        if (bmiValue < Constants.MODERATELY_OBESE)
            return "MODERATELY OBESE";
        else
            return "SEVERELY OBESE";
    }


    /**
     * Return ideal body weight, according to Devine, given gender & height.
     * <p>
     * Devine idealizes that ..
     * For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
     * If member is 5 feet or less, assume 50kg for male and 45.5kg for female.
     *
     * @param gender Male of Female only
     * @param height height in metres
     * @return Ideal body weight in kgs
     */
    public static float idealBodyWeight(String gender, double height) {
        // Calculate number of inches the users height exceeds five feet.
        double i = Math.max(0.0f, (height - Constants.METERS_IN_5FOOT) * Constants.INCHES_IN_METER);

        // Calculate & return the weight idealized by Devine.
        switch (gender.toUpperCase().charAt(0)) {
            case 'M':
                return Math.round(Constants.DEVINE_IDEAL_MALE + (i * Constants.DEVINE_INCR));
            default:
                return Math.round(Constants.DEVINE_IDEAL_FEMALE + (i * Constants.DEVINE_INCR));
        }
    }

    /**
     * a boolean to indicate if the member has an ideal body weight based on the "Devine" formula.
     *
     * @param m          member
     * @param assessment member assessment
     * @return true or false
     */
    public static boolean isIdealBodyWeight(Member m, Assessment assessment)
    {
        Person p = Person.findById(m.person_id);
        return Math.round(assessment.getWeight()) <= idealBodyWeight(p.getGender().toString(), p.getHeight());
    }


    /**
     * Is string a valid email?
     *
     * @param email Email to be tested against an email regex
     * @return true or false
     */
    public static boolean isValidEmail(String email)
    {
        if (email == null)
            return false;
        return Pattern.compile(Constants.EMAIL_REGEX).matcher(email).matches();
    }

    /**
     * Given a date, return a formatted string
     * @param date Date
     * @param dateformat dateformat
     * @return String dateformat
     */
    public static String showDate(Date date, String dateformat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateformat);
        return formatter.format(date);
    }

    /**
     * Given a date, return default format (dd-MMM-yyyy HH:mm)
     * @param date Date
     * @return String
     */
    public static String showDate(Date date)
    {
        return showDate(date, "dd-MMM-yyyy H:mm");
    }

}
