
/**
 * Write a description of class Star here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Star
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class Star
     */
    public Star()
    {
        // initialise instance variables
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void printStar(int y)
    {
        // put your code here
        
        for (int i = 1;i < y + 1;i++)
        {
            String emptyString = "";
            for (int j = 1; j < i + 1; j++)
            {
                StringBuffer sb = new StringBuffer(emptyString);
                sb.append("*");
                emptyString = sb.toString();
            }
            System.out.println(emptyString);
        }
    }
}
