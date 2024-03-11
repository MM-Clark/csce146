/*
 * Written by Michelle Clark
 */
public class Sort 
{
    private String name;
    private int sorts;
    public Sort()
    {
        this.name="none";
        this.sorts=0;
    }
    public Sort(String name, int sorts)
    {
        this.setName(name);
        this.setSorts(sorts);
    }
    public void setName(String name)
    {
        if(name!=null)
            this.name=name;
        else
            this.name="none";
    }
    public void setSorts(int sorts)
    {
        if(sorts>0)
            this.sorts=sorts;
        else 
            this.sorts=0;
    }
    public String getName()
    {
        return this.name;
    }
    public int getSorts()
    {
        return this.sorts;
    }
    public String toString()
    {
        return this.name+"  Sorts: "+this.sorts;
    }
    public String toStringName()
    {
        return this.name;
    }
    public boolean equals(Sort other)
    {
        return other!=null&&this.getName().equals(other.getName())&&this.getSorts()==other.getSorts();
    }
}
