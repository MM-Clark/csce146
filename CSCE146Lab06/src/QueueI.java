/*
 * Written by Michelle Clark
 */
//remember the <T> for generic type!
public interface QueueI
{
    public void enqueue(Sort aData);
    public Sort dequeue();
    public Sort peek();
    public void print();
    public int getSize();

}