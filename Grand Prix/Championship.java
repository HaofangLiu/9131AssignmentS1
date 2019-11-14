import java.util.ArrayList;

/**
 * Write a description of class Championship here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Championship
{
    private ListOfDrivers drivers;
    private ListOfVenues venues;

    /**
     * Constructor for objects of class Championship
     */
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
        gameContent();
        while (Input.continueGame())
        {
            venues.clearVenues();
            drivers.clearDrivers();
            gameContent();
        }
        System.out.println("Thank you for playing, hope to see you again soon!");
    }

    public void gameContent()
    {
        System.out.println("=+=+=+=+=+=+=+=+=+ Welcome to Grand Prix! =+=+=+=+=+=+=+=+=+");
        System.out.println("");
        int numberOfRaces = Input.enterNumberOfRaces();
        setDrivers();
        setVenues();
        racing(numberOfRaces);
    }

    public void racing(int numberOfRaces)
    {
        int[] usedVenues = new int[5];
        for(int i = 1; i <= numberOfRaces;i++)
        {
            System.out.println("Please choose the venue for this race, press enter to submit.");
            venues.displayVenues();
            int venueNo = Input.enterVenue(usedVenues);
            System.out.println("You chose venue " + venueNo + ".");
            System.out.println();
            usedVenues[i - 1] = venueNo;
            int laps = venues.getVenues().get(venueNo - 1).getNoOfLaps();
            racingInEachVenue(venueNo - 1,laps);
            drivers.clearTime();
        }
        drivers.calculateRacingResult();
        drivers.resetRanking();
        //write new ranking to the original file
        ArrayList<String> newDriverInfo = new ArrayList<>();
        for (int i = 0; i < drivers.getDrivers().size(); i++)
        {
            newDriverInfo.add(drivers.getDrivers().get(i).getName());
            newDriverInfo.add(String.valueOf(drivers.getDrivers().get(i).getRanking()));
            newDriverInfo.add(drivers.getDrivers().get(i).getSpecialSkill());
        }
        FileIO.writeToFile(newDriverInfo);
    }

    public void racingInEachVenue(int venueIndex,int laps)
    {
        System.out.println("====== Racing in venue " + venues.getVenues().get(venueIndex).getVenueName() + " ======");
        //HD+ chance of rain: the index list of drivers who has dry tyre
        ArrayList<Integer> dryTyreList = new ArrayList<>();
        int chanceOfRain = (int)(venues.getVenues().get(venueIndex).getChanceOfRain() * 100);
        RNG rainPossibility = new RNG(1,100);
        boolean raining = rainPossibility.calculateRain(chanceOfRain);
        if (raining)
            System.out.println("It is raining this venue.");
        for (int i = 1; i <= laps;i++)
        {
            System.out.println("+++++ Lap " + i + " +++++");
            for (int j = 0; j < drivers.getDrivers().size();j++)
            {
                if(drivers.getDrivers().get(j).getEligibleToRace())
                {
                    int ranking  = drivers.getDrivers().get(j).getRanking();
                    int timePenalty = calculateTimePenalty(ranking);
                    int averageLapTime = venues.getVenues().get(venueIndex).getAverageLapTime();
                    int totalTimeThisLap = averageLapTime + timePenalty;

                    //driver special skills
                    totalTimeThisLap = specialSkilling(totalTimeThisLap,j,i);

                    //HD+ chance of rain: change to wet tyre
                    if (i == 2)
                    {
                        RNG wetTyre = new RNG(0,1);
                        if (wetTyre.randomGenerator() == 1)
                            dryTyreList.add(j);// the list of DRY tyre
                        else
                        {
                            System.out.println(drivers.getDrivers().get(j).getName() + " changed to wet tyre.");
                            totalTimeThisLap += 10;
                        }
                    }

                    if (raining)
                    {
                        if (i == 1)
                            totalTimeThisLap = totalTimeThisLap + 5;
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
                    //occuring car problem
                    totalTimeThisLap = occurCarProblem(totalTimeThisLap,j);
                    //calculate the time of this lap
                    drivers.getDrivers().get(j).setAccumulatedTime(drivers.getDrivers().get(j).getAccumulatedTime() + totalTimeThisLap);
                }
            }

            //display leading driver
            drivers.displayLeadingDriver();
            Input.pressEnterContinue();
        }
        drivers.calculateRaceResult();
        Input.pressEnterContinue();
    }

    public int specialSkilling(int totalTimeThisLap,int driverIndex,int lapNo)
    {
        String specialSkill = drivers.getDrivers().get(driverIndex).getSpecialSkill();
        RNG specialSkillOneRNG;
        if (specialSkill.equals("Braking") || specialSkill.equals("Cornering"))
        {
            specialSkillOneRNG = new RNG(1,8);
            totalTimeThisLap = totalTimeThisLap - specialSkillOneRNG.randomGenerator();
        }
        if(specialSkill.equals("Overtaking") && lapNo % 3 == 0)
        {
            specialSkillOneRNG = new RNG(10,20);
            totalTimeThisLap = totalTimeThisLap - specialSkillOneRNG.randomGenerator();
        }
        return totalTimeThisLap;
    }

    public int occurCarProblem(int totalTimeThisLap,int driverIndex)
    {
        RNG fiveByHundred = new RNG(1,20);
        RNG threeAndOneByHundred = new RNG(1,100);
        String driverName = drivers.getDrivers().get(driverIndex).getName();
        //5%
        if (fiveByHundred.calculatePossibilityFiveAndOne())
        {
            System.out.println(driverName + " : Minor mechanical fault happens, 20 more seconds are spend in this lap.");
            totalTimeThisLap = totalTimeThisLap + 20;
        }
        //3%
        else if (threeAndOneByHundred.calculatePossibilityThree())
        {
            System.out.println(driverName + " : Major mechanical fault happens, 120 more seconds are spend in this lap.");
            totalTimeThisLap = totalTimeThisLap + 120;
        }
        //1%
        else if (threeAndOneByHundred.calculatePossibilityFiveAndOne())
        {
            System.out.println(driverName + " : Unrecoverable mechanical fault happens, the driver will exit the race.");
            drivers.getDrivers().get(driverIndex).setEligibleToRace(false);
        }
        return totalTimeThisLap;
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

    public void setDrivers()
    {
        ArrayList<String> driversInfo = FileIO.readFile("drivers.txt",3);
        drivers.initializeDriver(driversInfo);
        System.out.println("Drivers' information set.");
    }

    public void setVenues()
    {
        ArrayList<String> venuesInfo = FileIO.readFile("venues.txt",4);
        venues.initializeVenue(venuesInfo);
        System.out.println("Venues information set.");
    }

    public ListOfDrivers getDrivers() 
    {
        return drivers;
    }

    public void setDrivers(ListOfDrivers drivers)
    {
        this.drivers = drivers;
    }

    public ListOfVenues getVenues()
    {
        return venues;
    }

    public void setVenues(ListOfVenues venues)
    {
        this.venues = venues;
    }
}
