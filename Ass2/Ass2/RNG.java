
/**
 * Write a description of class RNG here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RNG
{
    // instance variables - replace the example below with your own
    private int minimumValue;
    private int maximumValue;

    /**
     * Constructor for objects of class RNG
     */
    public RNG()
    {
        // initialise instance variables
        minimumValue = 0;
        maximumValue = 0;
        
    }
    
    public RNG(int minimumValue, int maximumValue)
    {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }
    
    public void setMinimumValue(int minimumValue)
    {
        this.minimumValue = minimumValue;
    }
    
    public void setMaximumValue(int maximumValue)
    {
        this.maximumValue = maximumValue;
    }
    
    public int getMinimumValue()
    {
        return minimumValue;
    }
    
    public int getMaximumValue()
    {
        return maximumValue;
    }

    public int chanceEvent(int randomNum)
    {
        if(randomNum == 1)//%1
        {
            return 1;
        }
        if(randomNum > 1 && randomNum <= 4)//>=2 && <=4-----%3
        {
            return 3;
        }
        if(randomNum > 5 && randomNum <= 10)//--%5
        {
            return 5;
        }
        return 100;
    }
    
    public int generateRandom()
    {
        // put your code here
        int randomNum = (int)(Math.random() * (maximumValue - minimumValue + 1) + minimumValue);
        return randomNum;
    }
    
    public boolean calculateRain(int chanceOfRain)
    {
        int randomNo = generateRandom();
        if (randomNo <= chanceOfRain)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
}
