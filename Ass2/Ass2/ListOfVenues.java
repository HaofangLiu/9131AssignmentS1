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

    public ListOfVenues()
    {
        venues = new ArrayList<Venue>();
    }

    public ListOfVenues(ArrayList<Venue> venues)
    {
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
    
    public void clearVenues()
    {
        venues = new ArrayList<Venue>();
    }

    public void initializeVenue(ArrayList<String> venuesInfo)
    {
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
}
