import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Write a description of class FileIO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileIO
{
    // instance variables - replace the example below with your own
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
    public static ArrayList<String> readFile(String fileName,int delimeter)
    {
        ArrayList<String> info = new ArrayList<String>();
        try
        {
            FileReader filereader = new FileReader(fileName);
            try
            {
                Scanner scanner = new Scanner(filereader);
                while(scanner.hasNextLine())
                {
                    String lineContent = scanner.nextLine();
                    String[] sentenceArray = new String[delimeter];
                    sentenceArray = lineContent.split(",");
                    for (int i = 0;i < delimeter;i++)
                    {   
                        info.add(sentenceArray[i]);
                    }
                }
            }
            finally
            {
                filereader.close();
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs");
        } 
        return info;
    }
    
    public static void writeToFile(ArrayList<String> newDriverInfo) throws IOException
    {
        String filename = "drivers.txt";
        System.out.println("Writing new ranking result to the file...");
        PrintWriter outputFile = new PrintWriter(filename);
        try
        {
            for (int i = 0; i < newDriverInfo.size(); i = i + 3)
            {
                outputFile.println(newDriverInfo.get(i)+ "," + newDriverInfo.get(i + 1) + "," + newDriverInfo.get(i + 2));
            }
        }
        finally
        {
            System.out.println("New ranking saved.");
            outputFile.close();
        }

    }

}
