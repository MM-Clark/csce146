/*
 * Written by Michelle Clark
 */
import java.util.Scanner;
public class SorterFE 
{
    public final Sorter game = new Sorter();
    public static SorterBE runner = new SorterBE();
    public static Scanner keyboard = new Scanner(System.in);
    public static String next;
    public static String repeat;
    public LLQueue q = new LLQueue();

    public static void main(String[] args) throws Exception 
    {
        boolean run=true;
        while(run)
        {
            runner.runRound();
            run=runner.repeat();
        }
        System.out.println("Shutting down goodnight");
    }
}
