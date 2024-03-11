/*
 * Written by Michelle Clark
 */
public class Rectangle extends Shape 
{
    private double length;
    private double width;
    private double area;
    public Rectangle()
    {
        super();
        this.length=0;
        this.width=0;
        this.area=this.length*this.width;
    }
    public Rectangle(String name, double length, double width)
    {
        super(name);
        this.setLength(length);
        this.setWidth(width);
        this.setArea();
    }
    public void setLength(double length)
    {
        if(length>=0)
            this.length=length;
        else
            this.length=0;
    }
    public void setWidth(double width)
    {
        if(width>=0)
            this.width=width;
        else
            this.width=0;
    }
    public double getLength()
    {
        return this.length;
    }
    public double getWidth()
    {
        return this.width;
    }
    public void setArea()
    {
        if(this.length>0 && this.width>0)
            this.area=this.length*this.width;
        else
            this.area=0;
    }
    public double getArea()
    {
        return this.area;
    }
    public String toString()
    {
        return super.toString()+"\t"+"Length: "+this.length+"\t"+"Width: "+this.width+
        "\t"+"Area: "+this.area;
    }
    public String toFile()
    {
        return super.toFile()+"\t"+this.length+"\t"+this.width+
        "\n";
    }
    public boolean equals(Rectangle other)
    {
        return other!=null && super.equals(other) && this.getLength()==other.getLength() && 
        this.getWidth()==other.getWidth();
    }
    // public int compareTo(Rectangle o)
    // {
    //     if(this.getArea()>o.getArea())
    //         return 1;
    //     else if(this.getArea()<o.getArea())
    //         return -1;
    //     //otherwise the areas must be equal
    //     else 
    //         return 0;
    // }
}
