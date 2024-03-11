/*
 * Written by Michelle Clark
 */
import java.io.File;
import java.util.Scanner;
public class RobotSim 
{
    public static final String DELIM = " ";
    public static final int COLS = 2;
    public static final int MAZE_HEIGHT = 10;
    public static final int MAZE_WIDTH = 10;

    public static final char ROBOT = '@';
    public static final char EMPTY = '_';
    public static final char OBST = 'X';
    public static final char PATH = '#';

    public static final String NORTH = "Up";
    public static final String SOUTH = "Down";
    public static final String WEST = "Left";
    public static final String EAST = "Right";

    private static char[][] maze;
    private int[] currLoc; 
    
    public boolean init(String board, String commands)
    {
        //creating empty queue
        QueueI<String> q;
        q = new LLQueue();
        //creating empty maze
        maze = new char[MAZE_HEIGHT][MAZE_WIDTH];
        //setting the maze up
        maze = loadBoard(board, maze);
        //setting the top left character to robot
        maze[0][0] = ROBOT;
        //setting the queue up
        q = loadCommands(commands, q);
        currLoc = new int[] {0,0};
        printFullMaze(maze);
        System.out.println();
        boolean over = move(q);
        return over;
    }

    public void reset(String board, String commands)
    {
        this.init(board, commands);
    }

    public char[][] loadBoard(String readFile, char[][] maze)
    {
        int y = 0;
        //here I want the board to be stored as a char array to print to the screen
        try
        {
            Scanner fileScanner = new Scanner(new File(readFile));
            
            while(fileScanner.hasNextLine())
            {
                //stores the next line
                String next = fileScanner.nextLine();
                for(int i=0;i<next.length();i++)
                {
                    maze[y][i]=next.charAt(i);
                }
                y++;
            }
            
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return maze;
    }

    public QueueI<String> loadCommands(String readFile, QueueI<String> q)
    {
        try
        {
            Scanner fileScanner = new Scanner(new File(readFile));
            
            while(fileScanner.hasNext())
            {
                //stores the next word
                String next = fileScanner.nextLine();
                //wouldn't necessarily have to parse but that's how we did it
                String[] parse = next.split(DELIM);
                //checks for correct formatting of commands
                if(parse.length==COLS)
                {
                    String temp = parse[1];
                    //checking that the string is a concatenation of "Move" and valid direction
                    if(parse[0].equalsIgnoreCase("Move") && isValidCommand(temp))
                        q.enqueue(temp);
                }
            }
            
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return q;
    }

    public boolean isValidCommand(String s)
    {
        if(s.equalsIgnoreCase("Up")||s.equalsIgnoreCase("Down")||s.equalsIgnoreCase("Left")||s.equalsIgnoreCase("Right"))
            return true;
        else 
            return false;
    }
    
    public boolean move(QueueI<String> q)
    {
        int size = q.getSize();
        String input;
        int currY;
        int currX;
        //using a for loop to go through the queue
        
        //if we haven't crashed, we are safe and the program continues iterating through the for loop
        boolean safe = true;

        System.out.println("Simulation begin");
        while(safe)
        {
            for(int i=0;i<size;i++)
            {
                System.out.println("Command "+i);
                //setting the current location to '_'
                maze[currLoc[0]][currLoc[1]] = EMPTY;
                currY = currLoc[0];
                currX = currLoc[1];
                //don't reref
                int[] copyLoc = {currLoc[0],currLoc[1]};
                input = q.dequeue();
                
                if(input.equalsIgnoreCase(NORTH))
                {
                    System.out.println("Direction: "+Directions.UP);
                    if(isValid(currY-1)&&maze[currY-1][currX] != OBST)
                    {
                        //move the y value up by one
                        currLoc[0]--;
                    }
                    else
                    {
                        System.out.println("CRASH");
                        safe=false;
                        break;
                    }
                }

                else if(input.equalsIgnoreCase(SOUTH))
                {
                    System.out.println("Direction: "+Directions.DOWN);
                    if(isValid(currY+1)&&maze[currY+1][currX] != OBST)
                    {
                        //move down the y 
                        currLoc[0]++;
                    }
                    else
                    {
                        System.out.println("CRASH");
                        //terminates the loop immediately if crashes
                        safe=false;
                        break;
                    }
                }

                else if(input.equalsIgnoreCase(WEST))
                {
                    System.out.println("Direction: "+Directions.LEFT);
                    if(isValid(currX-1)&&maze[currY][currX-1] != OBST)
                    {
                        currLoc[1]--;
                    }
                    else
                    {
                        System.out.println("CRASH");
                        safe=false;
                        break;
                    }
                }

                else if(input.equalsIgnoreCase(EAST))
                {
                    System.out.println("Direction: "+Directions.RIGHT);
                    if(isValid(currX+1)&&maze[currY][currX+1] != OBST)
                    {
                        currLoc[1]++;
                    }
                    else
                    {
                        System.out.println("CRASH");
                        safe=false;
                        break;
                    }
                }

                else
                {
                    System.out.println("CRASH");
                    safe=false;
                    break;
                }

                //move the players piece by resetting current loc to player piece
                maze[currLoc[0]][currLoc[1]] = ROBOT; 

                //print the maze each time we move
                printFullMaze(maze);
                System.out.println();
            }
            safe=false;
        }
        return safe;
    }

    private boolean isValid(int index)
    {
        return index >= 0 && index < maze.length;
    }

    public boolean getWin()
    {
        return currLoc[0] == maze.length-1 && currLoc[1] == maze.length-1;
    }

    public void printFullMaze(char[][] maze)
    {
        for(int i=0;i<maze.length;i++)
        {
            for(int j=0;j<maze.length;j++)
            {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}

