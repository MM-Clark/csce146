/*
 * Written by Michelle Clark
 */
import java.awt.*;
//I never use this class but stored it here anyway
public class Robot 
{
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRoundRect(200,100,120,100,20,20);

        g.setColor(Color.cyan);
        g.fillRoundRect(210,110,100,80,20,20);
        g.fillOval(205,105,7,7);
        g.fillOval(307,105,7,7);
        g.fillOval(203,185,7,7);
        g.fillOval(309,185,7,7);

        g.setColor(Color.blue);
        g.fillOval(212,120,45,45);
        g.fillOval(260,120,45,45);

        g.setColor(Color.white);
        g.fillOval(217,125,35,35);
        g.fillOval(265,125,35,35);

        g.setColor(Color.black);
        g.fillOval(225,132,20,20);
        g.fillOval(273,132,20,20);

        g.setColor(Color.orange);
        g.fillRoundRect(235,168,50,18,20,20);

        g.setColor(Color.red);
        g.drawLine(242,168,242,185);
        g.drawLine(247,168,247,185);
        g.drawLine(252,168,252,185);
        g.drawLine(257,168,257,185);
        g.drawLine(262,168,262,185);
        g.drawLine(267,168,267,185);
        g.drawLine(272,168,272,185);
        g.drawLine(277,168,277,185);

        g.setColor(Color.cyan);
        int x[] = {260,270,275,255};
        int y[] = {85,85,100,100};
        g.fillPolygon(x,y,4);

        g.setColor(Color.red);
        g.fillOval(250,55,30,30);

        g.setColor(Color.cyan);
        g.fillRect(240,200,40,10);
        
        g.setColor(Color.blue);
        g.fillRoundRect(210,210,100,80,20,20);
        g.fillArc(185,220,40,50,90,35);
        g.fillArc(295,220,40,50,90,-35);

        g.setColor(Color.cyan);
        g.fillRoundRect(220,215,80,70,20,20);
        g.fillOval(213,212,7,7);
        g.fillOval(301,212,7,7);
        g.fillOval(213,275,7,7);
        g.fillOval(302,275,7,7);

        g.setColor(Color.orange);
        g.fillRoundRect(225,22,70,55,20,20);
        
        g.setColor(Color.cyan);
        g.fillRect(225,290,70,10);

        g.setColor(Color.blue);
        g.fillRect(225,300,70,50);
        
        g.setColor(Color.blue);
        int p[]={215,225,225,215};
        int q[]={300,300,360,300};
        g.fillPolygon(p,q,4);

        g.setColor(Color.orange);
        g.fillRoundRect(200,360,120,10,20,20);
        g.fillRoundRect(220,380,80,10,20,20);
        g.fillRoundRect(240,400,40,10,20,20);
    }
}
