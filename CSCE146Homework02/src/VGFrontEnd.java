/*
 * Written by Michelle Clark
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class VGFrontEnd 
{
    //must use buffered reader to process numbers at beginning
    public static final String IN_FILE_NAME = "./Collection.txt";
    public static final String OUT_FILE_NAME = "./Results.txt";
    public static final String DELIM = "\t";
    public static final int COLS = 2;
    public static VGLinkedList<VideoGame> avail = new VGLinkedList<VideoGame>();
    public static VGLinkedList<VideoGame> results = new VGLinkedList<VideoGame>();
    public static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) throws Exception 
    {
        boolean run = true;
        while(run)
        {
            int input = getInput();
            switch(input)
            {
                case 1:
                    avail = loadDatabase();
                    break;
                case 2:
                    results = searchDatabase(IN_FILE_NAME, avail, results);
                    break;
                case 3: 
                    printResultsToConsole(results);
                    break;
                case 4: 
                    printResultsToFile(OUT_FILE_NAME, results);
                    break;
                case 5:
                    printAvailToConsole(avail);
                    break;
                case 0:
                    run = false;
                    break;
            }
        }
    }

    public static void print(String in)
    {
        System.out.println(in);
    }

    public static int getInput()
    {
        boolean validInput=false;
        //this is an invalid value so that if it isn't reassigned it will not pass the if statement checks
        int input = -3;
        while(!validInput)
        {
            boolean validDigit = true;
            boolean inRange = true;
            print("Welcome to the Video Game Database!");
            print("Enter 1 to load the video game database");
            print("Enter 2 to search the database");
            print("Enter 3 to print current results to the console");
            print("Enter 4 to print current results to file");
            print("Enter 5 to print all videogames to console");
            print("Enter 0 to quit");

            //the first check is for if the input is an integer, if word then bad input
            String inputS = keyboard.nextLine();
            try
            {
                input = Integer.parseInt(inputS);
            }
            catch(NumberFormatException e)
            {
                print("that is not an integer+\n");
                validDigit = false;
            }
            //the second check sees that the input is a valid number in range
            if(validDigit && input!=0 && input!=1 && input!=2 && input!=3 && input!=4 && input!=5)
            {
                print(input+" is not 0, 1, 2, 3, or 4, or 5...try again+\n");
                inRange = false;
            }
            //the two booleans that were assigned true then false if either check above failed
            //are checked to determine if we should break the while loop
            if(validDigit && inRange)
                validInput = true;
        }
        return input;
    }

    public static VGLinkedList<VideoGame> loadDatabase() throws IOException
    {
        boolean hasTxt = false;
        String input=null;
        while(!hasTxt)
        {
            System.out.println("Enter the name of the database you would like to load or x to stick with the original one");
            input = keyboard.nextLine();

            if((input.equalsIgnoreCase("x")||input.equals("")))
            {
                hasTxt=true;
                continue;
            }
            //THIS IS UNNECESSARY CODE
            // if(!(input.length()>4 && input.substring(input.length()-4,input.length()).equals(".txt")))
            // {
            //     System.out.println("bad file name: must include .txt");
            //     hasTxt=false;
            //     continue;
            // }

            //checking if the file actually exists
            File f = new File(input);
            //first must see it exists, then use isFile to ensure isFile and is not directory
            if(!(f.exists() && f.isFile()))
            {
                System.out.println("so we can't find the file...sorry try again");
                hasTxt=false;
                continue;
            }
            //indicates we did not get stopped and turned around, so we can say we do in fact have Txt
            //allowing us to get on with our lives
            hasTxt = true;
        }
    
        //if the user doesn't input anything we assume they want the default file
        if(input.equalsIgnoreCase("x")||input.equals(""))
            input = IN_FILE_NAME;
        createDatabase(input, avail);
        return avail;
    }

    public static VGLinkedList<VideoGame> searchDatabase(String fileName, VGLinkedList<VideoGame> avail, VGLinkedList<VideoGame> results)
    {
        System.out.println("Enter the title of the game or'*' for all");
        //no need for error checking because the user can really search for any character or number contained
        String name = keyboard.nextLine();
        System.out.println("Enter the name of the console or '*' for all");
        //same here
        String console = keyboard.nextLine();
        VideoGame find = new VideoGame(name,console);
        results = findMatch(find, name, console, results, avail);
        return results;
    }

    public static void createDatabase(String fileName, VGLinkedList<VideoGame> games) throws IOException
    {
        try
        {
            BufferedReader s = new BufferedReader(new FileReader(new File(fileName)));
            //tracks line position
            int position = 0;
            for(String line = s.readLine();line!=null;line=s.readLine())
            {
                position++;
                if(line!="")
                {
                    // System.out.println(line);
                    //checks for proper number of items before assigning
                    String[] items = line.split(DELIM);
                    //Bart vs. Space Mutants is no good it has tab at end
                    if(items.length==COLS)
                    {
                        String name = items[0];
                        String console = items[1];

                        VideoGame temp = new VideoGame(name,console);
                        games.add(temp);
                    }
                }
            }
            s.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("~ file scanned! ~");
    }

    public static void printAvailToConsole(VGLinkedList<VideoGame> results)
    {
        avail.print();
    }

    public static void printResultsToConsole(VGLinkedList<VideoGame> results)
    {
        results.print();
    }

    public static void printResultsToFile(String fileName, VGLinkedList<VideoGame> results) throws IOException
    {
        System.out.println("Enter the file name or x for default");
        String printFileName = keyboard.nextLine();
        //if the user messes up and presses enter, it is assumed that they want the default file2
        if(printFileName.equalsIgnoreCase("x")||printFileName.equalsIgnoreCase(""))
            printFileName = OUT_FILE_NAME;

        System.out.println("Would you like to append? True or false?");
        String append = keyboard.nextLine();

        //calling two separtae methods for print and append
        if(append.equalsIgnoreCase("true"))
            appendToFile(printFileName, results);
        else if(append.equalsIgnoreCase("false"))
            printToFile(printFileName, results);
    }

    public static void printToFile(String fileName, VGLinkedList<VideoGame> results)
    {
        System.out.println("~ adding to the file: ~");
        try
        {
            PrintWriter output = new PrintWriter(new FileOutputStream(fileName));
            int length = results.getSize();
            VideoGame temp = new VideoGame();
            results.reset();
            for(int i=0;i<length;i++)
            {
                temp = (VideoGame) results.getCurrent();
                output.println(temp.toString());
                results.goToNext();
            }
            output.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
    
    public static void appendToFile(String fileName, VGLinkedList<VideoGame> results) throws IOException
    {
        System.out.println("~ appending to the file: ~");
        results.print();
        try
        {
            //I separated this out to function
            FileWriter fw = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter output = new PrintWriter(bw);
            
            int length = results.getSize();
            VideoGame temp = new VideoGame();
            results.reset();
            for(int i=0;i<length;i++)
            {
                temp = (VideoGame) results.getCurrent();
                output.println(temp.toString());
                results.goToNext();
            }
            output.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
    public static VGLinkedList<VideoGame> findMatch(VideoGame other, String name, String console,VGLinkedList<VideoGame> results, VGLinkedList<VideoGame> avail)
    {
        // avail.print();
        VGLinkedList<VideoGame> copyOfResults = new VGLinkedList<>();
        int indices = avail.getSize();
        for(int i=0;i<indices;i++)
        {
            VideoGame temp = (VideoGame) avail.getAt(i);
            //for each index, if the name and console are both '*' then we want to store the value
            //regardless of its name or console, CHANGED TO EQUALS BC NAME/CONSOLE MAY CONTAIN *
            if(name.equals("*")&&console.equals("*"))
            {
                VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                System.out.println(ret.toString());
                copyOfResults.add(ret);
            }
            //otherwise, if only name contains "*", we want to check if the VideoGame contains in console 
            //to the search VideoGame
            else if(name.equals("*"))
            {
                if(temp.containsConsole(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
            //otherwise if only console contains "*" we want to see if the VideoGame contains the name
            //of the search VideoGame
            else if(console.equals("*"))
            {
                if(temp.containsName(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
            //otherwise if neither name or console contains "*" we want to make sure the result
            //contains both name and console
            else
            {
                if(temp.containsName(other)&&temp.containsConsole(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
        }
        //informing the user if size is 0
        if(copyOfResults.getSize()==0)
            System.out.println("~la busca devuelve nada; lo siento por su perdida~\n(the search returned nothing, I'm sorry for your loss)");
        return copyOfResults;
    }
}

