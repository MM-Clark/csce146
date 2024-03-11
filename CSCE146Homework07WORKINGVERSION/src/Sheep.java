/*
 * Written by Michelle Clark
 */
public class Sheep implements Comparable <Sheep>
{
    private String name;
    private int shearingTime;
    private int arrivalTime;
    private int shearingTimeLeft;
    public Sheep()
    {
        this.name="none";
        this.shearingTime=0;
        this.arrivalTime=0;
        shearingTimeLeft=shearingTime;
    }
    public Sheep(String name, int shearingTime, int arrivalTime)
    {
        this.setName(name);
        this.setShearingTime(shearingTime);
        this.setArrivalTime(arrivalTime);
    }
    public void setName(String name)
    {
        if(name!=null)
            this.name=name;
        else
            this.name="none";
    }
    public String getName()
    {
        return this.name;
    }
    public void setShearingTime(int shearingTime)
    {
        if(shearingTime>0)
        {
            this.shearingTime=shearingTime;
            this.shearingTimeLeft=shearingTime;
        }
        else
            this.shearingTime=0;
    }
    public int getShearingTime()
    {
        return this.shearingTime;
    }
    public void decrementShearingTimeLeft()
    {
        this.shearingTimeLeft-=1;
    }
    public void setArrivalTime(int arrivalTime)
    {
        if(arrivalTime>0)
            this.arrivalTime=arrivalTime;
        else
            this.arrivalTime=0;
    }
    public int getArrivalTime()
    {
        return this.arrivalTime;
    }
    public int compareTo(Sheep other) 
    {
        //TODO: figure out the mess of comparability
        if(other==null)
            return -1;
        if(this.getShearingTime()<other.getShearingTime())
            return -1;
        else if(this.getShearingTime()>other.getShearingTime())
            return 1;
        else//implies is equal
            return this.getName().compareTo(other.getName());
    }
    public int compareTo2(Sheep other) 
    {
        //TODO: figure out the mess of comparability
        if(other==null)
            return -1;
        if(this.getArrivalTime()<other.getArrivalTime())
            return -1;
        else if(this.getArrivalTime()>other.getArrivalTime())
            return 1;
        else//implies is equal
            return this.getName().compareTo(other.getName());
    }
    public String toString()
    {
        return "Name: "+this.name+", Sheer Time: "+this.shearingTime+", Arrival Time: "+this.arrivalTime;
    }
    public String toStringFull()
    {
        return "Name: "+this.name+", Sheer Time: "+this.shearingTime+", Arrival Time: "+this.arrivalTime+" Shearing Time Left: "+this.shearingTimeLeft;
    }
    public boolean isDone()
    {
        return this.shearingTimeLeft==0;
    }
}
