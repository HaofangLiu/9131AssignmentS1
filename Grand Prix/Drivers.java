
/**
 * Write a description of class Drivers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Drivers
{
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedScore;
    private int accumulatedTime;
    
    /**
     * Constructor for objects of class Drivers
     */
    public Drivers()
    {
        name = "";
        ranking = 0;
        specialSkill = "";
        eligibleToRace = true;
        accumulatedScore = 0;
        accumulatedTime = 0;
    }
    
    public Drivers(String name,int ranking,String specialSkill)
    {
        this.name = name;
        this.ranking = ranking;
        this.specialSkill = specialSkill;
        eligibleToRace = true;
        accumulatedScore = 0;
        accumulatedTime = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
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
