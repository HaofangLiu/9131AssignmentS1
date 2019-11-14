import java.util.ArrayList;
/**
 * Write a description of class ListOfVenues here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ListOfVenues
{
    private ArrayList<Venue> venues;

    /**
     * Constructor for objects of class ListOfVenues
     */
    public ListOfVenues()
    {
        venues = new ArrayList<Venue>();
    }
    
    public ListOfVenues(ArrayList<Venue> venues)
    {
        this.venues = venues;
    }

    public void initializeVenue(ArrayList<String> venuesInfo)
    {
        int venueIndex = 0;
        for (int i = 0; i < venuesInfo.size(); i = i + 4)
        {
            String venueName = venuesInfo.get(i);
            int noOfLaps = Integer.parseInt(venuesInfo.get(i + 1));
            int averageLapTime = Integer.parseInt(venuesInfo.get(i + 2));
            double chanceOfRain = Double.parseDouble(venuesInfo.get(i + 3));
            Venue currentVenue = new Venue(venueName,noOfLaps,averageLapTime,chanceOfRain);
            venues.add(currentVenue);
        }
    }
    
    public void displayVenues()
    {
        int index = 1;
        for (Venue currentVenue : venues)
        {
            System.out.println(index + ". " + currentVenue.getVenueName());
            index ++;
        }
    }
    
    public void clearVenues()
    {
        venues = new ArrayList<Venue>();
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ArrayList<Venue> getVenues()
    {
        return venues;
    }
    
    public void setVenues(ArrayList<Venue> venues)
    {
        this.venues = venues;
    }
}
