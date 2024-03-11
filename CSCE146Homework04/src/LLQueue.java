/*
 * Written by Michelle Clark
 */
public class LLQueue <T> implements QueueI<T>
{
    private class ListNode
    {
        T data;
        ListNode link;
        public ListNode(T aData, ListNode aLink)
        {
            data = aData;
            link = aLink;
        }
    }

    private ListNode head;
    private ListNode tail;

    public LLQueue()
    {
        head = tail = null;
    }
    //adds to end of queue
    public void enqueue(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        if(head==null)
        {
            head = tail = newNode;
            return;
        }
        tail.link = newNode;
        tail = tail.link;
    }
    //removes from head
    public T dequeue()
    {
        if(head==null)
            return null;
        T ret = head.data;
        head = head.link;
        return ret;
    }
    //shows head without removing
    public T peek()
    {
        if(head==null)
            return null;
        return head.data;
    }
    //prints queue
    public void print()
    {
        for(ListNode temp = head;temp!=null;temp=temp.link)
            System.out.println(temp.data);
    }

    public int getSize()
    {
        if(head==null)
            return 0;
        int size = 1;
        for(ListNode n = head; n.link !=null; n = n.link)
            size++;
        return size;
    }
}
