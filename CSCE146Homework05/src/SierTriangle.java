/*
 * Written by Michelle Clark
 */
import java.awt.*;
import javax.swing.*;

public class SierTriangle extends Canvas
{
    public static final int SIZE = 700;
    public static void main(String[] args) throws Exception 
    {
        JFrame frame = new JFrame("Sierspenski's Triangle");
        frame.setSize(SIZE,SIZE);
        SierTriangle st = new SierTriangle();
        frame.add(st);
        frame.setVisible(true);
        //set to exit on close to not make the awful can't exit pop-up adds (-cough cough McAfee-)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.CYAN);
    }
    public void paint(Graphics g)
    {
        //the height of the triangle is the rounded version of the square root of 3 multiplied by 2 bc of
        //30-60-90 triangles
        int triangleHeight=(int)Math.round(SIZE*Math.sqrt(3.0)/2.0);
        //notic that we round the triangle height to make it compatible with point
        Point left = new Point(0, triangleHeight);
        Point top = new Point(SIZE / 2, 0);
        Point right = new Point(SIZE, triangleHeight);
        int l = triangleHeight;
        drawTriangle(l,top,left,right,g);
    }
    public void drawTriangle(int height, Point left, Point top,Point right,Graphics g)
    {
        g.setColor(Color.MAGENTA);
        //this means that the pixel limit of 4 has been reached
        if(height==4)
        {
            //base case
            Polygon p = new Polygon();
            p.addPoint(left.x, left.y);
            p.addPoint(top.x, top.y);
            p.addPoint(right.x, right.y);
            g.fillPolygon(p);
        }
        else
        {
            //split into three triangles with recursion; finding the midpoint of each side
            Point leftSideMidpoint = midpoint(left, top);
            Point rightSideMidpoint = midpoint(top, right);
            Point bottomSideMidpoint = midpoint(left, right);

            //draw three small triangles
            drawTriangle(height/2,left, leftSideMidpoint, bottomSideMidpoint, g);
            drawTriangle(height/2,leftSideMidpoint, top, rightSideMidpoint,g);
            drawTriangle(height/2,bottomSideMidpoint,rightSideMidpoint,right,g);
        }
    }
    public static Point midpoint(Point p1, Point p2)
    {
        //uses the midpoint formula to find the midpoint 
        return new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);
    }
}
