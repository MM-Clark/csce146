/*
 * Written by Michelle Clark
 */
public class Task 
{
    private String action;
    private int priority;
    public Task()
    {
        action="none";
        priority=4;
    }
    public Task(String action, int priority)
    {
        this.setAction(action);
        this.setPriority(priority);
    }
    public void setAction(String action)
    {
        if(action!=null)
            this.action = action;
        else    
            this.action="none";
    }
    public void setPriority(int priority)
    {
        if(priority<=4&&priority>=0)
            this.priority = priority;
        else    
            this.priority=4;
    }
    public String getAction()
    {
        return this.action;
    }
    public int getPriority()
    {
        return this.priority;
    }
    public String toString()
    {
        return "[Task] Priority: "+this.getPriority()+" Task: "+this.getAction();
    }
    public String toStringFile()
    {
        return this.getPriority()+"\t"+this.getAction();
    }
    public boolean equals(Task other)
    {
        //here we assume that Action 01 and action 01 are the same thing because they only vary in case
        return other!=null && this.getAction().equalsIgnoreCase(other.getAction()) && this.getPriority()==other.getPriority();
    }
    public boolean equals(Object other)
    {
        if(other instanceof Task)
            return other!=null && this.getAction().equalsIgnoreCase(((Task) other).getAction()) && this.getPriority()==((Task) other).getPriority();
        else
            return false;
    }
}
