/*
 * Written by Michelle Clark
 */
public class Circle extends Shape
{
    private double radius;
    private double area;
    public Circle()
    {
        super();
        this.radius=0;
        this.area=Math.PI*Math.pow(this.radius, 2);
    }
    public Circle(String name, double radius)
    {
        super(name);
        this.setRadius(radius);
        this.setArea();
    }
    public void setRadius(double radius)
    {
        if(radius>=0)
            this.radius=radius;
        else
            this.radius=0;
    }
    public double getRadius()
    {
        return this.radius;
    }
    public void setArea()
    {
        if(this.radius>0)
            this.area=Math.PI*Math.pow(this.radius, 2);
        else
            this.area=0;
    }
    public double getArea()
    {
        return this.area;
    }
    public String toString()
    {
        return super.toString()+"\t"+"Radius: "+this.radius+"\t"+"Area: "+this.area;
    }
    public String toFile()
    {
        return super.toFile()+"\t"+this.radius+"\n";
    }
    public boolean equals(Circle other)
    {
        return other!=null && super.equals(other) && this.getRadius()==other.getRadius();
    }
}
