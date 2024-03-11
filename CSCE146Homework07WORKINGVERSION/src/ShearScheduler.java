/*
 * Written by Michelle Clark
 */
public class ShearScheduler 
{
    MinHeap<Sheep> sheep = new MinHeap<Sheep>();
    private Sheep currentSheep;
    private int currentMinute;
    private int totalSheep;
    private double summedWaitingTimes;
    public void addSheep(Sheep in)
    {
        this.totalSheep++;
        if(currentSheep==null)
            this.currentSheep=in;
        else
            this.sheep.add(in);
    }
    public GenLL<String> advanceOneMinute(GenLL<String> in)
    {
        currentMinute++;
        this.currentSheep.decrementShearingTimeLeft();
        //check if current sheep is done
        if(this.currentSheep.isDone())
        {
            System.out.println("FINISHED SHEARING "+this.currentSheep.toString());
            in.add(this.currentSheep.toString());
            int waitingTime = this.currentMinute - this.currentSheep.getArrivalTime();
            this.summedWaitingTimes+=waitingTime;
            //replace current sheep with sheep at top of heap
            this.currentSheep=sheep.remove();
        }
        return in;
    }
    public boolean isDone()
    {
        if(this.currentSheep==null)
            return true;
        else
            return  false;
    }
    public double getAverageWaitingTime()
    {
        return this.summedWaitingTimes/this.totalSheep;
    }
    public String getCurrentSheep()
    {
        return this.currentSheep.toStringFull();
    }
}
