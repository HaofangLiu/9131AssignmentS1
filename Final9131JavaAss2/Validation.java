import java.util.ArrayList;
import java.util.*;
/**
 * Write a description of class Validation here.
 *
 * @author (Yi Yu)
 * @version (2019.0)
 */
public class Validation
{
    // instance variables - replace the example below with your own
    private String userInput;

    /**
     * Constructor for objects of class Validation
     */
    public Validation()
    {
        // initialise instance variables
        userInput = "";
    }

    public Validation(String userInput)
    {
        this.userInput = userInput;
    }

    public String getUserInput()
    {
        return userInput;
    }

    public void setUserInput(String userInput)
    {
        this.userInput = userInput;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public boolean rightInputRace()
    {
        // put your code here
        try
        {
            int userInputRaceNum = Integer.parseInt(userInput);
            if(userInputRaceNum > 2 && userInputRaceNum < 6)
            {
                return true;
            }   
            else
            {
                System.out.println("Please enter the number betweeen 3 and 5 !");
                return false;
            }

        }
        catch(Exception e)
        {
            System.out.println("Please enter the right character !");
            return false;
        }

    }

    public boolean rightInputVenue(String userInput, ArrayList<Integer> racedVenue)
    {
        try
        {
            int userInputVenueNum = Integer.parseInt(userInput);
            if(userInputVenueNum > 0 && userInputVenueNum < 9)
            {
                for(Integer racedA : racedVenue)
                {
                    if(racedA == userInputVenueNum)
                    {
                        System.out.println("Please enter different value !");
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                return true;
            }
            else
            {
                System.out.println("Please enter the number betweeen 1 and 8 !");
                return false;
            }
            //return null;
        }
        catch(Exception e)
        {
            System.out.println("Please enter the right character !");
            return false;
        }
    }
}
