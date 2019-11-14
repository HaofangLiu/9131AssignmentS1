
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
    private double chanceOfRain;

    /**
     * Constructor for objects of class Venue
     */
    public Venue()
    {
        // initialise instance variables
        venueName = "";
        noOfLaps = 0;
        averageLapTime = 0;
        chanceOfRain = 0;
    }
    
    public Venue(String venueName,int noOfLaps,int averageLapTime,double chanceOfRain)
    {
        this.venueName = venueName;
        this.noOfLaps = noOfLaps;
        this.averageLapTime = averageLapTime;
        this.chanceOfRain = chanceOfRain;
    }
    
    public String getVenueName()
    {
        return venueName;
    }
    
    public void setVenueName(String venueName)
    {
        this.venueName = venueName;
    }
    
    public int getNoOfLaps()
    {
        return noOfLaps;
    }

    public void setVoOfLaps(int noOfLaps)
    {
        this.noOfLaps = noOfLaps;
    }
    
    public int getAverageLapTime()
    {
        return averageLapTime;
    }
    
    public void setAverageLapTime(int averageLapTime)
    {
        this.averageLapTime = averageLapTime;
    }
    
    public double getChanceOfRain()
    {
        return chanceOfRain;
    }
    
    public void setChanceOfRain(double chanceOfRain)
    {
        this.chanceOfRain = chanceOfRain;
    }
}
