/*
 * Written by Michelle Clark
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//This is the basis for a hash table
public class TaskOrgFE 
{
    private static final String IN_FILE_NAME = "./TasksFile.txt";
    private static final String OUT_FILE_NAME = "./Results.txt";
    private static final String DELIM = "\t";
    private static final int COLS = 2;
    private static final int DEF_SIZE = 5;
    private static TOLinkedList<Task>[] organizedTasks;
    private static final Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) throws Exception 
    {
        boolean run=true;
        init();
        while(run)
        {
            int input = getData("s");

            switch(input)
            {
                case 1:
                    organizedTasks=addTask(organizedTasks);
                    //adding this to each case automatically shows the user their changes to make it easier to see updates implemented
                    printTasks(organizedTasks);
                    break;
                case 2:
                    organizedTasks=removeTask(organizedTasks);
                    printTasks(organizedTasks);
                    break;
                case 3: 
                    printTasks(organizedTasks);
                    break;
                case 4:
                    organizedTasks=readTaskFile(IN_FILE_NAME,organizedTasks);
                    printTasks(organizedTasks);
                    break;
                case 5: 
                    writeTaskFile(OUT_FILE_NAME,organizedTasks);
                    break;
                case 9: 
                    run=false;
            }
        }
        System.out.println("bye");
        keyboard.close();
    }
    public static void init()
    {
        //constructing the array of linked lists of tasks 
        organizedTasks = new TOLinkedList[DEF_SIZE];
        for(int i=0;i<organizedTasks.length;i++)
        {
            organizedTasks[i] = new TOLinkedList<Task>();
        }
    }
    public static int getData(String type)
    {
        boolean valid = false;
        int data = -3;
        if(type.equalsIgnoreCase("s")||type.equalsIgnoreCase("start"))
        {
            while(!valid)
            {
                boolean isDigit = true;
                System.out.println("Welcome to the task organizer!");
                System.out.println();
                System.out.println("Enter 1. To Add a Task");
                System.out.println("Enter 2. To Remove a Task");
                System.out.println("Enter 3. To Print Tasks To Console");
                System.out.println("Enter 4. To Read From a Task File");
                System.out.println("Enter 5. To Write To a Task File");
                System.out.println("Enter 9. To Quit");

                String inputS = keyboard.nextLine();
                //this boolean set checks first if the input is an integer value
                try
                {
                    data = Integer.parseInt(inputS);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("\n~input must be an integer value~\n");
                    isDigit = false;
                }
                //assuming checks have passed, isDigit will be true allowing for the secondary check of if the input is a valid number in range
                //if so, we can jump out of while loop
                if(isDigit && (data == 1 || data == 2 || data == 3 || data == 4 || data == 5 || data == 9))
                    valid = true;
                //assuming the user entered a number out of range, but it wasn't a word
                else if(isDigit)   
                    System.out.println("\n~entry must be 1, 2, 3, 4, 5, or 9\n");
            }
        }
        if(type.equalsIgnoreCase("p")||type.equalsIgnoreCase("priority"))
        {
            while(!valid)
            {
                boolean isDigit = true;
                System.out.println("Enter the task's priority");
                String priorityString = keyboard.nextLine();
                try
                {
                    data = Integer.parseInt(priorityString);
                }
                catch(NumberFormatException e)
                {
                    System.out.println("\n~priorities must be an integer value~\n");
                    isDigit = false;
                }
                if(isDigit && (data == 0 || data == 1 || data == 2 || data == 3 || data == 4))
                    valid = true;
                else if(isDigit)
                    System.out.println("\n+~priorities must be 0, 1, 2, 3, 4+\n");
            }
        }
        return data;
    }
    public static TOLinkedList<Task>[] addTask(TOLinkedList<Task>[] organizedTasks)
    {
        int priority = getData("p");

        System.out.println("Enter the task's action");
        String action = keyboard.nextLine();

        Task add = new Task(action,priority);
        //need to check if the priority is already contained
        if(!(organizedTasks[priority].contains(add)))
        {
            organizedTasks[priority].add(add);
            printToConsole("added the task!");
        }
        else 
        {   
            System.out.println("There was a duplicate task, so your task was not added...");
        }

        return organizedTasks;
    }
    public static TOLinkedList<Task>[] removeTask(TOLinkedList<Task>[] organizedTasks)
    {
        boolean isDigit=false;
        //initialized to negative number in case I ever wanted to implement a check to ensure data was entered
        //not really necessary here because I force the user to enter digit
        int priority=-2;
        while(!isDigit)
        {
            System.out.println("Enter the task's priority");
            String priorityS = keyboard.nextLine();
            //try catch forces the user to use an integer here
            try
            {
                priority = Integer.parseInt(priorityS);
                if(!(priority == 0 || priority == 1 || priority == 2 || priority == 3 || priority == 4))
                {
                    System.out.println("~ so priorities need to be 0, 1, 2, 3, or 4 ~");
                    isDigit=false;
                    continue;
                }
                isDigit=true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("~ so priorities need to be integer whole number digits ~");
                isDigit=false;
            }
        }

        System.out.println("Enter the task's action");
        String action = keyboard.nextLine();

        Task remove = new Task(action,priority);
        if(organizedTasks[priority].contains(remove))
        {
            organizedTasks[priority].removeAction(remove);
            printToConsole("removed the task!");
        }
        else
            System.out.println("So the task wasn't found...");

        return organizedTasks;
    }
    public static void printTasks(TOLinkedList<Task>[] organizedTasks)
    {
        for(int i=0;i<organizedTasks.length;i++)
        {
            organizedTasks[i].print(i);
        }
    }
    public static TOLinkedList<Task>[] readTaskFile(String IN_FILE_NAME,TOLinkedList<Task>[] organizedTasks)
    {
        boolean validFile = false;
        String inFileName = null;
        while(!validFile)
        {
            System.out.println("Enter the file name or x for default");
            inFileName = keyboard.nextLine();
            if(inFileName.equalsIgnoreCase("x")||inFileName.equalsIgnoreCase(""))
            {
                inFileName = IN_FILE_NAME;
                validFile=true;
                continue;
            }
            //indicates we did not continue, so have to check for file validity
            //letter plus .txt is of length 5 for instance a.txt

            //OMMITTED CODE BECAUSE THE TASK FILE COULD POTENTIALLY BE A NON TXT FILE AND WOULD STILL BE 
            //CHECKED FOR EXISTENCE BELOW

            // if(!(inFileName.length()>4 && inFileName.substring(inFileName.length()-4,inFileName.length()).equals(".txt")))
            // {
            //     System.out.println("filename must include .txt");
            //     validFile=false;
            //     continue;
            // }
            File f = new File(inFileName);
            if(!(f.exists() && f.isFile()))
            {
                System.out.println("~ cannot find file ~");
                validFile=false;
                continue;
            }
            //indicates we made it to the end and the file should be existent
            validFile=true;
        }
        organizedTasks = readFile(inFileName, organizedTasks);
        printToConsole("Read file!");
        return organizedTasks;
    }
    public static TOLinkedList<Task>[] readFile(String inFile,TOLinkedList<Task>[] organizedTasks)
    {
        //first we must delete everything in the linked list; I use a separate integer to lower time/computation
        //by finding length once
        int tasksLength = organizedTasks.length;
        for(int i=0;i<tasksLength;i++)
            organizedTasks[i].deleteList();
        //now we can add to list
        try
        {
            Scanner fileScanner = new Scanner(new File(inFile));
            while(fileScanner.hasNext())
            {
                //stores the next word
                String next = fileScanner.nextLine();
                
                if(next!="")
                {
                    //splits the line 
                    String[] info = next.split(DELIM);
                    if(info.length == COLS)
                    {
                        int priority = Integer.parseInt(info[0]);
                        String action = info[1];
                        Task temp = new Task(action, priority);
                        //checks for repeat values
                        if(!(organizedTasks[priority].contains(temp)))
                            organizedTasks[priority].add(temp);
                    }
                }
            }
            
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return organizedTasks;
    }
    public static void writeTaskFile(String OUT_FILE_NAME,TOLinkedList<Task>[] organizedTasks)
    {
        printToConsole("Enter the file name or x for default");
        String outFileName = keyboard.nextLine();
        if(outFileName.equalsIgnoreCase("x"))
            outFileName = OUT_FILE_NAME;
        writeFile(outFileName, organizedTasks);
    }
    //EXPERIENCING PROBLEMS 
    public static void writeFile(String outFile, TOLinkedList<Task>[] organizedTasks)
    {
        //create the out file if it does not exist
        File destination = new File(outFile);
        if(!destination.exists())
        {
            try 
            {
                destination.createNewFile();
                System.out.println("~ new file created ~");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else //assumes the destination exists
        {
            System.out.println("~ file exists ~");
        }
        //length of the entire list (which is the number of linked lists)
        int maxOfWhole = organizedTasks.length;
        try
        {
            //setting up the output printwriter
            PrintWriter output = new PrintWriter(new FileOutputStream(outFile));
            //using a double for loop to access data
            for(int i=0;i<maxOfWhole;i++)
            {
                //length of the linked list at the index 
                int maxOfIndex = organizedTasks[i].getSize();
                for(int j=0;j<maxOfIndex;j++)
                {
                    Task tempT = new Task();
                    tempT = organizedTasks[i].getAtIndex(j);
                    output.println(tempT.toStringFile());
                }
            }
            output.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
        System.out.println("Written to task file: "+outFile);
    }
    public static void printToConsole(String input)
    {
        System.out.println(input);
    }
}
