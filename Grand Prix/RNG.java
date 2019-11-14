
/**
 * Write a description of class RNG here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RNG
{
    private int minimumValue;
    private int maximumValue;
    
    public RNG()
    {
        minimumValue = 0;
        maximumValue = 0;
    }
    
    public RNG(int minimumValue, int maximumValue)
    {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    public int randomGenerator()
    {
        return (int)(Math.random() * (maximumValue - minimumValue + 1) + minimumValue);
    }
    
    //1% and 5%
    public boolean calculatePossibilityFiveAndOne()
    {
        if (randomGenerator() == 1)
            return true;
        else
            return false;
    }
    
    //%3
    public boolean calculatePossibilityThree()
    {
        int randomNo = randomGenerator();
        if (randomNo == 1 || randomNo == 2 || randomNo == 3)
            return true;
        else
            return false;
    }
    
    //HD+ chance of rain
    public boolean calculateRain(int chanceOfRain)
    {
        int randomNo = randomGenerator();
        if ( randomNo <= chanceOfRain)
            return true;
        else 
            return false;
    }

    public int getMinimumValue()
    {
        return minimumValue;
    }
    
    public void setMinimumValue(int minimumValue)
    {
        this.minimumValue = minimumValue;
    }
    
    public int getMaximumValue()
    {
        return maximumValue;
    }
    
    public void setMaximumValue(int maximumValue)
    {
        this.maximumValue = maximumValue;
    }
}
