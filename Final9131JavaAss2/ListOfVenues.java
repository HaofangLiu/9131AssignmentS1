import java.util.ArrayList;
/**
 * Write a description of class ListOfVenues here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ListOfVenues
{
    // instance variables - replace the example below with your own
    private ArrayList<Venue> venues;

    /**
     * Constructor for objects of class ListOfVenues
     */
    public ListOfVenues()
    {
        // initialise instance variables
        venues = new ArrayList<>(); 
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ListOfVenues(ArrayList<Venue> venues)
    {
        // put your code here
        this.venues = venues;
    }
    
    public ArrayList<Venue> getVenues()
    {
        return venues;
    }
    
    public void setVenues(ArrayList<Venue> venues)
    {
        this.venues = venues;
    }
      //set the arraylist
    public void addVenues(String venuename,String noOfLaps,String averageTime,String chanceOfRain)
    {
       int noOfLapsN = Integer.parseInt(noOfLaps);
       int averageTimeN = Integer.parseInt(averageTime);
       double chanceOfRainN = Double.parseDouble(chanceOfRain);
       Venue v = new Venue(venuename,noOfLapsN,averageTimeN,chanceOfRainN);
       venues.add(v);
    }
    
    public void deleteVenues()
    {
        venues = new ArrayList<Venue>();
    }
    
    public void displayVenues()
    {
        int i = 1;
        for(Venue v: venues){
            System.out.println(i + ". The" + v.getVenueName() + "welcome us." + "The racers are going to complete for the title over"+ v.getNoOfLaps() + "Laps." + "With an average lap time of " + v.getAverageLapTime() + ".");
            i++;
        }
        System.out.println();
    }
}
