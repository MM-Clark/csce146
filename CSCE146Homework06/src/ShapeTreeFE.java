
/*
 * Written by Michelle Clark
 */
import java.io.File;
import java.io.IOException;
import java.util.*;
//FUN FACT: THERE'S AN EXTRA TAB IN ALL OF THE CIRCLES!

//PRO TIP: use 'rect','circ', and 'righ' or 'tri' to make life easier!
//You can also press enter without entering x to load the default file!
//you will still have to write out 'preorder','inorder','postorder' unfortunately
public class ShapeTreeFE {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final String IN_FILE = "./shapeFile.txt";
    private static final String OUT_FILE = "./output.txt";
    public static LinkedBST<Shape> sBST = new LinkedBST<Shape>();
    public static final ShapeTreeBE game = new ShapeTreeBE();

    public static void main(String[] args) throws Exception {
        boolean run = true;
        while (run) {
            int input = getInput();
            switch (input) {
                case 1:
                    readFromFile();
                    break;
                case 2:
                    printTreeConsoleOpts();
                    break;
                case 3:
                    game.addShape(sBST);
                    break;
                case 4:
                    game.removeShape(sBST);
                    break;
                case 5:
                    searchForShape();
                    break;
                case 6:
                    game.findMaxArea(sBST);
                    break;
                case 7:
                    removeShapesAboveArea();
                    break;
                case 8:
                    printTreeFileOpts();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static int getInput() {
        boolean valid = false;
        int input = -15;
        System.out.println("Welcome to the Shape Tree!");
        while (!valid) {
            boolean isDigit = true;
            boolean validOption = true;
            printOptions();
            String inputS = keyboard.nextLine();
            try {
                input = Integer.parseInt(inputS);
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer of digits");
                isDigit = false;
            }
            if (isDigit && (!(input == 1 || input == 2 || input == 3 || input == 4 || input == 5 || input == 6
                    || input == 7 || input == 8 || input == 0)))
                validOption = false;
            if (isDigit && !validOption)
                System.out.println("input must be 1, 2, 3, 4, 5, 6, 7, 8, 9");
            if (isDigit && validOption)
                valid = true;
        }
        return input;
    }

    public static void printOptions() {
        System.out.println("Enter 1. To Read a Shape Tree from a File.");
        System.out.println("Enter 2. To Print a Tree Traversal to the Console.");
        System.out.println("Enter 3. To Add a Shape.");
        System.out.println("Enter 4. To Remove a Shape.");
        System.out.println("Enter 5. To Search for a Shape.");
        System.out.println("Enter 6. To Find the Shape with the Max Area.");
        System.out.println("Enter 7. To Remove All Shapes Greater than an Area.");
        System.out.println("Enter 8. To Print Shape Tree to File.");
        System.out.println("Enter 0. To Quit.");
    }

    public static void readFromFile() {
        boolean valid = false;
        String inFile = IN_FILE;
        while (!valid) {
            System.out.println("Enter the file's name or x for default");
            inFile = keyboard.nextLine();
            if (inFile.equalsIgnoreCase("x") || inFile.equalsIgnoreCase("")) {
                inFile = IN_FILE;
                valid = true;
                break;
            }
            File f = new File(inFile);
            if (!(f.exists() && f.isFile())) {
                System.out.println("Bad file -- not found!");
                valid = false;
                continue;
            }
            valid = true;
        }
        sBST = ShapeTreeBE.readTree(inFile, sBST);
        sBST.printInorder();
        System.out.println();
    }

    public static void printTreeConsoleOpts() {
        int order = getTraversal();
        game.printTreeFile(sBST, order);
    }

    public static int getTraversal() {
        boolean valid = false;
        int input = -15;
        while (!valid) {
            boolean isDigit = true;
            boolean validOption = true;

            System.out.println("Which traversal?");
            System.out.println("Enter 1. for Pre-order");
            System.out.println("Enter 2. for In-order");
            System.out.println("Enter 3. for Post-order");

            String orderS = keyboard.nextLine();

            try {
                input = Integer.parseInt(orderS);
            } catch (NumberFormatException e) {
                System.out.println("That is not an integer of digits");
                isDigit = false;
            }
            // sure I could combine this with the other method using a for loop possibly but
            // development time
            if (isDigit && (!(input == 1 || input == 2 || input == 3)))
                validOption = false;
            if (isDigit && !validOption)
                System.out.println("input must be 1, 2, or 3");
            if (isDigit && validOption)
                valid = true;
        }
        return input;
    }

    public static void printTreeFileOpts() throws IOException 
    {
        boolean valid = false;
        String fileName = OUT_FILE;
        while (!valid) 
        {
            System.out.println("Enter the file's name or x for default");
            fileName = keyboard.nextLine();
            if (fileName.equalsIgnoreCase("x") || fileName.equalsIgnoreCase("")) {
                fileName = OUT_FILE;
                valid = true;
                break;
            }
            File f = new File(fileName);
            f.createNewFile();
            valid = true;
        }

        String printType = getOrder();

        // printing to file
        game.print(fileName, sBST, printType);
    }

    public static String getOrder() {
        boolean valid = false;
        String printType = "";
        while (!valid) 
        {
            System.out.println("Enter whether to print preorder, inorder, or postorder");
            printType = keyboard.nextLine();

            if (printType.equalsIgnoreCase("preorder") || printType.equalsIgnoreCase("inorder")
                    || printType.equalsIgnoreCase("postorder"))
                valid = true;
            else
                System.out.println("Choose one of these exactly: preorder, inorder, postorder");
        }
        return printType;
    }

    public static void searchForShape() throws Exception 
    {
        boolean isValidName = false;
        String name = "none";
        while(!isValidName)
        {
            System.out.println("Enter the shape name");
            name = keyboard.nextLine();
            if(!(name.toUpperCase().contains("RECT")||name.toUpperCase().contains("CIRC")||
                name.toUpperCase().contains("RIGH")||name.toUpperCase().contains("TRI")))
            {
                System.out.println("- bad name, try again - ");
                isValidName = false;
            }
            else
                isValidName = true;
        }
        boolean ret = game.searchForShape(sBST, name);
        System.out.println("Found that guy: " + ret + "\n");
    }

    public static void removeShapesAboveArea() 
    {
        boolean validDouble = false;
        String maxAreaS="100000.0";
        Double maxArea = 100000.0; //trying to initialize to a ceiling value
        while(!validDouble)
        {
            System.out.println("Enter the maximum area");
            maxAreaS = keyboard.nextLine();
            try{
                maxArea = Double.parseDouble(maxAreaS);
                validDouble=true;
                if(maxArea<=0)
                {
                    System.out.println("Area must be above 0!");
                    validDouble = false;
                }
            }catch(NumberFormatException e)
            {
                System.out.println("Enter a decimal value for max area");
                validDouble = false;
            }
        }
        maxArea = Double.parseDouble(maxAreaS);
        game.removeShapesAboveArea(sBST, maxArea);
    }
}
