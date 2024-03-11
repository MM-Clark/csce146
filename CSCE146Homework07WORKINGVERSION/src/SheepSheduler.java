/*
 * Written by Michelle Clark
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SheepSheduler 
{
    private static final String IN_FILE_NAME="./sheep.txt";
    private static final String DELIM = "\t";
    private static final int COLS = 3;
    private static final String LINES = "----------------------";

    //creating the database of sheep
    public MinHeapEntryPt<Sheep> createDatabase(String fileName,MinHeapEntryPt<Sheep> in)
    {
        String ret = "";
        try
        {
            //major error here for some reason, while hasNext never executes
            Scanner fileScanner = new Scanner(new File(fileName));

            while(fileScanner.hasNext())
            {
                String line = fileScanner.nextLine();
                if(line!="")
                {
                    String[] items = line.split(DELIM);
                    if(items.length==COLS)
                    {
                        String name = items[0];
                        String shearingTimeS = items[1];
                        String arrivalTimeS = items[2];
                        try
                        {
                            int shearingTime = Integer.parseInt(shearingTimeS);
                        }
                        catch(NumberFormatException e)
                        {
                            throw new NumberFormatException("bad input");
                        }
                        int shearingTime = Integer.parseInt(shearingTimeS);
                        try
                        {
                            int arrivalTime = Integer.parseInt(arrivalTimeS);
                        }
                        catch(NumberFormatException e)
                        {
                            throw new NumberFormatException("bad input");
                        }
                        int arrivalTime = Integer.parseInt(arrivalTimeS);

                        Sheep temp = new Sheep(name,shearingTime,arrivalTime);
                        in.add(temp);
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return in;
    }
    public GenLL<String> simulate(GenLL<String> schedule, MinHeapEntryPt<Sheep> avail)
    {
        ShearScheduler s = new ShearScheduler();

        Sheep next = new Sheep("Default",0,0);
        Sheep current = new Sheep("Default",0,0);

        int minute = 0;
        int currSheep = 0;
        int totalSheep=avail.getSize();
        while(true)
        {
            System.out.println(LINES+"MINUTE "+minute+LINES);
            if(avail.hasMore() && minute == avail.peekHead().getArrivalTime())
            {
                while(avail.hasMore() && minute == avail.peekHead().getArrivalTime())
                {
                    System.out.println("SHEEP ADDED: "+avail.peekHead().toString());
                    s.addSheep(avail.remove());
                }
                currSheep++;
            }
            if(s.isDone())
            {
                System.out.println("ALL SHEEP COMPLETE");
                break;
            }
            System.out.println("Currently Shearing Sheep: \n"+s.getCurrentSheep());
            schedule=s.advanceOneMinute(schedule);
            minute++;
        }
        System.out.println("The average summed waiting time is: "+s.getAverageWaitingTime()+" minutes");
        return schedule;
    }
    public void print(String[] out)
    {
        for(String i:out)
        {
            System.out.println(i);
        }
    }
    public void print(GenLL<String> out)
    {
        out.reset();
        while(out.hasMore())
        {
            System.out.println(out.getCurrent().toString());
            out.goToNext();
        }
    }
}
