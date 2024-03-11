/*
 * Written by JJ Shepherd
 */
//Do not alter-----------------------------------------------
import java.util.Scanner;
import java.io.*;
public class GroceryListTester {

	//The grocery list being tested
	public static GroceryList gList = new GroceryList();
	//Name of the provide file assumed to be in the project's directory
	public static String fileName = "./GroceryList.txt";
	//The grocery list is assumed to be tab-delimited
	public static final String DELIM = "\t";
	//The file is assumed to have the data name and value for each item, thus 2 fields
	public static final int FIELD_AMT = 2;
	
	public static void main(String[] args) {
		System.out.println("Grocery List Tester.");
		test01();
		test02();
		test03();
		test04();
	}
	//Adding elements to the list from a file
	public static void test01()
	{
		printTest(1);
		System.out.println("Reading the grocery list.");
		try
		{
			//Creates the scanner to read the file
			Scanner fileScanner = new Scanner(new File(fileName));
			//While there is a next line
			while(fileScanner.hasNextLine())
			{
				//Read the next line in the file
				String fileLine = fileScanner.nextLine();
				//Split said line based on the tab delimiter
				String[] splitLines = fileLine.split(DELIM);
				//Check if it is the correct size
				if(splitLines.length != FIELD_AMT)
					continue;//If not then skip it
				//Otherwise create a new Grocery Item from the data in split line
				String name = splitLines[0];
				double value = Double.parseDouble(splitLines[1]);//Converts the String value to a double value
				GroceryItem g = new GroceryItem(name,value);
				//Adds the new item to the end of the list
				gList.addItem(g);
			}
			fileScanner.close();//Closes the file Scanner
			gList.showList();//Prints out the list
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void test02()
	{
		printTest(2);
		System.out.println("Finding the total cost.");
		System.out.println("Total Cost: "+gList.totalCost());
	}
	public static void test03()
	{
		printTest(3);
		GroceryItem g = new GroceryItem("Potatoes",5.32);
		System.out.println("Checking Contains with item known to be in the list\n"+g);
		System.out.println("Contains? "+gList.contains(g));
		g = new GroceryItem();
		System.out.println("Checking Contains with item known to NOT be in the list\n"+g);
		System.out.println("Contains? "+gList.contains(g));
	}
	public static void test04()
	{
		printTest(4);
		
		System.out.println("Accessing First Item in the list");
		GroceryItem g = gList.getCurrent();
		System.out.println(g);
		
		System.out.println("\nRemoving First Item in the list");
		gList.removeCurrent();
		gList.showList();
		
		System.out.println("\nAccessing Fourth Item in the list");
		gList.gotoNext();
		gList.gotoNext();
		gList.gotoNext();
		g = gList.getCurrent();
		System.out.println(g);
		
		System.out.println("\nRemoving Fourth Item in the list");
		gList.removeCurrent();
		gList.showList();
	}
	public static void printTest(int testNum)
	{
		System.out.println("\n-----------------------------------------------\n"+
							"Test "+testNum+
							"\n-----------------------------------------------");
	}

}
//-----------------------------------------------