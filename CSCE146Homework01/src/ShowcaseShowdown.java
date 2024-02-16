/*
 * Written by Michelle Clark
 */

//write a program that replicates the final game of a famous price related game show. In this version, a 
//"Showcase" contains exactly 3 unique randomly selected prizes. The user must guess the sum of the prizes
//within $1300 below or equal to the sum in order to win. 

import java.io.*;
import java.util.*;

public class ShowcaseShowdown 
{
    //this is private scope for encapsulation
    private static final String IN_FILE_NAME = "./Prizelist.txt";
    private static final String DELIM = "\t";
    private static final String HEADER = "Prizelist";//dead code, useful if there was a header line on the txt file that we didn't want to parse/store
    private static final int COLUMNS = 2;
    private static final int RANDOMS = 5;//length of array of random index numbers to select random prizes
    private static final Scanner keyboard = new Scanner(System.in);
    // private static Showcase showcase = new Showcase(); -> MADE MORE EFFICIENT BY INITIALIZING LATER
    //coloring stuff, public scope because this data isn't super sensitive
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static Showcase readList(String fileName) throws FileNotFoundException
    {
        String ret="";
        double priceHolder;
        String validInput;
        int count = 0;
        Showcase showcase;
        try
        {
            Scanner input = new Scanner(new File(fileName));
            //finding the number of lines of file to init array to that size
            while(input.hasNextLine())
            {
                input.nextLine();
                count++;
            }
            showcase = new Showcase(count);
            
            int nameCol = 0;
            int priceCol = 1;
            // int index = 0; 
            
            input = new Scanner(new File(fileName));
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                //checks that there is actually information in the line
                if(line!="")
                {
                    String linePieces[] = line.split(DELIM);
                    //checks that we have properly split into name and price, which creates two cols
                    if(linePieces.length == COLUMNS)
                    {
                        String name = linePieces[nameCol];
                        try
                        {
                            double price = Double.parseDouble(linePieces[priceCol]);
                        }
                        catch(Exception e)
                        {
                            continue;
                        }
                        double price = Double.parseDouble(linePieces[priceCol]);
                        //now i need to assign these values to the showcase array
                        //maybe i can use a for loop, can't use add bc not allowed
                        //to use linked list or ArrayCollections
                        Prize temp = new Prize(name,price);
                        // showcase.replaceVals(name, price, index);
                        // index++;
                        showcase.addPrize(temp);
                    }  
                }
            }
            input.close();
            return showcase;
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            // break;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        // showcase.findAntacid();
        return null;
    }
    public static Showcase findPrizes(Showcase showcase)
    {
        return showcase;
    }
    public static boolean findWithinTol(double guess, double actual)
    {
        //taking the absolute value of subtracting the actual amt from the guess amt gives us the distance
        //btwn the two values 
        //return guess>=0 && Math.abs(guess-actual)<=1300;
        return guess>=0 && guess<=actual && guess>=actual-1300;
    }

