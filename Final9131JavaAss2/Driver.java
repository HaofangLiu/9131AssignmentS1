
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    // field
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedScore;
    private int accumulatedTime;

    /**
     * Constructor for objects of class Driver
     */
    public Driver()
    {
        // default constructor
        name = "";
        ranking = 0;
        specialSkill = "";
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
    public Driver(String name,int ranking,String specialSkill,boolean eligibleToRace,int accumulatedScore, int accumulatedTime)
    {
        // put your code here
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

    public int getRanking()
    {
        return ranking;
    }

    public String getSpecialSkill()
    {
        return specialSkill;
    }

    public boolean getEligibleToRace()
    {
        return eligibleToRace;
    }

    public int getAccumulatedScore()
    {
        return accumulatedScore;
    }

    public int getAccumulatedTime()
    {
        return accumulatedTime;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    public void setRanking(int ranking)
    {
        this.ranking = ranking;
    }

    public void setSpecialSkill(String specialSkill)
    {
        this.specialSkill = specialSkill;
    }

    public void setEligibleToRace(boolean eligibleToRace)
    {
        this.eligibleToRace = eligibleToRace;
    }

    public void setAccumulatedScore(int accumulatedScore)
    {
        this.accumulatedScore = accumulatedScore;
    }

    public void setAccumulatedTime(int accumulatedTime)
    {
        this.accumulatedTime = accumulatedTime;
    }

    public void displayDriverInfo()
    {
        System.out.println( name + "whose rank is" + ranking + "use" + specialSkill + "skill with accumulagted score" + accumulatedScore + "and accumulated time" + accumulatedTime); 
    }

}
