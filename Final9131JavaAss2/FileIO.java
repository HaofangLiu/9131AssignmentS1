import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 * Write a description of class FileIO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileIO
{
    // instance variables - replace the example below with your own
    private String fileName;
    private int delimeter;

    /**
     * Constructor for objects of class FileIO
     */
    public FileIO()
    {
        // initialise instance variables

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */

    public ArrayList<String> readFile(String fileName,int index )
    {
        ArrayList<String> data = new ArrayList<String>();
        try
        {
            FileReader inputFile = new FileReader(fileName);//filename
            try
            {
                Scanner parser = new Scanner(inputFile);//inputfile
                while(parser.hasNextLine())
                {
                    String content = parser.nextLine();
                    String[] lineArray = new String[index];
                    lineArray = content.split(",");
                    for(int i = 0;i <index ;i++)
                    {    
                        data.add(lineArray[i]);
                    }
                }
            }
            finally  
            {
                inputFile.close();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(fileName + "can not be found");
        }
        catch(IOException e)
        {
            System.out.println("I/O Error Occur");  
        }
        return data;
    }

    public void writeFile(ArrayList<String> newInfo)
    {
        try
        {
            fileName = "output.txt";
            PrintWriter output = new PrintWriter(fileName);//filename
            try
            {
                int i = 0;
                while(i < newInfo.size())
                {
                    //PrintWriter output = new PrintWriter(fileName);//filename
                    output.println(newInfo.get(i) + "," + newInfo.get(i + 1) + ","+ newInfo.get(i + 2));//parameters
                    i += 3;
                    //output.close();
                }

            }
            finally
            {
                System.out.println("write successful");  
                output.close();
            }
        }
        catch(IOException e)
        {
            System.out.println("I/O Error Occurs");
        }

    }
}
 