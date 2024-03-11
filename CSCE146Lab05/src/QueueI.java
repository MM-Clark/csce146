/*
 * Written by Michelle Clark
 */
public interface QueueI <T>
{
    //interface allows for functionality across classes
    public void enqueue(T aData);
    public T dequeue();
    public T peek();
    public void print();
}
