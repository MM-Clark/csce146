/*
 * Written by JJ Shepherd
 */
import java.util.Scanner;
public class FileIOProblems {

	public static Scanner keyboard = new Scanner(System.in);
	public static void main(String[] args) {
		
		System.out.println("Welcome to the File I/O Problem's Solutions!");
		boolean quit = false;
		while(!quit)
		{
			printChoices();
			int input = keyboard.nextInt();
			keyboard.nextLine();//Useful fix-up
			switch(input)
			{
			case 1:
				test01();
				break;
			case 2:
				test02();
				break;
			case 9:
				quit = true;
				break;
				default:
					System.out.println("Invalid Input");
			}
		}
		System.out.println("Goodbye!");

	}
	public static void printChoices()
	{
		System.out.println("Enter 1. For Test 1\nEnter 2. For Test 2\nEnter 9. To Quit");
	}
	public static void test01()
	{
		System.out.println("\n-----------------------\nTest01\n-----------------------");
		System.out.println("Testing method \"pastTense\" with the file \"ItIs.txt\" and outputting to \"ItWas.txt\"");
		FileIOSolutions.pastTense("ItIs.txt", "ItWas.txt");
	}
	public static void test02()
	{
		System.out.println("\n-----------------------\nTest02\n-----------------------");
		System.out.println("Testing method \"totalTubeVolume\" with the file \"./Tubes.txt\"");
		double volume = FileIOSolutions.totalTubeVolume("./Tubes.txt");
		System.out.println("The total combined volume is: "+volume+"\n");
	}

}
