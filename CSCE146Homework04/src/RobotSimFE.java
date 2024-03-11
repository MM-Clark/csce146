/*
 * Written by Michelle Clark
 */

import java.awt.*;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;

public class RobotSimFE extends JFrame
{
    public static Scanner keyboard = new Scanner(System.in);
    public static RobotSim game = new RobotSim();
    public static final String BOARD_DEFAULT = "./board.txt";
    public static final String COMMANDS_DEFAULT = "./robotCommands.txt";
    public static JFrame f;
    public static JLabel welcome;
    public static JLabel keepOpen;
    public static JLabel oops;
    
    public static void main(String[] args) throws Exception 
    {
        display();
        boolean run = true;
        while(run)
        {
            
            boolean runRound=true;
            while(runRound)
            {
                String board = initBoard();
                String commands = initCommands();
                
                runRound = game.init(board, commands);
            }
            System.out.println("Play again? Enter y/n.");
            String again = keyboard.nextLine();

            if(again.contains("n"))
                run=false;
            else if(again.contains("y"))
                run=true;
            else
                throw new Exception("Follow instructions bud.");
        }
        System.out.println("night");
        //hide window
        f.setVisible(false);
        //close window
        f.dispose();
        //stop program
        System.exit(0);
        keyboard.close();
    }
    public static String initBoard()
    {
        boolean valid = false;
        //initialized to default value
        String board=BOARD_DEFAULT;
        //error checks the user to ensure they input proper data
        while(!valid)
        {
            System.out.println("Enter file for the Board or x for default");
            board = keyboard.nextLine();
            if(board.equalsIgnoreCase("x")||board.equalsIgnoreCase(""))
            {
                board = BOARD_DEFAULT;
                valid = true;
                //sends us immediately out of while loop 
                break;
            }
            //must check length of board greater than 4 to ensure will be able to physically contain ".txt"
            if(!(board.length()>4 && (board.substring(board.length()-4,board.length()).equals(".txt"))))
            {
                valid = false;
                System.out.println("The board file needs to contain '.txt'");
                //sends us out of current iteration of while loop
                continue;
            }
            File f = new File(board);
            if(!(f.exists() && f.isFile()))
            {
                valid = false;
                System.out.println("The board file does not exist");
                //sends us out of current iteration of while loop
                continue;
            }
            valid = true;
        }
        return board;
    }
    public static String initCommands()
    {
        boolean valid = false;
        String commands=COMMANDS_DEFAULT;
        while(!valid)
        {
            System.out.println("Enter file for the Commands or x for default");
            commands = keyboard.nextLine();
            if(commands.equalsIgnoreCase("x")||commands.equalsIgnoreCase(""))
            {
                commands = COMMANDS_DEFAULT;
                valid = true;
                //sends us immediately out of while loop 
                break;
            }
            //still under assumption that valid file must be txt file
            if(!(commands.length()>4 && commands.substring(commands.length()-4,commands.length()).equals(".txt")))
            {
                valid = false;
                System.out.println("The commands file needs to contain '.txt'");
                //sends us out of current iteration of while loop
                continue;
            }
            File f = new File(commands);
            if(!(f.exists() && f.isFile()))
            {
                valid = false;
                System.out.println("The commands file does not exist");
                //sends us out of current iteration of while loop
                continue;
            }
            valid = true;
        }
        return commands;
    }
    public static void display()
    {
        //frame to store text field
        f = new JFrame("RobotSimulator");
        
        //label for text display
        welcome = new JLabel("Welcome to the Robot Simulator!");
        keepOpen = new JLabel("Keep me open or the program won't work!");
        oops = new JLabel("Oh wait I'm set to not be closable nevermind");

        JPanel p = new JPanel();
        p.add(welcome);
        p.add(keepOpen);
        p.add(oops);
        p.setBackground(Color.cyan);
        f.add(p);
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(true);
    }
}