    public static int[] generateRandomIndices(int times, Showcase showcase)
    {
        int[] randoms = new int[times];
        int[] alreadyChosen = new int[showcase.getLength()];
        int temp;
        int length = randoms.length;
        int max = showcase.getLength()+1;
        int min = 1;

        int count = 0;
        //tracks how many times this runs in total; could be useful for something or debugging
        int tracker = 0;

        for(int i=0;i<length;i++)
        {
            count = 0;
            //multiplying the array by a decimal between 0 and 1 will give us a random array index
            temp = min + (int)(Math.random() * ((max - min)));

            //THIS FUNCTION DEALS WITH REPEAT VALS
            for(int j=0;j<alreadyChosen.length;j++)
            {
                if(alreadyChosen[j]==temp)
                {
                    break;
                }
                else
                {
                    count++;
                }
            }
            //if the counter value is equal to the length of the alreadyChosen array, we had no repeat values
            //so we can assign the value
            if(count==alreadyChosen.length)
            {
                randoms[i]=temp;
                alreadyChosen[i]=temp;
            }
            //otherwise we must try again to generate a random value for the index
            else
            {
                i--;
            }

            tracker++;
        }

        return randoms;
    }
    public static void runRound(Showcase available) throws OutOfPriceRangeException
    {
        String guessString=null;
        //just a negative value because yeah
        double guessDouble=-3;
        double actualPrice=0;
        boolean correct=false;
        

        System.out.println("\n"+"Your available prizes are: ");

        //here I generate the random indices for the prize array
        //note that using a public static int value makes it easier to change the amount of random prizes to be chosen
        //because I can simply change it at the top of the code
        int[]randoms = generateRandomIndices(RANDOMS, available);
        Showcase selection = available.selectPrizes(available, randoms);
        
        //showing to the user while hiding prices 
        selection.printPrizeNames();

        //now the user must guess the sum of the prizes
        System.out.println("Try guessing the sum of these prizes!");

        //calculating the actual price
        actualPrice = selection.addPrices();

        boolean validInput = false;
        //forcing the user to do stuff the right way
        while(!validInput)
        {
            boolean validInputAboveZero=true;
            boolean validInputNumericalValue=true;
            System.out.println("Go ahead, enter a decimal number above 0.");
            guessString = keyboard.nextLine();
            try
            {
                guessDouble=Double.parseDouble(guessString);
            }
            catch(NumberFormatException e)
            {
                //has to sit above the throw statement or it's invalid code
                validInputNumericalValue = false;
                System.out.println("That's not a number bud...");
            }
            //triggered only if we have already decided that the input is a non-digit
            //ASSUMPTION IS THAT A PRICE OF 0 IS A VALID PRICE TO GUESS, AS ITEMS COULD BE FREE
            if(validInputNumericalValue && guessDouble<0)
            {
                validInputAboveZero = false;
                System.out.println(guessDouble+" is at or below zero...");
            }
            if(validInputAboveZero && validInputNumericalValue)
                validInput=true;
        }

        //find if within the tolerance of $1,000
        System.out.println("\n"+"Let's find out if these values are within the tolerance!");
        correct = findWithinTol(guessDouble,actualPrice);

        if(correct==true)
        {
            System.out.println("Good job! You guessed the price sum within $1300!");
            System.out.println("It was actually "+actualPrice+"\n");

            System.out.println(ANSI_CYAN+"actual prizes: ");
            selection.printPrizes();
            System.out.println(ANSI_RESET);
        }

        else if(correct==false)
        {
            System.out.println("Oof. That was out of range...");
            System.out.println("It was actually "+actualPrice+"\n");

            System.out.println(ANSI_CYAN+"actual prizes: ");
            selection.printPrizes();
            System.out.println(ANSI_RESET);
        }
    }
    public static int getInput()
    {
        boolean valid = false;
        String input = "";
        int parsedInput = -1;
        while(!valid)
        {
            boolean validInputNumericalValue = true;
            boolean validInRange = true;
            System.out.println("Welcome to Showcase Showdown!");
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to initiate a round");
            System.out.println("Press 2 to view the full prize array");
            System.out.println("Press 3 to quit");

            input = keyboard.nextLine();
            try
            {
                parsedInput = Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                System.out.println("~ that is not a numerical integer dude (you entered a word instead of number 1, 2, or 3), try again ~");
                validInputNumericalValue = false;
                
            }
            if(validInputNumericalValue && (parsedInput!=1 && parsedInput!=2 && parsedInput!=3))
            {
                System.out.println("~ Enter 1, 2, or 3. Your number was outside of these values... ~");
                validInRange = false;
                
            }
            if(validInputNumericalValue && validInRange)
                valid = true;
        }
        return parsedInput;   
    }
    public static void main(String[] args) throws IOException, OutOfPriceRangeException 
    {
        Showcase available = new Showcase();
        boolean runProgram = true;

        int choice=0;

        //getting the available prizes from the file
        //put outside for loop because makes more sense unless the file changed for each iteration of the game
        available = readList(IN_FILE_NAME);
        while(runProgram == true)
        {
            choice = getInput();

            switch(choice)
            {
                case 1:
                    runRound(available);
                    break;
                case 2:
                    available.printPrizes();
                    break;
                case 3:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        System.out.println("\n"+"Night you guys...");
        keyboard.close();
    }
}
