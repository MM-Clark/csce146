/*
 * Written by Michelle Clark
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SheepSchedulerFE 
{
    private static final Scanner keyboard = new Scanner(System.in);
    private static final String IN_FILE_NAME="./sheep.txt";
    private static final String DELIM = "\t";
    private static final int COLS = 3;

    private static SheepSheduler runner = new SheepSheduler();
    private static MinHeapEntryPt<Sheep> waiting = new MinHeapEntryPt<Sheep>();
    private static GenLL<String> schedule;
    public static void main(String[] args) throws Exception 
    {
        boolean run = true;
    
        while(run)
        {
            loadDatabase();
            System.out.println("WARNING: the following simulation\nmay be intensive for your laptop\nproceed with caution\npress any key to continue");
            String anything=keyboard.nextLine();
            System.out.println("\n"+"NOW SIMULATING........");
            schedule = new GenLL<String>();
            schedule=runner.simulate(schedule, waiting);
            System.out.println("Now that you know it simulated, how about we give you a schedule\npress any key to continue");
            String anythingElse=keyboard.nextLine();
            runner.print(schedule);
            run = rerun();
        }
        System.out.println("goodbye!");
    }
    public static void loadDatabase()
    {
        boolean validInput=false;
        String input = "none";
        while(!validInput)
        {
            System.out.println("Enter the name of the database you would like to load or x to stick with the original one");
            input = keyboard.nextLine();
            if(input.equalsIgnoreCase("x")||input.equalsIgnoreCase(""))
            {
                validInput=true;
                break;
            }
            File f = new File(input);
            if(f.exists())
            {
                validInput = true;
                break;
            }
            System.out.println("Welp the file wasn't detected in the system; try again");
        }
        if(input.equalsIgnoreCase("x")||input.equalsIgnoreCase(""))
            input = IN_FILE_NAME;
        waiting = runner.createDatabase(input,waiting);
        System.out.println("WAITING PROCESSED");
    }
    public static boolean rerun() throws Exception
    {
        boolean rerun;
        System.out.println("Would you like to run the program again? Enter y/n.");
        String next = keyboard.nextLine();
        if(next.contains("y"))
            rerun=true;
        else if(next.contains("n"))
            rerun=false;
        else
            throw new Exception("Invalid input");
        return rerun;
    }
}
