/*
 * Written by Michelle Clark
 */
public class Showcase 
{
    //this class holds the arrays for the entire prize list and the randomly selected prize array that is
    //the showcase; each array uses the type Prize; 

    //the class must populate the entire prize array upon its construction from the file 
    //the file contains each prize name and cost separated by a tab 

    //method for populating the showcase by randomly selecting items from the prize list
    private Prize[] prizes;
    public String printPrizes;
    public static final int DEF_SIZE = 100;
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Showcase()
    {
        initialize(DEF_SIZE);
    }

    public Showcase(int size)
    {
        initialize(size);
    }

    public void initialize(int size)
    {
        if(size>=1)
            prizes = new Prize[size];
        else
            prizes = new Prize[DEF_SIZE];
    }

    public void addPrize(Prize prize)
    {
        //if null return
        if(prize == null)
            return;
        //check if last index is full
        if(prizes[prizes.length-1]!=null)
            return;
        for(int i=0;i<prizes.length;i++)
        {
            if(prizes[i]==null)
            {
                prizes[i]=prize;
                break;
            }
        }
    }

    public void replaceVals(String name, double price, int index)
    {
        for(int i=0;i<prizes.length;i++)
        {
            if(i==index)
            {
                prizes[i].setName(name);
            }
        }
    }

    public Prize findPrize(int index)
    {
        Prize ret = new Prize();
        for(int i=0;i<prizes.length;i++)
        {
            if(i==index)
            {
                String name = prizes[i].getName();
                double price = prizes[i].getPrice();

                ret.setName(name);
                ret.setPrice(price);
                break;
            }
        }
        return ret;
    }

    public void removePrize(String name)
    {
        int removeIndex = -1;
        for(int i=0;i<prizes.length;i++)
        {
            if(prizes[i]!=null&&prizes[i].getName().equals(name))
            {
                removeIndex = i;
                break;
            }
        }

        if(removeIndex == -1)
        {
            return;
        }

        else
        {
            for(int i=removeIndex;i<prizes.length-1;i++)
                prizes[i]=prizes[i+1];
            prizes[prizes.length-1] = null;
        }
    }

    private void sortPrizes()
    {
        boolean swapped = true;
        while(swapped)
        {
            swapped = false;
            for(int i=0;i<prizes.length-1;i++)
            {
                if(prizes[i]==null||prizes[i+1]==null)
                    break;
                if(prizes[i].getPrice() > prizes[i].getPrice())
                {
                    Prize temp = prizes[i];
                    prizes[i]=prizes[i+1];
                    prizes[i+1]=temp;
                    swapped = true;
                }
            }
        }
    }

    //prints full prizes 
    public void printPrizes()
    {
        for(Prize prize: prizes)
        {
            if(prize == null)
            {
                break;
            }
            System.out.println(prize.toString());
        }
        System.out.println();
    }

    //aggravating the user by only printing the names of prizes
    public void printPrizeNames()
    {
        for(Prize prize: prizes)
        {
            if(prize == null)
            {
                break;
            }
            System.out.println(ANSI_GREEN+prize.toStringName()+ANSI_RESET);
        }
    }

    //this works
    public int getLength()
    {
        int length = 0;
        for(Prize prize: prizes)
        {
            if(prize == null)
            {
                break;
            }
            length++;
        }
        return length;
    }

    public double addPrices()
    {
        double addedPrices = 0;

        for(int i=0;i<prizes.length;i++)
        {
            if(prizes[i] == null)
                break;
            else
            {
                double temp = prizes[i].getPrice();
                addedPrices+=temp;
            }
        }

        return addedPrices;
    }

    public Showcase selectPrizes(Showcase input, int[] randoms)
    {
        int length = randoms.length;
        Showcase ret = new Showcase(length);
        int temp = -1;
        for(int i=0;i<length;i++)
        {
            if(randoms[i]==0)
                throw new NullPointerException("uhhhh...oopsies we got a random that is zero uh this is problematic");
            else
            {
                //these random values get iterated down by one because they are 1-6 but indices are 0-5
                temp = randoms[i]-1;
                if(temp>=0)
                {
                    Prize prizeTemp = input.findPrize(temp);
                    ret.addPrize(prizeTemp);
                }
            }
        }
        return ret;
    }
    //THIS GUY SHOWS IF SOMETHING IS NEVER FOUND
    // public void findAntacid()
    // {
    //      String antacid = "Globe that lights up!";
    //      boolean found=false;
    //      for(int i=0;i<prizes.length;i++)
    //      {
    //          if(prizes[i]!=null && prizes[i].getName().contains(antacid))
    //          {
    //              System.out.println("true");
    //              found=true;
    //          }
    //      }
    //      if(!found)
    //         System.out.println("NEVER FOUND");
    // }

    
}
