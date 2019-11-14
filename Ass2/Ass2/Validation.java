import java.util.Scanner;
import java.util.*;
/**
 * Write a description of class Validation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Validation
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
        // initialise instance variables
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static boolean onlyNumber(String userInpu)
    {
        try
        {
            int userInputNumberOfRace = Integer.parseInt(userInpu);
            if (userInputNumberOfRace < 3 || userInputNumberOfRace > 5)
            {
                System.out.println("Races should between 3 and 5. Please renter.");
                return false;
            }
            else
            {
                return true;
            }
        }   
        catch(Exception e)
        {
            System.out.println("Races should be an integer. Please renter.");
            return false;
        }
    }
    
    public static boolean venueSelect(String venueSelect,ArrayList<Integer> racedVenue)
    {
        try
        {
            int selectedVenue = Integer.parseInt(venueSelect);
            if (selectedVenue < 1 || selectedVenue > 8)
            {
                System.out.println("Races should between 3 and 8. Please renter.");
                return false;
            }
            for(Integer a : racedVenue)
            {
                if(a == selectedVenue)
                {
                    System.out.println("this has been selected.");
                    return false;
                }
            }
            return true;
        }   
        catch(Exception e)
        {
            System.out.println("only integer. Please renter.");
            return false;
        }
    }
}
