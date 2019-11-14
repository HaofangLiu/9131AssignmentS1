
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables - replace the example below with your own
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;//can play game?
    private int accumulatedScore;//update after menu
    private int accumulatedTime;//

    /**
     * Constructor for objects of class Driver
     */
    public Driver()
    {
        // initialise instance variables
        name = "";
        ranking = 0;
        specialSkill = "";
        eligibleToRace = true;
        accumulatedScore = 0;
        accumulatedTime = 0;
    }

    public Driver(String name,int ranking,String specialSkill,boolean eligibleToRace,int accumulatedScore,int accumulatedTime)
    {
        this.name = name;
        this.ranking = ranking;
        this.specialSkill = specialSkill;
        this.eligibleToRace = eligibleToRace;
        this.accumulatedScore = accumulatedScore;
        this.accumulatedTime = accumulatedTime;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRanking()
    {
        return ranking;
    }

    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    public String getSpecialSkill()
    {
        return specialSkill;
    }

    public void setSpecialSkill(String specialSkill)
    {
        this.specialSkill = specialSkill;
    }

    public boolean getEligibleToRace()
    {
        return eligibleToRace;
    }

    public void setEligibleToRace(boolean eligibleToRace)
    {
        this.eligibleToRace = eligibleToRace;
    }

    public int getAccumulatedScore()
    {
        return accumulatedScore;
    }

    public void setAccumulatedScore(int accumulatedScore)
    {
        this.accumulatedScore = accumulatedScore;
    }

    public int getAccumulatedTime()
    {
        return accumulatedTime;
    }

    public void setAccumulatedTime(int accumulatedTime)
    {
        this.accumulatedTime = accumulatedTime;
    }
}
