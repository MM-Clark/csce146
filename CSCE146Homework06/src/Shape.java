/*
 * Written by Michelle Clark
 */
public class Shape implements Comparable<Shape>
{
    private String name;
    
    public Shape()
    {
        this.name="null";
    }
    public Shape(String name)
    {
        this.setName(name);
    }
    public void setName(String name)
    {
        if(name!=null)
            this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public String toString()
    {
        return "Name: "+this.name;
    }
    public String toFile()
    {
        return this.name;
    }
    public boolean equals(Shape other)
    {
        return other!=null && this.getName().equals(other.getName());
    }
    public int compareTo(Shape o)
    {
        //TODO: figure out how I can overload this method to compare two rectangles
        double thisArea=-1;
        double oArea=-1;
        if(o!=null)
        {
            if(this instanceof Rectangle)
            {
                Rectangle thisR = (Rectangle)this;
                thisArea = thisR.getArea();
            } 
            else if(this instanceof Circle)
            {
                Circle thisR = (Circle)this;
                thisArea = thisR.getArea();
            }
            else if(this instanceof RightTriangle)
            {
                RightTriangle thisR = (RightTriangle)this;
                thisArea = thisR.getArea();
            }

            if(o instanceof Rectangle)
            {
                Rectangle othR = (Rectangle)o;
                oArea = othR.getArea();
            } 
            else if(o instanceof Circle)
            {
                Circle othR = (Circle)o;
                oArea = othR.getArea();
            }
            else if(o instanceof RightTriangle)
            {
                RightTriangle othR = (RightTriangle)o;
                oArea = othR.getArea();
            }
        }

        if(thisArea>=0 && oArea>=0)
        {
            if(thisArea > oArea)
                return 1;
            else if(thisArea < oArea)
                return -1;
            else//areas must be equal
                //It just so happens that the shapes are ordered lexicographically for comparing equal areas
                return (this.getName().compareTo(o.getName()));
        }
        //returning a very massive value to indicate to program that it messed up badly
        return 2000;
    }
}
