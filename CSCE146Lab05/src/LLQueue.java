/*
 * Written by Michelle Clark
 */
public class LLQueue <T> implements QueueI<T>
{
    //singly linked list
    private class Node
    {
        T data;
        Node link;
        public Node(T aData, Node aLink)
        {
            data = aData;
            link = aLink;
        }
    }

    private Node head;
    private Node tail;
    //don't need current or previous for queue
    public LLQueue()
    {
        head = tail = null;
    }

    public void enqueue(T aData)
    {
        Node newNode = new Node(aData,null);
        if(head == null)
        {
            head = tail = newNode;
            return;
        }
        //point to newNode and set as tail
        tail.link = newNode;
        tail = tail.link;
    }

    public T dequeue()
    {
        //if head isnt null, returns the value of head (contents)
        if(head == null)
            return null;
        T ret = head.data;
        //sets head to next of head, delinking the original head from the list
        head = head.link;
        return ret;
    }

    public T peek()
    {
        if(head==null)
            return null;
        return head.data;
    }

    public T getCurrent()
    {
        if(tail==null)
            return null;
        return tail.data;
    }

    public void print()
    {
        //check if list is empty if so return, nothing prints
        if(head==null)
            return;
        //using for loop to iterate through linked list to print
        for(Node temp = head; temp != null; temp = temp.link)
            System.out.println(temp.data);
    }
}
