import java.util.Random;
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

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public RNG(int min,int max)
    {
        // put your code here
        minimumValue = min;
        maximumValue = max;
    }
    
    public int getMinValue()
    {
        return minimumValue;
    }
    
    public int getMaxValue()
    {
        return maximumValue;
    }
    
    public void setMinValue(int min)
    {
        minimumValue = min;
    }
    
    public void setMaxValue(int max)
    {
        maximumValue = max;
    }
    
    public int generateRandomNo()
    {
        int randomNo = minimumValue + (int)(Math.random() * (maximumValue - minimumValue +1));
        return randomNo;
    }
    
    public int randomEvent(int randomNo)
    {
        if(randomNo >= 1 && randomNo < 6)
        {
            return 5;
        }
        
        if(randomNo == 6)
        {
            return 1;
        }
        
        if(randomNo > 6 && randomNo <= 9)
        {
            return 3;
        }
        return 100;
    }
    
    public boolean calculateRain(int randomNo)
    {
        int chanceOfRain = generateRandomNo();
        if(chanceOfRain > randomNo)
        {
            return false;
        }
        return true;
    }
}
