/*
 * Written by Michelle Clark
 */
public class RightTriangle extends Shape
{
    private double base;
    private double height;
    private double area;
    public RightTriangle()
    {
        super();
        this.base=0;
        this.height=0;
        this.area=0.5*this.base*this.height;
    }
    public RightTriangle(String name, double base, double height)
    {
        super(name);
        this.setBase(base);
        this.setHeight(height);
        this.setArea();
    }
    public void setBase(double base)
    {
        if(base>=0)
            this.base=base;
        else
            this.base=0;
    }
    public void setHeight(double height)
    {
        if(height>=0)
            this.height=height;
        else
            this.height=0;
    }
    public double getBase()
    {
        return this.base;
    }
    public double getHeight()
    {
        return this.height;
    }
    public void setArea()
    {
        if(this.height>0&&this.base>0)
            this.area=0.5*this.base*this.height;
        else
            this.area=0;
    }
    public double getArea()
    {
        return this.area;
    }
    public String toString()
    {
        return super.toString()+"\t"+"Base: "+this.base+"\t"+"Height: "+this.height+
        "\t"+"Area: "+this.area;
    }
    public String toFile()
    {
        return super.toFile()+"\t"+this.base+"\t"+this.height+"\n";
    }
    public boolean equals(RightTriangle other)
    {
        return other!=null && super.equals(other) && this.getBase()==other.getBase() && 
        this.getHeight()==other.getHeight();
    }
}
