/*
 * Written by Michelle Clark
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ShapeTreeBE 
{
    //shape extends comparable issue
    
    private static final String DELIM = "\t";
    //separating out based on whether there are three parameters or two
    //because right triangles and rectangles have 3 parameters while circles have 2
    private static final int R_COLS = 3;
    private static final int C_COLS = 2;
    public static final Scanner keyboard = new Scanner(System.in);

    //reading the tree
    public static LinkedBST<Shape> readTree(String readFile, LinkedBST<Shape> game)
    {
        int tracker = 1;//starts at one because that's the first line of file
        try
        {
            Scanner fileScanner = new Scanner(new File(readFile));
            
            while(fileScanner.hasNext())
            {
                //stores the next word
                String next = fileScanner.nextLine();
                //checks for words that don't end with new line
                if(next.endsWith("\t")||next.endsWith("\s"))
                {
                    tracker++;
                    continue;
                }
                String[] parse = next.split(DELIM);
                //checks for correct formatting of commands
                if(parse.length==R_COLS)
                {
                    String name = parse[0];
                    double param1 = Double.parseDouble(parse[1]);
                    double param2 = Double.parseDouble(parse[2]);
                    
                    if(name.equalsIgnoreCase("rectangle"))
                    {
                        Rectangle temp = new Rectangle(name, param1, param2);
                        //TODO: figure out how to not have to call to set area
                        // temp.setArea();
                        if(!(game.search(temp)))
                            game.add(temp);
                    }
                    else if(name.equalsIgnoreCase("right triangle"))
                    {
                        RightTriangle temp2 = new RightTriangle(name, param1, param2);
                        // temp2.setArea();
                        if(!(game.search(temp2)))
                            game.add(temp2);
                    }
                    else
                    {
                        System.out.println("invalid sequence at line "+tracker+": "+next);
                    }
                }
                else if(parse.length==C_COLS)
                {
                    String name = parse[0];
                    double param = Double.parseDouble(parse[1]);
                    
                    if(name.equalsIgnoreCase("circle"))
                    {
                        Circle temp = new Circle(name, param);
                        // temp.setArea();
                        if(!(game.search(temp)))
                            game.add(temp);
                    }
                    else
                    {
                        System.out.println("invalid sequence at line "+tracker+": "+next);
                    }
                }
                else
                {
                    System.out.println("invalid sequence at line "+tracker+": "+next);
                }
                tracker++;
            }
            
            fileScanner.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Printing after reading In-order"+"\n");
        return game;
    }

    //adding a shape
    public void addShape(LinkedBST<Shape> game)
    {
        boolean validShape = true;
        boolean exists = false;
        boolean reLength = false;
        boolean reWidth = false;
        boolean cRad = false;
        boolean riBase = false;
        boolean riHeight = false;
        do
        {
            System.out.println("What type of shape would you like to add?");
            String shape = keyboard.nextLine();
            if(shape.toLowerCase().contains("rect"))
            {
                //reformatting the name
                shape = "Rectangle";
                String lengthS="none";
                String widthS="none";
                while(!reLength)
                {
                    System.out.println("What length should the rectangle be?");
                    lengthS = keyboard.nextLine();
                    try
                    {
                        Double length = Double.parseDouble(lengthS);
                        reLength = true;
                        if(length<=0)
                        {
                            System.out.println("Need to enter length greater than zero!");
                            reLength = false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Enter decimal numbers");
                        //little redundant but let's be cautious
                        reLength = false;
                    }
                }

                Double length = Double.parseDouble(lengthS);
                while(!reWidth)
                {
                    System.out.println("What width should the rectangle be?");
                    widthS = keyboard.nextLine();
                    try
                    {
                        Double width = Double.parseDouble(widthS);
                        reWidth = true;
                        if(width<=0)
                        {
                            System.out.println("Need to enter width greater than zero!");
                            reWidth = false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Enter decimal numbers");
                        reWidth = false;
                    }
                }

                Double width = Double.parseDouble(widthS);

                Rectangle add = new Rectangle(shape, length, width);
                exists = game.search(add);
                if(!exists)
                    game.add(add);
                validShape=true;
            }

            else if(shape.toLowerCase().contains("circ"))
            {
                shape = "Circle";
                String radiusS = "none";
                while(!cRad)
                {
                    System.out.println("What radius should the circle be?");
                    radiusS = keyboard.nextLine();
                    try
                    {
                        Double radius = Double.parseDouble(radiusS);
                        cRad = true;
                        if(radius<=0)
                        {
                            System.out.println("Need to enter radius greater than zero!");
                            cRad = false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Enter numbers dudeee");
                        cRad = false;
                    }
                }

                Double radius = Double.parseDouble(radiusS);

                Circle add = new Circle(shape, radius);
                exists = game.search(add);
                if(!exists)
                    game.add(add);
                validShape=true;
            }
            //maybe the user accidentally types right OR triangle instead of both
            else if(shape.toLowerCase().contains("righ")||shape.toLowerCase().contains("tri"))
            {
                shape = "Right Triangle";
                String baseS = "none";
                String heightS = "none";
                while(!riBase)
                {
                    System.out.println("What base length should the triangle be?");
                    baseS = keyboard.nextLine();
                    try
                    {
                        Double base = Double.parseDouble(baseS);
                        riBase = true;
                        if(base<=0)
                        {
                            System.out.println("Need to enter base greater than zero!");
                            riBase = false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Enter decimal numbers");
                        riBase = false;
                    }
                }

                Double base = Double.parseDouble(baseS);

                while(!riHeight)
                {
                    System.out.println("What height should the triangle be?");
                    heightS = keyboard.nextLine();
                    try
                    {
                        Double height = Double.parseDouble(heightS);
                        riHeight = true;
                        if(height<=0)
                        {
                            System.out.println("Need to enter height greater than zero!");
                            riHeight=false;
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Enter decimal numbers");
                        riHeight = false;
                    }
                }

                Double height = Double.parseDouble(heightS);

                RightTriangle add = new RightTriangle(shape, base, height);
                exists = game.search(add);
                if(!exists)
                    game.add(add);
                validShape = true;
            }

            else
            {
                validShape = false;
                System.out.println("The input should be 'rectangle', 'circle', or ' right triangle', ignoring case");
            }
        } while(!validShape);

        if(exists)
            System.out.println("THE SHAPE ALREADY EXISTS AND WAS NOT RE-ADDED");
        else
        {
            System.out.println("~ Added the shape! ~");
            System.out.println("printing in order ");
            game.printInorder();
        }
    }

    //removing a shape
    public void removeShape(LinkedBST<Shape> game)
    {
        System.out.println("What type of shape would you like to remove?");
        String shape = keyboard.nextLine();
        boolean removed = false;

        if(shape.toLowerCase().contains("rect"))
        {
            boolean validL = false;
            boolean validW = false;
            String lengthS = "none";
            String widthS = "none";
            //formatting name correctly
            shape = "Rectangle";
            while(!validL)
            {
                System.out.println("What length was the rectangle?");
                lengthS = keyboard.nextLine();
                try
                {
                    Double length = Double.parseDouble(lengthS);
                    validL = true;
                    if(length<=0)
                    {
                        System.out.println("Need to enter length greater than zero!");
                        validL=false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter valid decimals");
                    validL=false;
                }
            }
            Double length = Double.parseDouble(lengthS);

            while(!validW)
            {
                System.out.println("What width was the rectangle?");
                widthS = keyboard.nextLine();
                try
                {
                    Double width = Double.parseDouble(widthS);
                    validW = true;
                    if(width<=0)
                    {
                        System.out.println("Need to enter width greater than zero!");
                        validW = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter valid decimals");
                    validW = false;
                }
            }
            Double width = Double.parseDouble(widthS);

            Rectangle subtract = new Rectangle(shape, length, width);
            removed = game.search(subtract);
            if(removed)
                game.remove(subtract);
        }

        else if(shape.toLowerCase().contains("circ"))
        {
            boolean validRad = false;
            String radiusS = "none";
            shape = "Circle";

            while(!validRad)
            {
                System.out.println("What radius was the circle?");
                radiusS = keyboard.nextLine();
                try
                {
                    Double radius = Double.parseDouble(radiusS);
                    validRad = true;
                    if(radius <= 0)
                    {
                        System.out.println("Need to enter radius greater than zero!");
                        validRad = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter valid decimals");
                    validRad = false;
                }
            }
            Double radius = Double.parseDouble(radiusS);

            Circle subtract = new Circle(shape, radius);
            removed = game.search(subtract);
            if(removed)
                game.remove(subtract);
        }

        else if(shape.toLowerCase().contains("righ")||shape.toLowerCase().contains("tri"))
        {
            boolean validBase = false;
            boolean validHeight = false;
            String baseS = "none";
            String heightS = "none";
            shape = "Right Triangle";

            while(!validBase)
            {
                System.out.println("What base length was the triangle?");
                baseS = keyboard.nextLine();
                try
                {
                    Double base = Double.parseDouble(baseS);
                    validBase = true;
                    if(base<=0)
                    {
                        System.out.println("Need to enter base greater than zero!");
                        validBase = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter valid decimals");
                    validBase = false;
                }
            }
            Double base = Double.parseDouble(baseS);

            while(!validHeight)
            {
                System.out.println("What height was the triangle?");
                heightS = keyboard.nextLine();
                try
                {
                    Double height = Double.parseDouble(heightS);
                    validHeight = true;
                    if(height<=0)
                    {
                        System.out.println("Need to enter height greater than zero!");
                        validHeight = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter valid decimals");
                    validHeight = false;
                }
            }
            Double height = Double.parseDouble(heightS);

            RightTriangle subtract = new RightTriangle(shape, base, height);
            removed = game.search(subtract);
            if(removed)
                game.remove(subtract);
        }
        
        if(removed)
        {
            System.out.println("printing in order ");
            game.printInorder();
        }
        else
        {
            System.out.println("ELEMENT NEVER FOUND");
        }
    }
    
    //finding the maximum area
    public void findMaxArea(LinkedBST<Shape> game) throws Exception
    {
        System.out.println("Finding the shape with the max area");
        Shape ret = game.findMaxInTree();
        System.out.print("The shape with the max area is...");
        if(ret instanceof Rectangle)
        {
            Rectangle rect = (Rectangle)ret;
            System.out.print(rect.toString()+"\n");
        }
        else if(ret instanceof Circle)
        {
            Circle circ = (Circle)ret;
            System.out.print(circ.toString()+"\n");
        }
        else if(ret instanceof RightTriangle)
        {
            RightTriangle right = (RightTriangle)ret;
            System.out.print(right.toString()+"\n");
        }
        else
        {
            throw new Exception("uh oh something went very wrong");
        }
        System.out.println();
    }

    //removing shapes above a certain area
    public void removeShapesAboveArea(LinkedBST<Shape> game, double maxArea)
    {
        System.out.println("Removing the shapes above the area");
        game.removeAboveArea(maxArea);
        System.out.println("printing in order ");
        game.printInorder();
    }

    //printing to file
    public void printTreeFile(LinkedBST<Shape> game, int order)
    {
        switch(order)
        {
            case 1:
                game.printPreorder();
                System.out.println();
                break;
            case 2:
                game.printInorder();
                System.out.println();
                break;
            case 3: 
                game.printPostorder();
                System.out.println();
                break;
            default:
                System.out.println("Not an option");
        }
    }
    public void print(String fileName, LinkedBST<Shape> game, String typePrint) throws IOException
    {
        if(typePrint.toLowerCase().contains("pre"))
            game.writeTreePreorder(fileName);
        else if(typePrint.toLowerCase().contains("in"))
            game.writeTreeInorder(fileName);
        else if(typePrint.toLowerCase().contains("post"))
            game.writeTreePostorder(fileName);
    }

    //finding a shape
    public boolean searchForShape(LinkedBST<Shape> game, String name) throws Exception
    {
        if(name.toLowerCase().contains("rect"))
        {
            boolean validLength = false;
            boolean validWidth = false;
            String lengthS="none";
            String widthS="none";

            name = "Rectangle";

            while(!validLength)
            {
                System.out.println("Enter the length");
                lengthS = keyboard.nextLine();
                try
                {
                    double length = Double.parseDouble(lengthS);
                    validLength = true;
                    if(length<=0)
                    {
                        System.out.println("Need to enter length greater than zero!");
                        validLength = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter a valid decimal value for length");
                    validLength = false;
                }
            }
            double length = Double.parseDouble(lengthS);

            while(!validWidth)
            {
                System.out.println("Enter the width");
                widthS = keyboard.nextLine();
                try
                {
                    double width = Double.parseDouble(widthS);
                    validWidth = true;
                    if(width<=0)
                    {
                        System.out.println("Need to enter length greater than zero!");
                        validWidth=false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter a valid decimal value for width");
                    validWidth = false;
                }
            }
            double width = Double.parseDouble(widthS);

            Rectangle temp = new Rectangle(name, length, width);
            boolean ret = game.search(temp);
            return ret;
        }
        else if(name.toLowerCase().contains("circ"))
        {
            boolean validRad=false;
            String radiusS = "none";
            name = "Circle";

            while(!validRad)
            {
                System.out.println("Enter the radius");
                radiusS = keyboard.nextLine();
                try
                {
                    double radius = Double.parseDouble(radiusS);
                    validRad = true;
                    if(radius<=0)
                    {
                        System.out.println("Need to enter radius greater than zero!");
                        validRad = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter a valid decimal radius");
                    validRad = false;
                }
            }
            double radius = Double.parseDouble(radiusS);

            Circle temp = new Circle(name, radius);
            boolean ret = game.search(temp);
            return ret;
        }
        else if(name.toLowerCase().contains("righ")||name.toLowerCase().contains("tri"))
        {
            boolean validBase=false;
            boolean validHeight=false;
            String baseS = "none";
            String heightS = "none";
            name = "Right Triangle";

            while(!validBase)
            {
                System.out.println("Enter the base");
                baseS = keyboard.nextLine();
                try
                {
                    double base = Double.parseDouble(baseS);
                    validBase = true;
                    if(base<=0)
                    {
                        System.out.println("Need to enter base greater than zero!");
                        validBase = false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter a valid decimal base");
                    validBase = false;
                }
            }
            double base = Double.parseDouble(baseS);

            while(!validHeight)
            {
                System.out.println("Enter the height");
                heightS = keyboard.nextLine();
                try
                {
                    double height = Double.parseDouble(heightS);
                    validHeight=true;
                    if(height<=0)
                    {
                        System.out.println("Need to enter height greater than zero!");
                        validHeight=false;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.println("Enter a valid decimal height");
                    validHeight = false;
                }
            }
            double height = Double.parseDouble(heightS);

            RightTriangle temp = new RightTriangle(name, base, height);
            boolean ret = game.search(temp);
            return ret;
        }
        else
        {
            throw new Exception("~ input doesn't match a shape type ~");
        }
    }
}
