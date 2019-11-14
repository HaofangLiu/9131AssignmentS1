
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

    /**
     * Constructor for objects of class Championship
     */
    public Championship()
    {
        // initialise instance variables
        drivers = new ListOfDrivers();
        venues = new ListOfVenues();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Championship(ListOfDrivers drivers, ListOfVenues venues)
    {
        // put your code here
        this.drivers = drivers;
        this.venues = venues;
    }

    public ListOfDrivers getDriver()
    {
        return drivers;
    }

    public ListOfVenues getVenues()
    {
        return venues;
    }

    public void setDriver(ListOfDrivers drivers)
    {
        this.drivers = drivers;
    }

    public void setVenues(ListOfVenues venues)
    {
        this.venues= venues;
    }

    public void startGame()
    {
        boolean gameStart = true;
        while(gameStart)
        {
            System.out.println("****************************************************************************************");
            System.out.println("****************************Welcome to FIT9131 Grand Prix !******************************");
            System.out.println("****************************************************************************************");
            System.out.println("Please input the number of Race");
            int raceNum = selectRaceNum();
            loadDrivers();
            loadVenues();
            race(raceNum);
            
            Scanner checkStart = new Scanner(System.in);
            System.out.println("If you want to quit the game, you can press the Q. If you want to start a new championship you can use other press !");
            if(checkStart.nextLine().equalsIgnoreCase("n"))
            {
                gameStart = false;
            }
            else
            {
                venues.deleteVenues();
                drivers.deleteDrivers();
            }
            System.exit(0);
        }
    }

    public void race(int raceNum)
    {
        ArrayList<Integer> finishedVenue = new ArrayList<Integer>();
        int i = 0;
        while(i < raceNum)
        {
            //i++;
            System.out.println("Please chooose the Venue and enter the number");
            venues.displayVenues();
            //Validation v = new Validation();
            Scanner scanner = new Scanner(System.in);
            String venueChoice = scanner.nextLine();
            Validation v = new Validation(venueChoice);
            while(!v.rightInputVenue(venueChoice,finishedVenue))
            {
                System.out.println("Please check what you entered and enter it again !");
                venueChoice = scanner.nextLine();
            }
            int venueChoiceInt = Integer.parseInt(venueChoice);
            System.out.println(" You chose venue " +venueChoice + "." );
            finishedVenue.add(venueChoiceInt);
            //racingInEachVenue(venueChoiceInt - 1);
            everyVenue(venueChoiceInt - 1);
            drivers.reNewDriver();
            i++;
        }
        drivers.countPoint();
        ArrayList<String> newDriverInfo = new ArrayList<>();
        int j = 0;
        while (j < drivers.getDrivers().size())
        {
            //i++;
            newDriverInfo.add(drivers.getDrivers().get(j).getName());
            newDriverInfo.add(String.valueOf(drivers.getDrivers().get(j).getRanking()));
            newDriverInfo.add(drivers.getDrivers().get(j).getSpecialSkill());
            j++;
        }
        FileIO fileIO = new FileIO();
        fileIO.writeFile(newDriverInfo);
    }

    public void everyVenue(int venueChoiceInt)
    {
        System.out.println("Racing in venue " + venues.getVenues().get(venueChoiceInt).getVenueName() + ".");
        int numberOfLaps = venues.getVenues().get(venueChoiceInt).getNoOfLaps();
        int chanceOfRain = (int)(venues.getVenues().get(venueChoiceInt).getchanOfRain() * 100);
        RNG rng = new RNG(1,100);
        boolean raining = rng.calculateRain(chanceOfRain);
        if(raining)
        {
            System.out.println("it's raining!");
        }
        raceEveryLap(numberOfLaps,raining,venueChoiceInt);
        
    }

    public void raceEveryLap(int numberOfLaps, boolean raining,int venueChoiceInt)
    {
        int i = 1;
        ArrayList<Integer> changeTyre = new ArrayList<Integer>();
        while (i < numberOfLaps + 1)
        {
            System.out.println("****************Lap " + i + "****************");
            for (int driverNum = 0; driverNum < drivers.getDrivers().size(); driverNum++)
            {
                if(drivers.getDrivers().get(driverNum).getEligibleToRace())
                {
                    int driverRanking = drivers.getDrivers().get(driverNum).getRanking();
                    int averageLapTime = venues.getVenues().get(venueChoiceInt).getAverageLapTime();
                    int totalTimeThisLap = averageLapTime + timePenalty(driverRanking);
                    boolean everyThirdLap = false;
                    if(i % 3 == 0)
                    {
                        everyThirdLap = true;
                    }
                    totalTimeThisLap = useSkill(everyThirdLap,driverNum,totalTimeThisLap);
                    totalTimeThisLap = chanceEvent(driverNum,totalTimeThisLap);
                    if(i == 2)
                    {
                        RNG rng = new RNG(0,1);
                        int chanceOfChangeTyre = rng.generateRandomNo();
                        if(chanceOfChangeTyre == 1)
                        {
                            changeTyre.add(driverNum);
                        }
                        else
                        {
                            System.out.println(drivers.getDrivers().get(driverNum).getName() + " changed to wet tyre.");
                            totalTimeThisLap += 10;
                        }
                    }
                    if(raining)
                    {
                        for(Integer unChangedTyre : changeTyre)
                        {
                            if(unChangedTyre == driverNum)
                            {
                                totalTimeThisLap += 5;
                            }
                        }
                    }
                    drivers.getDrivers().get(driverNum).setAccumulatedTime(drivers.getDrivers().get(driverNum).getAccumulatedTime() + totalTimeThisLap);
                }
            }
            drivers.theFastestDriver();
            i++;
        }
        drivers.countTime();
    }

    public int chanceEvent(int driverIndex,int totalTimeThisLap)
    {
        RNG rng = new RNG(1,100);
        int randomChanceRate = rng.generateRandomNo();
        int randomResult = rng.randomEvent(randomChanceRate);
        String driverName = drivers.getDrivers().get(driverIndex).getName();
        if(randomResult == 1)
        {
            System.out.println("************" + driverName + " : Unrecoverable mechanical fault happens ************");
            drivers.getDrivers().get(driverIndex).setEligibleToRace(false);
        }
        else if(randomResult == 3)
        {
            System.out.println("************" + driverName + " : Major mechanical fault happens************");
            totalTimeThisLap = totalTimeThisLap + 120;
        }
        else if(randomResult == 5)
        {
            System.out.println("************" + driverName + " : Minor mechanical fault happens************");
            totalTimeThisLap = totalTimeThisLap + 20;
        }
        return totalTimeThisLap;
    }

    public int timePenalty(int ranking)
    {
        int timePenaltyNum;
        switch(ranking)
        {
            case 1:timePenaltyNum = 0;break;
            case 2:timePenaltyNum = 3;break;
            case 3:timePenaltyNum = 5;break;
            case 4:timePenaltyNum = 7;break;
            //case 5:setAccumulatedTime(getAccumulatedTime() + 10);break;
            default: timePenaltyNum = 10;break;
        }
        return timePenaltyNum;
    }

    public int selectRaceNum()
    {

        Scanner parser = new Scanner(System.in);//parser difference with  name of scanner
        String  raceNum = parser.nextLine();
        Validation v = new Validation(raceNum);
        if(!v.rightInputRace())
        {
            System.out.println("Please enter it again !");
            return selectRaceNum();
        }
        else
        {
            return Integer.parseInt(raceNum);
        }
        //return Integer.parseInt(raceNum);
    }
    // public void everyLap(int venueIndex)
    // {
    // ArrayList<Integer> dryTyreList = new ArrayList<>();
    // }

    public int useSkill(boolean everyThirdLap, int index,int totalTimeThisLap)
    {
        RNG r1 = new RNG(1,8);
        RNG r2 = new RNG(10,20);
        int time1 = r1.generateRandomNo();//braking time
        int time2 = r2.generateRandomNo();//corning time
        //int currentTime = drivers.getDrivers().get(index).getAccumulatedTime();
        String skill = drivers.getDrivers().get(index).getSpecialSkill();
        String name = drivers.getDrivers().get(index).getName();
        if(skill.equals("Braking"))
        {
            System.out.println(name + "use skill Braking!" + "reduce" + time1 + "second");
            totalTimeThisLap = totalTimeThisLap - time1;
        }
        else if(skill.equals("Cornering"))
        {
            System.out.println(name + "use skill Cornering!" + "reduce" + time1 + "second");
            totalTimeThisLap = totalTimeThisLap - time1;
        }
        else if(skill.equals("Overtaking") && everyThirdLap)
        {
            System.out.println(name + "use skill Overtaking!" + "reduce" + time2 + "second");
            //drivers.getDrivers().get(index).setAccumulatedTime( currentTime - time1);
            totalTimeThisLap = totalTimeThisLap - time2;
        }
        return totalTimeThisLap;
    }

    public void loadVenues()
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> venuesContent = fileIO.readFile("venues.txt",4);
        for(int i = 0; i < venuesContent.size(); i += 4)
        {
            venues.addVenues(venuesContent.get(i),venuesContent.get(i + 1),venuesContent.get(i + 2),venuesContent.get(i + 3));   
        }
        System.out.println("Vennues' records has been loaded");
    }

    public void loadDrivers()
    {
        FileIO fileIO = new FileIO();
        ArrayList<String> driversContent = fileIO.readFile("drivers.txt",3);
        for(int i = 0; i < driversContent.size(); i +=3 )
        {
            drivers.addDrivers(driversContent.get(i),driversContent.get(i + 1),driversContent.get(i + 2),true,0,0);
        }
        System.out.println("Drivers' records has been loaded");
    }
}

