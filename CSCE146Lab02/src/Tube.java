/*
 * Written by Michelle Clark
 */
public class Tube 
{
    private double radius;
    private double height;
    private double volume;
    public Tube()
    {
        this.radius = 0;
        this.height=0;
        this.volume=0;
    }
    public Tube(double radius, double height)
    {
        this.setRadius(radius);
        this.setHeight(height);
        this.setVolume();
    }
    public void setRadius(double radius)
    {
        if(radius>=0)
            this.radius=radius;
        else
            this.radius=0.0;
    }
    public double getRadius()
    {
        return this.radius;
    }
    public void setHeight(double height)
    {
        if(height>=0)
            this.height=height;
        else
            this.height=0.0;
    }
    public double getHeight()
    {
        return this.height;
    }
    public void setVolume()
    {
        if(this.radius!=0.0&&this.height!=0.0)
            this.volume = Math.pow(this.radius,2)* Math.PI* this.height;
        else
            this.volume=0.0;
    }
    public double getVolume()
    {
        return this.volume;
    }
    public String toString()
    {
        return "radius "+this.radius+" height "+this.height+" volume "+this.volume;
    }
}
