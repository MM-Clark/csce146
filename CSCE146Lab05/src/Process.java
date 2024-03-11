/*
 * Written by Michelle Clark
 */
public class Process 
{
    private String name;
    private double completionTime;
        
    public Process()
    {
        this.name="none";
        this.completionTime=0;
    }
    public Process(String name, double completionTime)
    {
        this.setName(name);
        this.setCompletionTime(completionTime);
    }

    public void setName(String name)
    {
        if(name!=null)
            this.name=name;
        else    
            this.name="none";
    }

    public void setCompletionTime(double completionTime)
    {
        if(completionTime>=0)
            this.completionTime=completionTime;
        else
            this.completionTime=0.0;
    }

    public String getName()
    {
        return this.name;
    }

    public double getCompletionTime()
    {
        return this.completionTime;
    }

    public String toString()
    {
        return "Process Name: "+this.name+" Completion Time: "+this.completionTime;
    }
    
    //not necessary here but good general practice
    public boolean equals(Process other)
    {
        return other!=null && this.getName().equalsIgnoreCase(other.getName()) && this.getCompletionTime()==other.getCompletionTime();
    }
}
