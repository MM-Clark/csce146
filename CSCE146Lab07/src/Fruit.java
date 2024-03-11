/*
 * Written by Michelle Clark
 */
public class Fruit implements Comparable <Fruit>
{
    private String type;
    private double weight;
    public Fruit()
    {
        this.type="apple";
        this.weight=1.0;
    }
    public Fruit(String type, double weight)
    {
        this.setType(type);
        this.setWeight(weight);
    }
    public void setType(String type)
    {
        //checks if the type is a valid type
        if(type!=null && type.equalsIgnoreCase("apple")||type.equalsIgnoreCase("orange")||
            type.equalsIgnoreCase("banana")||type.equalsIgnoreCase("kiwi")||type.equalsIgnoreCase("tomato"))
            this.type=type;
        else
            this.type="apple";
    }
    public void setWeight(double weight)
    {
        //ensures weight is a positive non zero decimal value
        if(weight>0)
            this.weight=weight;
        else
            this.weight=1.0;
    }
    public String getType()
    {
        return this.type;
    }
    public double getWeight()
    {
        return this.weight;
    }
    public String toString()
    {
        return "Type: "+type+" Weight: "+weight;
    }
    public boolean equals(Fruit other)
    {
        return other!=null && this.getType().equals(other.getType()) 
        && this.getWeight()==other.getWeight();
    }
    
    @Override
    public int compareTo(Fruit other) 
    {
        if(this==null)
            return -1;
        //if this fruit is heavier than the other, we return 1 to indicate so 
        if(this.getWeight()>other.getWeight())
            return 1;
        //if it is lighter, we return -1 to indicate so SEE BINARY TREE
        else if(this.getWeight()<other.getWeight())
            return -1;
        //otherwise if they are equal we compare names
        else if(this.getWeight()==other.getWeight())  
            return this.getType().compareTo(other.getType());
        else
            return -1;
    }
}
