/*
 * Written by Michelle Clark
 */
public class GroceryItem 
{
    private String name;
    private double value;

    public GroceryItem()
    {
        //default constructor
        this.name="none";
        this.value=0;
    }
    public GroceryItem(String name, double value)
    {
        //paramaterized constructor
        this.setName(name);
        this.setValue(value);
    }
    public void setName(String name)
    {
        if(name!=null)
            this.name=name;
        else
            this.name="none";
    }
    public void setValue(double value)
    {
        //if value is above 0(assuming the price is not $0.00 bc absolutely not in this economy) set value to 
        //inputed val
        if(value>=0)
            this.value=value;
        //otherwise set to 0
        else    
            this.value=0;
    }
    public String getName()
    {
        return this.name;
    }
    public double getValue()
    {
        return this.value;
    }
    public String toString()
    {
        return "Grocery Item Name: "+this.name+" Value: "+this.value;
    }
    public boolean equals(GroceryItem other)
    {
        //under assumption that if the case if off, the strings are still equal
        return ((other!=null && this.getName().equalsIgnoreCase(other.getName())) && this.getValue()==other.getValue());
    }
}
