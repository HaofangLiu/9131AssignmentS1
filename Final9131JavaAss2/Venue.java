
/**
 * Write a description of class Venue here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Venue
{
    // instance variables - replace the example below with your own
    private String venueName;
    private int noOfLaps;
    private int averageLapTime;
    private double chanOfRain;

    /**
     * Constructor for objects of class Venue
     */
    public Venue()
    {
        // initialise instance variables
        venueName = "";
        noOfLaps = 0;
        averageLapTime = 0;
        chanOfRain = 0.0;
    }
    

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Venue(String venueName, int noOfLaps, int averageLapTime, double chanOfRain) 
    {
        // put your code here
        this.venueName = venueName;
        this.noOfLaps = noOfLaps;
        this.averageLapTime = averageLapTime;
        this.chanOfRain = chanOfRain;
    }
    
    public String getVenueName()
    {
        return venueName;
    }
    
    public int getNoOfLaps()
    {
        return noOfLaps;
    }
    
    public int getAverageLapTime()
    {
        return averageLapTime;
    }
    
    public double getchanOfRain()
    {
        return chanOfRain;
    }
    
    public void setvenueName(String venueName)
    {
        this.venueName = venueName;
    }
    
    public void setnoOfLaps(int noOfLaps)
    {
        this.noOfLaps = noOfLaps;
    }
    
    public void setaverageLapTime(int averageLapTime)
    {
        this.averageLapTime = averageLapTime;
    }
    
    public void setchanOfRain(double chanOfRain)
    {
        this.chanOfRain = chanOfRain;
    }
    
    public void displayVenueInfo()
    {
        System.out.println("The" + venueName + "welcome us." + "The racers are going to complete for the title over"+ noOfLaps + "Laps." + "With an average lap time of " + averageLapTime + ".");
    }
}
