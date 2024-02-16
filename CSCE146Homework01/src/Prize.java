/*
 * Written by Michelle Clark
 */

public class Prize 
{
    //this class holds a single item from the list provided 
    private String name;
    private double price;
    
    public Prize()
    {
        this.name="none";
        this.price=0.00;
    }

    public Prize(String name, double price)
    {
        this.setName(name);
        this.setPrice(price);
    }

    public void setName(String name)
    {
        if(name != null)
        {
            this.name = name;
        }
    }

    public String getName()
    {
        return this.name;
    }

    public void setPrice(double price)
    {
        if(price >= 0.0)
        {
            this.price = price;
        }
    }

    public double getPrice()
    {
        return this.price;
    }

    public String toString()
    {
        return "Name: "+this.name+" Price: "+this.price;
    }

    //returns only the prize name
    public String toStringName()
    {
        return "Name: "+this.name;
    }

    public boolean equals(Prize other)
    {
        return other!=null&&this.getName().equalsIgnoreCase(other.getName())&&this.getPrice()==other.getPrice();
    }
    //checks if the input contains special character, omitted check because Shredded Wheat is invalid prize for space delim
    //and Globe that lights up! is fine
    public boolean validateInput()
    {
        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}~";
        String name=this.getName();
        String str2[]=name.split("");

        for (int i=0;i<str2.length;i++)
        {
            if (specialCharacters.contains(str2[i]))
            {
                return false;
            }
        }
        return true;
    }
}
