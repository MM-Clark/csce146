/*
 * Written by Michelle Clark
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class FileIOSolutions 
{
    private static final int ARRAY_SIZE = 100;
    private static final int TUBE_COLS = 3;
    private static final String DELIM = "\t";
    private static Tube[] tubes;

    public static void pastTense(String readFile, String outputFile)
    {
        String append;
        //I use an array list to append words to store them 
        ArrayList<String> words = new ArrayList<String>();
        try
        {
            Scanner fileScanner = new Scanner(new File(readFile));
            while(fileScanner.hasNext())
            {
                //stores the next word
                String next = fileScanner.next();
                
                //checks if the word is "is", if so change to "was"
                if(next.equalsIgnoreCase("is"))
                {
                    next = "was";
                }
                append = new String(next);
                //append the line to the out file
                words.add(append);
            }
            //here I call another method to print file that takes in the String fileName and ArrayList words as input
            printToFile(outputFile, words);
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private static void init(int size)
    {
        if(size>=1)
            tubes = new Tube[size];
        else
            tubes= new Tube[ARRAY_SIZE];
    }
    public static double totalTubeVolume(String inFile)
    {
        init(-1);
        double totalTubeVolume = 0.0;
        // //to store the radii of the tubes
        // double[] radii = new double[ARRAY_SIZE];
        // //to store the heights of the tubes
        // double[] heights = new double[ARRAY_SIZE];

        // //to store each tube's volume
        // double[] volumes = new double[ARRAY_SIZE];
        int index = 0;

        try
        {
            Scanner fileScanner = new Scanner(new File(inFile));
            while(fileScanner.hasNextLine())
            {
                //stores the next word
                String next = fileScanner.nextLine();
                //checks for null line
                if(next!="")
                {
                    //splits the line 
                    String[] tubeInfo = next.split(DELIM);
                    //check for proper # of parameters
                    if(tubeInfo.length == TUBE_COLS)
                    {
                        //storing the current tube's radius and height
                        //note that I don't access the tubeInfo[0] because tube names are not necessary to solve the problem
                        double tubeRadius = Double.parseDouble(tubeInfo[1]);
                        double tubeHeight = Double.parseDouble(tubeInfo[2]);
                        Tube t = new Tube(tubeRadius,tubeHeight);
                        
                        //adding to arrays
                        if(tubes[index]==null)
                            tubes[index]=t;
                        
                        //increasing index to access the next null element of each array
                        index++;
                    }
                }
            }
            
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // for(int i=0;i<tubes.length;i++)
        // {
        //     //breaks the for loop once the values of height and radius become 0, which indicates they are null
        //     if(radii[i]==0 && heights[i]==0)
        //         break;
        //     volumes[i]=Math.pow(radii[i],2)* Math.PI* heights[i];
        // }

        for(int i=0;i<tubes.length;i++)
        {
            //iterates through the volume array to add to the total volume
            if(tubes[i]!=null)
                totalTubeVolume+=tubes[i].getVolume();
        }

        return totalTubeVolume;
    }

    public static void printToFile(String fileName, ArrayList<String> text)
    {
        try
        {
            //setting up the output printwriter
            PrintWriter output = new PrintWriter(new FileOutputStream(fileName),true);
            for(int i=0;i<text.size();i++)
            {
                //printing to the output file
                output.println(text.get(i));
                //printing to the console
                System.out.println(text.get(i));
            }
            //to prevent resource leak
            output.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
}
