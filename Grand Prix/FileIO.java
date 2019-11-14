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
    }

    /**
     * 
     */
    public static ArrayList<String> readFile(String filename,int loopTime)
    {
        ArrayList<String> info = new ArrayList<String>();
        try
        {
            FileReader inputFile = new FileReader(filename);
            try
            {
                Scanner parser = new Scanner(inputFile);
                while (parser.hasNextLine())
                {
                    String lineContent = parser.nextLine();
                    String[] outputArray = new String[loopTime];
                    if (!lineContent.trim().isEmpty())
                        outputArray = lineContent.split(",");
                    for (int i = 0; i < loopTime; i++)
                        info.add(outputArray[i]);
                }
            }
            finally
            {
                inputFile.close();
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(filename + " not found");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs");
        } 
        return info;
    }

    public static void writeToFile(ArrayList<String> newDriverInfo)
    {
        String filename = "drivers2.txt";
        System.out.println("Writing new ranking result to the file...");
        try 
        {
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

        catch(IOException e) 
        {
            System.out.println("Unexpected I/O exception occurs");
        }

    }

}
