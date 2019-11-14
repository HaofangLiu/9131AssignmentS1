import java.util.Scanner;
import java.util.*;
/**
 * Write a description of class Championship here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Championship
{
    // instance variables - replace the example below with your own
    private ListOfDrivers drivers;
    private ListOfVenues venues;

    public Championship()
    {
        drivers = new ListOfDrivers();
        venues = new ListOfVenues();
    }

    public Championship(ListOfDrivers drivers,ListOfVenues venue)
    {
        this.drivers = drivers;
        this.venues = venues;
    }

    public void startGame()
    {
        boolean gameContinue = true;
        while(gameContinue)
        {
            System.out.println("=+=+=+=+=+=+=+=+=+ Welcome to Grand Prix! =+=+=+=+=+=+=+=+=+");
            System.out.println("");
            System.out.println("input number of games");
            int numberRace = enterNumberOfGame();
            setDrivers();
            setVenues();
            race(numberRace);
            Scanner scanner = new Scanner(System.in);
            System.out.println("press N to stop game, otherwise press any key to play another championship");
            if(scanner.nextLine().equalsIgnoreCase("n"))
            {
                gameContinue = false;
            }
            else
            {
                venues.clearVenues();
                drivers.clearDrivers();
            }
        }   
        System.exit(0);
    }

    public void race(int numberRace)
    {
        ArrayList<Integer> racedVenue = new ArrayList<Integer>();
        for(int i = 0; i < numberRace; i++)
        {
            System.out.println("Please choose the venue for this race, press enter to submit.");
            venues.displayVenues();
            Scanner scanner = new Scanner(System.in);
            String venueSelect = scanner.nextLine();
            while(!Validation.venueSelect(venueSelect,racedVenue))
            {
                System.out.println("enter again");
                venueSelect = scanner.nextLine();
            }
            int venueSelectInt = Integer.parseInt(venueSelect);
            System.out.println("You chose venue " + venueSelect + ".");
            System.out.println();
            racedVenue.add(venueSelectInt);
            racingInEachVenue(venueSelectInt - 1);//arraylist
            drivers.clearTime();
        }
        drivers.calculateRacingResult();
        ArrayList<String> newDriverInfo = new ArrayList<>();
        for (int i = 0; i < drivers.getDrivers().size(); i++)
        {
            newDriverInfo.add(drivers.getDrivers().get(i).getName());
            newDriverInfo.add(String.valueOf(drivers.getDrivers().get(i).getRanking()));
            newDriverInfo.add(drivers.getDrivers().get(i).getSpecialSkill());
        }
        try{
            FileIO.writeToFile(newDriverInfo);
        }
        catch(Exception w)
        {
            
        }

    }

    public void racingInEachVenue(int venueIndex)
    {
        ArrayList<Integer> dryTyreList = new ArrayList<>();
        int laps = venues.getVenues().get(venueIndex).getNoOfLaps();
        System.out.println("====== Racing in venue " + venues.getVenues().get(venueIndex).getVenueName() + " ======");
        int chanceOfRain = (int)(venues.getVenues().get(venueIndex).getChanceOfRain() * 100);
        RNG rainPossibility = new RNG(1,100);
        boolean isRain = rainPossibility.calculateRain(chanceOfRain);
        if (isRain)
        {
            System.out.println("It is raining this venue.");
        }
        for(int i = 0; i < laps; i++)
        {
            int currentLap = i + 1;
            System.out.println("+++++ Lap " + currentLap + " +++++");
            for (int j = 0; j < drivers.getDrivers().size();j++)
            {
                if(drivers.getDrivers().get(j).getEligibleToRace())
                {
                    int ranking  = drivers.getDrivers().get(j).getRanking();
                    int timePenalty = calculateTimePenalty(ranking);
                    int averageLapTime = venues.getVenues().get(venueIndex).getAverageLapTime();
                    int totalTimeThisLap = averageLapTime + timePenalty;
                    //special skill
                    String specialSkill = drivers.getDrivers().get(j).getSpecialSkill();
                    if (specialSkill.equals("Braking") || specialSkill.equals("Cornering"))
                    {
                        RNG specialSkillOneRNG = new RNG(1,8);
                        totalTimeThisLap = totalTimeThisLap - specialSkillOneRNG.generateRandom();
                    }
                    if(specialSkill.equals("Overtaking") && currentLap % 3 == 0)
                    {
                        RNG specialSkillOneRNG = new RNG(10,20);
                        totalTimeThisLap = totalTimeThisLap - specialSkillOneRNG.generateRandom();
                    }
                    //change dry tyer
                    if (currentLap == 2)
                    {
                        RNG wetTyre = new RNG(0,1);
                        if (wetTyre.generateRandom() == 1)
                        {
                            dryTyreList.add(j);// the list of DRY tyre
                        }
                        else
                        {
                            System.out.println(drivers.getDrivers().get(j).getName() + " changed to wet tyre.");
                            totalTimeThisLap += 10;
                        }
                    }
                    //add time for dry tyer
                    if(isRain)
                    {
                        if(currentLap == 1)
                        {
                            totalTimeThisLap += 5;
                        }
                        else
                        {
                            for (int dryTyreIndex: dryTyreList)
                            {
                                if (dryTyreIndex == j)
                                {
                                    totalTimeThisLap = totalTimeThisLap + 5;
                                }
                            }
                        }
                    }
                    RNG chanceRate = new RNG(1,100);
                    int chance = chanceRate.generateRandom();
                    String driverName = drivers.getDrivers().get(j).getName();
                    if(chance == 1)
                    {
                        System.out.println(driverName + " : Unrecoverable mechanical fault happens, the driver will exit the race.");
                        drivers.getDrivers().get(j).setEligibleToRace(false);
                    }
                    else if(chance == 3)
                    {
                        System.out.println(driverName + " : Major mechanical fault happens, 120 more seconds are spend in this lap.");
                        totalTimeThisLap = totalTimeThisLap + 120;
                    }
                    else if(chance == 5)
                    {
                        System.out.println(driverName + " : Minor mechanical fault happens, 20 more seconds are spend in this lap.");
                        totalTimeThisLap = totalTimeThisLap + 20;
                    }
                    drivers.getDrivers().get(j).setAccumulatedTime(drivers.getDrivers().get(j).getAccumulatedTime() + totalTimeThisLap);

                }
            }
            drivers.displayLeadingDriver();
        }
        drivers.calculateRaceResult();
        Scanner console = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        console.nextLine();
    }

    public int calculateTimePenalty(int ranking)
    {
        int timePenalty = 0;
        switch (ranking)
        {
            case 1: timePenalty = 0;break;
            case 2: timePenalty = 3;break;
            case 3: timePenalty = 5;break;
            case 4: timePenalty = 7;break;
            default: timePenalty = 10;break;
        }
        return timePenalty;
    }

    public int enterNumberOfGame()
    {   
        Scanner scanner = new Scanner(System.in);
        String raceNo = scanner.nextLine();
        while(!Validation.onlyNumber(raceNo))
        {        
            System.out.println("enter again");
            //raceNo = String.valueOf(enterNumberOfGame());
            raceNo = scanner.nextLine();
        }
        return Integer.parseInt(raceNo);
    }

    public void setVenues()
    {
        ArrayList<String> venuesInfo = FileIO.readFile("venues.txt",4);
        venues.initializeVenue(venuesInfo);
        System.out.println("Venues information set.");
    }

    public void setDrivers()
    {
        ArrayList<String> driversInfo = FileIO.readFile("drivers.txt",3);
        drivers.initializeDriver(driversInfo);
        System.out.println("Drivers' information set.");
    }

}
