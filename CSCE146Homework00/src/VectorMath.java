/*
 * Written by Michelle Clark
 */
import java.util.*;
import javax.swing.JOptionPane;
import java.io.*;
public class VectorMath 
{
    //only accessable across this class, done to make life easier with methods
    private static double[] vector1;
    private static double[] vector2;
    //I pass in order to allow the user to see which order the vector is coming in at
    //It's a string, so I can set it to "" to avoid entering a number
    public static double[] init(int numberVecs, String order)
    {
        String sizeString="";
        String value;
        //used to ensure that if we have an error we can go back to the main menu
        boolean keepGoing=true;
        double[] vector;
        int size=-1;
        int orderInt = -1;
        //checker val for if second string array
        if(order!=null)
            orderInt = Integer.parseInt(order);
        while(keepGoing)
        {
            //the boolean checks that size is greater than 0; if so, we exit the loop
            //this indicates the user won't try to create an array of negative or 0 length
            while(size<1)
            {
                if(orderInt==2)//implies that the first length exists
                {
                    //therefore we set the first length to that length and break
                    size = vector1.length;
                    break;
                }

                if(numberVecs>1)
                    sizeString = JOptionPane.showInputDialog(null,"Enter the size of the vectors");
                else
                    sizeString = JOptionPane.showInputDialog(null,"Enter the size of vector "+order);
                
                try
                {
                    size = Integer.parseInt(sizeString);
                }
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "that's not an integer of digits bud.\n try again");  
                    size=-1;
                }

                //inclusion of if-statement to allow users who decide to enter negative values to realize their wrongdoings
                if(size<1)
                    JOptionPane.showMessageDialog(null, "that's not a number above zero bud.\n try again"); 
                //for torturing the user if you want to make them remember the size of the first array
                // if(orderInt>1 && size!=vector1.length)
                // {
                //     JOptionPane.showMessageDialog(null, "so unfortunately your arrays must be of equal size;\nyou'll have to reenter the data");
                //     size = -1;
                // }
            }

            vector = new double[size];
        
            for(int i=0;i<vector.length;i++)
            {
                boolean validInput = false;
                double input1=0;//not worried about initial value bc will be changed...eventually 
                //if user enters valid input
                while(!validInput)
                {
                    if(i==0)
                        value = JOptionPane.showInputDialog(null,"Enter the first value of vector "+order);
                    else
                        value = JOptionPane.showInputDialog(null,"Enter the next value of vector "+order);
                    try
                    {
                        input1 = Double.parseDouble(value);
                        validInput = true;
                    }
                    catch(NumberFormatException e)
                    {
                        JOptionPane.showMessageDialog(null, "you did not enter a decimal number, try again");
                        validInput = false;
                    }
                }
                vector[i]=input1;
            }
            return vector;
        }
        return null;  
    }
    
    public static double[] addVectors(double[] vec1, double[] vec2)
    {
        int length = vec1.length;
        double[] ret = new double[length];
        for(int i=0;i<length;i++)
        {
            //iterates through each index of each vector, adds them together,
            //and stores the value at the corresponding index of return 
            ret[i] = vec1[i] + vec2[i];
        }

        JOptionPane.showMessageDialog(null,"Result is in the command line!");
        printVector(vec1);
        System.out.println("+");
        printVector(vec2);
        System.out.println("=");
        printVector(ret);
        return ret;
    }
    public static double[] subtractVectors(double[]vec1, double[] vec2)
    {
        int length = vec1.length;
        double[] ret = new double[length];
        for(int i=0;i<length;i++)
        {
            ret[i] = vec1[i]-vec2[i];
        }

        JOptionPane.showMessageDialog(null,"Result is in the command line!");
        printVector(vec1);
        System.out.println("-");
        printVector(vec2);
        System.out.println("=");
        printVector(ret);
        return ret;
    }
    public static double findMag(double[] vec)
    {
        double[] temp = new double[vec.length];
        double ret = 0;

        for(int i=0;i<vec.length;i++)
        {
            temp[i]=Math.pow(vec[i], 2);
        }

        for(int i=0;i<vec.length;i++)
        {
            ret+=temp[i];
        }

        ret = Math.sqrt(ret);
        System.out.println("Result: ");
        System.out.println("Original vector: ");
        printVector(vec);
        System.out.println("The vectors magnitude is "+ret);
        return ret;
    }
    public static void printVector(double[] vec)
    {
        //iterates through each vector and prints it out to the console
        //if we just tried to do System.out.println(vec1); we would get 
        //the memory address
        for(int i=0;i<vec.length;i++)
        {
            System.out.println(vec[i]);
        }
    }
    public static boolean checkValidity(double[] vec1, double[] vec2)
    {
        //we initiate the check for if the lengths of the arrays are different at the start of the program
        //to be efficient aka to avoid reserving space in memory for the integer length and empty double
        //array ret
        if(vec1.length!=vec2.length)
        {
            JOptionPane.showMessageDialog(null,"ERROR: Unequal lengths");
            return false;
        }
        if(vec1==null || vec2==null)
        {
            JOptionPane.showMessageDialog(null,"ERROR: Null vector");
            return false;
        }
        //note that I don't include a size check bc I already checked that initially
        return true;
    }
    public static int startProgram()
    {
        String inputString;
        boolean valid = true;
        boolean triggerAltErrMessage=false;
        int input=-1;
        do
        {
            triggerAltErrMessage=true;
            inputString = JOptionPane.showInputDialog(null,"Welcom to the Vector Operations Program!"+"\n"
            +"Enter 1. To Add 2 Vectors"+"\n"+"Enter 2. To Subtract 2 Vectors"+"\n"+
            "Enter 3. To Find the Magnitude of a Vector"+"\n"+"Enter 9. to Quit");
            //I do this to avoid the keyboard issue Java loves to give us when we use keyboard.nextInt
            try
            {
                input = Integer.parseInt(inputString);
                valid = true;
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "We need numbers in this venue, try again");
                valid = false;
                triggerAltErrMessage=false;
            }
            if(!(input==1||input==2||input==3||input==9)&&(triggerAltErrMessage))
            {
                JOptionPane.showMessageDialog(null, "You have four options for numbers; try again");
                valid = false;
            } 
        }
        while(!valid);

        return input;
    }
    public static void main(String[] args) throws Exception 
    {
        boolean run = true;
        boolean sameSize = false;
        while(run)
        {
            int input = startProgram();
            switch(input)
            {
                case 1:
                    vector1 = init(1,"1");
                    vector2 = init(1,"2");
                    sameSize = checkValidity(vector1, vector2);
                    if(sameSize)
                        addVectors(vector1, vector2);
                    break;
                case 2:
                    vector1 = init(1,"1");
                    vector2 = init(1,"2");
                    sameSize = checkValidity(vector1, vector2);
                    if(sameSize)   
                        subtractVectors(vector1, vector2);
                    break;
                case 3:
                    vector1= init(1, "1");
                    if(vector1!=null)
                        findMag(vector1);
                    break;
                case 9:
                    run=false;
                    break;
                default:
                    System.out.println("Bad input");
                    break;
            }
            System.out.println();
        }
        JOptionPane.showMessageDialog(null,"goodnight");
    }
}
