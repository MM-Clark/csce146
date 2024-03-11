/*
 * Written by Michelle Clark
 */

import java.util.Scanner;

public class SorterBE 
{
    public final Sorter game = new Sorter();
    public static Scanner keyboard = new Scanner(System.in);
    public static String repeat;
    public LLQueue q = new LLQueue();

    public void runRound()
    {
        System.out.println("Enter any number of strings and I will sort by SORT's."+
            " Once you're done entering sentences enter 'quit'");
            boolean go = true;
            while(go)
            {
                String next = keyboard.nextLine();
                if(next.toUpperCase().contains("QUIT"))
                {
                    go = false;
                    break;
                }
                Sort temp = new Sort(next,0);
                //add temp to array/linked list
                q.enqueue(temp);
                // q.print();
                System.out.println("Enter next");
            }
            System.out.println("Initiating count of sorts."+"\n");
            q = game.sortSorts(q);
            System.out.println();
            System.out.println("Here are the results: ");
            game.mergeSort(q);
            System.out.println();
    }
    public boolean repeat() throws Exception
    {
        boolean run = false;
        System.out.println("Would you like to sort more Strings? Enter y/n.");
        repeat = keyboard.nextLine();
        if(repeat.contains("y"))
            run = true;
        else if(repeat.contains("n"))
            run = false;
        else
            throw new Exception("Your string did not contain y or n, what the heck dude...");
        return run;
    }
}
