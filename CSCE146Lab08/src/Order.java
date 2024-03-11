/*
 * Written by Michelle Clark
 */
public class Order implements Comparable <Order>
{
    private String customer;
    private String foodOrder;
    private int cookingTime;
    private int arrivalTime;
    private int cookingTimeLeft;

    public Order()
    {
        customer="none";
        foodOrder="none";
        cookingTime=1;
        arrivalTime=0;
        cookingTimeLeft=cookingTime;
    }
    public Order(String customer, String foodOrder, int cookingTime, int arrivalTime)
    {
        this.setCustomer(customer);
        this.setFoodOrder(foodOrder);
        this.setCookingTime(cookingTime);
        this.setArrivalTime(arrivalTime);
    }

    public void setCustomer(String customer)
    {
        if(customer!=null)
            this.customer=customer;
        else
            this.customer="none";
    }
    public String getCustomer()
    {
        return this.customer;
    }

    public void setFoodOrder(String foodOrder)
    {
        if(foodOrder!=null)
            this.foodOrder=foodOrder;
        else
            this.foodOrder="none";
    }
    public String getFoodOrder()
    {
        return this.foodOrder;
    }

    public void setCookingTime(int cookingTime)
    {
        if(cookingTime>0)
        {
            this.cookingTime=cookingTime;
            this.cookingTimeLeft=cookingTime;
        }
        else
            this.cookingTime=1;
    }
    public int getCookingTime()
    {
        return this.cookingTime;
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
    public String toString()
    {
        return "Customer: "+this.customer+", Order: "+this.foodOrder+", Cooking Time Left: "+this.cookingTimeLeft;
    }
    @Override
    public int compareTo(Order other) 
    {
        if(other==null)
            return -1;
        if(this.getCookingTime()>other.getCookingTime())
            return 1;
        else if(this.getCookingTime()<other.getCookingTime())
            return -1;
        else if(this.getCookingTime()==other.getCookingTime())
            return 0;
        else
            return -1;
    }
    public void cookForOneMinute()
    {
        this.cookingTimeLeft-=1;
    }
    public boolean isDone()
    {
        return this.cookingTimeLeft==0;
    }
}
