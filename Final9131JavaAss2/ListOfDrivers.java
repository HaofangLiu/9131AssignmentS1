import java.util.ArrayList;
import java.util.*;
/**
 * Write a description of class ListOfDrivers here.
 *
 * @author (Yi Yu)
 * @version (2019.05.28)
 */
public class ListOfDrivers
{
    // instance variables - replace the example below with your own
    private ArrayList<Driver> drivers;

    /**
     * Constructor for objects of class ListOfDrivers
     */
    public ListOfDrivers()
    {
        // initialise instance variables
        drivers = new ArrayList<>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public ListOfDrivers(ArrayList<Driver> drivers)
    {
        // put your code here
        this.drivers = drivers;
    }

    public ArrayList<Driver> getDrivers()
    {
        return drivers;
    }

    public void setdrivers(ArrayList<Driver> drivers)
    {
        this.drivers = drivers;
    }

    public void addDrivers(String name, String ranking, String specialSkills, boolean eligibleToRace, int accumulatedScore, int accumulatedTime)
    {
        int rankingN = Integer.parseInt(ranking);
        Driver d = new Driver(name, rankingN, specialSkills, eligibleToRace, accumulatedScore, accumulatedTime); 
        drivers.add(d);
    }

    public void awardDrivers()
    {
        ArrayList<Integer>accTimes = new ArrayList<>();
        for(Driver d : drivers)
        {
            if(d.getEligibleToRace())
            {
                accTimes.add(d.getAccumulatedTime()); 
            }
        }

    }

    public void theFastestDriver()
    {
        int lowestTime = drivers.get(0).getAccumulatedTime();// = drivers.get(0).getAccumulatedTime();
        String fastestDriverName = drivers.get(0).getName();;
        for(Driver everySingleDriver : drivers)
        {
            int driverNowTime = everySingleDriver.getAccumulatedTime();
            if(driverNowTime < lowestTime && everySingleDriver.getEligibleToRace())
            {
                lowestTime = driverNowTime;
                fastestDriverName = everySingleDriver.getName();
            }
        }
        System.out.println("The fastest is " + fastestDriverName + " , the time is " +  lowestTime + " .");
    }

    public void countTime()
    {
        for(int i = 0;i < drivers.size() - 1; i++)
        {
            for(int j = i + 1;j < drivers.size(); j++)
            {
                if (drivers.get(i).getAccumulatedTime() > drivers.get(j).getAccumulatedTime())
                {
                    Collections.swap(drivers,i,j);
                }
                else if (drivers.get(i).getAccumulatedTime() == drivers.get(j).getAccumulatedTime())
                {
                    Random positionChange = new Random();
                    if(positionChange.nextBoolean())
                    {
                        Collections.swap(drivers,i,j);
                    }
                }
            }
        }
        Driver outDriver = new Driver();
        for (int i = 0; i < drivers.size();i++)
        {
            if(!drivers.get(i).getEligibleToRace())
            {
                outDriver = drivers.get(i);
                outDriver.setAccumulatedScore(0);
                drivers.remove(i);
                drivers.add(outDriver);
            }
        }
        for(int i = 0;i < drivers.size();i++)
        {
            placePoints(i + 1);
        }
        System.out.println("The result of this race is:");
        for (int i = 0; i < drivers.size();i++)
        {
            System.out.println((i + 1) +","+ drivers.get(i).getName() +",time : " + drivers.get(i).getAccumulatedTime());
        }
    }

    public void countPoint()
    {
        for(int i = 0;i < drivers.size() - 1; i++)
        {
            for(int j = i + 1;j < drivers.size(); j++)
            {
                if (drivers.get(i).getAccumulatedScore() < drivers.get(j).getAccumulatedScore())
                {
                    Collections.swap(drivers,i,j);
                }
                else if (drivers.get(i).getAccumulatedScore() == drivers.get(j).getAccumulatedScore())
                {
                    Random positionChange = new Random();
                    if(positionChange.nextBoolean())
                    {
                        Collections.swap(drivers,i,j);
                    }
                }
            }
        }
        System.out.println("Races finished");
        
        for (int i = 0; i < drivers.size();i++)
        {
            System.out.println((i + 1) +","+ drivers.get(i).getName() +",score : " + drivers.get(i).getAccumulatedScore());
        }

    }   

    public void placePoints(int ranking)
    {
        switch(ranking)
        {
            case 1:drivers.get(ranking - 1).setAccumulatedScore(drivers.get(ranking - 1).getAccumulatedScore() + 8);break;
            case 2:drivers.get(ranking - 1).setAccumulatedScore(drivers.get(ranking - 1).getAccumulatedScore() + 5);break;
            case 3:drivers.get(ranking - 1).setAccumulatedScore(drivers.get(ranking - 1).getAccumulatedScore() + 3);break;
            case 4:drivers.get(ranking - 1).setAccumulatedScore(drivers.get(ranking - 1).getAccumulatedScore() + 1);break;
            default: drivers.get(ranking - 1).setAccumulatedScore(drivers.get(ranking - 1).getAccumulatedScore() + 0);break;
        }
    }

    public void allocatePosition()
    {
        for(Driver d : drivers)
        {
            //d.timePenalty();
        }
    }

    public void displayDrivers()
    {
        for(Driver d : drivers)
        {
            d.displayDriverInfo();
        }
    }

    public void displayChanpion()
    {
        for(Driver d :drivers)
        {
            if(d.getRanking() == 1)
            {
                System.out.println("*******This is the chanpion********");
                d.displayDriverInfo();
            }
        }
    }

    public void deleteDrivers()
    {
        drivers = new ArrayList<Driver>();
    }

    public void reNewDriver()
    {
        for(Driver eachDriver : drivers)
        {
            eachDriver.setAccumulatedTime(0);
        }
    }
}

