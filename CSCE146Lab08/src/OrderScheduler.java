/*
 * Written by Michelle Clark
 */
public class OrderScheduler 
{
    MinHeap<Order> orders = new MinHeap<Order>();
    private Order currentOrder;
    private int currentMinute;
    private int totalOrders;
    private double summedWaitingTimes;
    public void addOrder(Order in)
    {
        this.totalOrders++;
        if(currentOrder==null)
            this.currentOrder = in;
        else
            this.orders.add(in);
    }
    public void advanceOneMinute()
    {
        currentMinute++;
        this.currentOrder.cookForOneMinute();
        //checking if the current order is done
        if(this.currentOrder.isDone())
        {
            int waitingTime = this.currentMinute - this.currentOrder.getArrivalTime();
            this.summedWaitingTimes+=waitingTime;
            //replace current order with order on top of the heap
            this.currentOrder = orders.remove();
        }
    }
    public boolean isDone()
    {
        if(this.currentOrder==null)
            return true;
        else
            return false;
    }
    public double getAverageWaitingTime()
    {
        //making summedWaitingTimes a double ensures we get a decimal val
        return this.summedWaitingTimes/this.totalOrders;
    }
    public Order getCurrentOrder()
    {
        return this.currentOrder;
    }
}
