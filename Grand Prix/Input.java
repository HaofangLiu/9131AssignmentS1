import java.util.Scanner;
/**
 * Write a description of class Input here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Input
{

    /**
     * Constructor for objects of class Input
     */
    public Input()
    {
        
    }

    public static void pressEnterContinue()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        console.nextLine();
    }
    
    public static boolean continueGame()
    {
        Scanner console = new Scanner(System.in);
        System.out.println("Do you want to cotinue a new championship game? Input 'y' to continue, or input anything else to quit.");
        System.out.println("Press enter to confirm after input.");
        String choice = console.nextLine();
        if (choice.equalsIgnoreCase("y"))
            return true;
        else
            return false;
    }
    
    /**
     * Accept the input of Races
     */
    public static int enterNumberOfRaces()
    {
        Scanner console = new Scanner(System.in);
        try
        {
            System.out.println("Please enter number of races (3 - 5 inclusive):");
            int raceNo = Integer.parseInt(console.nextLine());
            if (raceNo < 3 || raceNo > 5)
            {
                System.out.println("Races should between 3 and 5. Please renter.");
                raceNo = enterNumberOfRaces();
            }
            return raceNo;
        }
        catch(Exception e)
        {
            System.out.println("Races should be an integer. Please renter.");
            return enterNumberOfRaces();
        }
    }
    
    public static int enterVenue(int[] usedVenues)
    {
        Scanner console = new Scanner(System.in);
        try
        {
            System.out.println("Venue number:");
            int venueNo = Integer.parseInt(console.nextLine());
            if (venueNo < 1 || venueNo > 8)
            {
                System.out.println("Venue number should between 1 and 8. Please renter.");
                venueNo = enterVenue(usedVenues);
            }
            for (int venueNumber: usedVenues)
            {
                if(venueNo == venueNumber)
                {
                    System.out.println("Venue number has been used! Please renter.");
                    venueNo = enterVenue(usedVenues);
                }
            }
            return venueNo;
        }
        catch(Exception e)
        {
            System.out.println("Venue number should be an integer. Please renter.");
            return enterVenue(usedVenues);
        }
    }
}
