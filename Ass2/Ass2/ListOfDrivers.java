import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Write a description of class ListOfDrivers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ListOfDrivers
{
    // instance variables - replace the example below with your own
    private ArrayList<Driver> drivers;
    
    public ListOfDrivers()
    {
        drivers = new ArrayList<Driver>();
    }

    public ListOfDrivers(ArrayList<Driver> drivers)
    {
        this.drivers = drivers;
    }
    
    public ArrayList<Driver> getDrivers()
    {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers)
    {
        this.drivers = drivers;
    }
    
    public void clearTime()
    {
        for (Driver driver: drivers)
        {
            driver.setAccumulatedTime(0);
        }
    }
    
    public void clearDrivers()
    {
        drivers = new ArrayList<Driver>();
    }
    
    public void calculateRacingResult()
    {
        for (int i = 0;i < drivers.size() - 1; i++)
        {
            for (int j = i + 1; j < drivers.size(); j++)
            {
                if (drivers.get(i).getAccumulatedScore() < drivers.get(j).getAccumulatedScore())
                {
                    Collections.swap(drivers,i,j);
                }
                else if (drivers.get(i).getAccumulatedScore() == drivers.get(j).getAccumulatedScore())//decide the driver with same time randomly
                {
                    Random swapOrNot = new Random();
                    if (swapOrNot.nextBoolean())
                    {
                        Collections.swap(drivers,i,j);
                    }
                }
            }
        }
        //display the champion
        System.out.println("All races are finished!");
        for (int i = 0; i < drivers.size(); i++)
        {
            System.out.println((i + 1) +". " + drivers.get(i).getName() + ", score: " + drivers.get(i).getAccumulatedScore());
            //HD level. change ranking of drivers
            if (i < 4)
                drivers.get(i).setRanking(i + 1);
            else
                drivers.get(i).setRanking(5);
        }
        System.out.println("The winner is: " + drivers.get(0).getName() + ", total score: " + drivers.get(0).getAccumulatedScore());
    }
    
    public void initializeDriver(ArrayList<String> driversInfo)
    {
        for (int i = 0; i < driversInfo.size(); i = i + 3)
        {
            String name = driversInfo.get(i);
            int ranking = Integer.parseInt(driversInfo.get(i + 1));
            String specialSkill = driversInfo.get(i + 2);
            Driver currentDriver = new Driver(name,ranking,specialSkill,true,0,0);
            drivers.add(currentDriver);
        }
    }
    
    public void displayLeadingDriver()
    {
        int minTime = drivers.get(0).getAccumulatedTime();
        String minTimeDriver = drivers.get(0).getName();
        for (Driver currentDriver: drivers)
        {
            int currentTime = currentDriver.getAccumulatedTime();
            if(currentTime < minTime && currentDriver.getEligibleToRace())
            {
                minTime = currentTime;
                minTimeDriver = currentDriver.getName();
            }
        }
        System.out.println("The leading driver in this lap is : " + minTimeDriver + ", time : " + minTime);
    }

    public void calculateRaceResult()
    {
        for (int i = 0;i < drivers.size() - 1; i++)
        {
            for (int j = i + 1; j < drivers.size(); j++)
            {
                if (drivers.get(i).getAccumulatedTime() > drivers.get(j).getAccumulatedTime())
                {
                    Collections.swap(drivers,i,j);
                }
                else if (drivers.get(i).getAccumulatedTime() == drivers.get(j).getAccumulatedTime())//decide the driver with same time randomly
                {
                    Random swapOrNot = new Random();
                    if (swapOrNot.nextBoolean())
                    {
                        Collections.swap(drivers,i,j);
                    }
                }
            }
        }
        Driver outDriver = new Driver();
        for (int i = 0; i < drivers.size();i++)
        {
            if (!drivers.get(i).getEligibleToRace())
            {
                outDriver = drivers.get(i);
                outDriver.setAccumulatedScore(0);
                drivers.remove(i);
                drivers.add(outDriver);
            }
        }
        //display the result and award point
        System.out.println("The result of this race is:");
        for (int i = 0; i < 4; i++)
        {
            System.out.println((i + 1) +". " + drivers.get(i).getName() + ", time: " + drivers.get(i).getAccumulatedTime() + ", Current score: " + awardScore(i));
        }
    }
    
    public int awardScore(int driverIndex)
    {
        int previousScore = drivers.get(driverIndex).getAccumulatedScore();
        int afterScore = 0;
        switch (driverIndex)
        {
            case 0: afterScore = previousScore + 8;break;
            case 1: afterScore = previousScore + 5;break;
            case 2: afterScore = previousScore + 3;break;
            case 3: afterScore = previousScore + 1;break;
            default: System.out.println("Index error");
        }
        drivers.get(driverIndex).setAccumulatedScore(afterScore);
        return afterScore;
    }
}
