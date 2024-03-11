/*
 * Written by Michelle Clark
 */
//SELF NOTE: THIS WORKS AS EXPECTED (for filing purposes only)
import java.io.File;
import java.util.*;
public class FruitTreeTester 
{
    private static final LinkedBST<Fruit> fBST = new LinkedBST<Fruit>();
    private static final String IN_FILE = "./fruitFile.txt";
    private static final Scanner keyboard = new Scanner(System.in);
    private static final String DELIM = "\t";
    private static final int COLS = 2;
    public static void main(String[] args) throws Exception 
    {
        printOptions();
    }
    public static void printOptions()
    {
        System.out.println("Welcome to the Fruit Tree!");
        System.out.println("Please enter a Fruit File Name or x for default");
        String fileName = keyboard.nextLine();
        if(fileName.equalsIgnoreCase("x")||fileName.equalsIgnoreCase(""))
            fileName=IN_FILE;
        readFile(fileName, fBST); 
    }
    public static void readFile(String readFile, LinkedBST<Fruit> fBST)
    {
        try
        {
            //fun fact: this says resource leak but I do in fact close fileScanner
            Scanner fileScanner = new Scanner(new File(readFile));
            while(fileScanner.hasNext())
            {
                //stores the next line
                String next = fileScanner.nextLine();
                String[] parse = next.split(DELIM);
                //checks if right # of cols
                if(parse.length==COLS)
                {
                    //checks if valid name input
                    if(parse[0]!=null)
                    {
                        String type = parse[0];
                        if(!(type.equalsIgnoreCase("apple")||type.equalsIgnoreCase("orange")||
            type.equalsIgnoreCase("banana")||type.equalsIgnoreCase("kiwi")||type.equalsIgnoreCase("tomato")))
                        try
                        {
                            //checks if valid numerical input
                            double weight = Double.parseDouble(parse[1]);
                        }
                        catch(NumberFormatException e)
                        {
                            throw new NumberFormatException("Bad file...");
                        }
                        double weight = Double.parseDouble(parse[1]);
                        Fruit append = new Fruit(type, weight);
                        fBST.add(append);
                    }
                }
            }
            fileScanner.close();
            testMethods(fBST);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void testMethods(LinkedBST<Fruit> fBST)
    {
        System.out.println("Printing the in-order traversal");
        fBST.printInorder();

        System.out.println("\n"+"Printing out the pre-order traversal");
        fBST.printPreorder();

        System.out.println("\n"+"Printing the post-order traversal");
        fBST.printPostorder();

        Fruit delete = new Fruit("Apple",0.4859853412170728);
        System.out.println("\n"+"Deleting "+delete.toString());
        System.out.println("Printing the in-order traversal");

        fBST.remove(delete);
        fBST.printInorder();
        //shows still works!
        // Fruit delete2 = new Fruit("Kiwi",1.0340475191748726);
        // System.out.println("\n"+"Deleting "+delete2.toString());
        // System.out.println("Printing the in-order traversal");
        // fBST.remove(delete2);
        fBST.printInorder();
    }
}
