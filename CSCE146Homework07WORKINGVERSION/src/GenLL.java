/*
 * Written by Michelle Clark
 */

import org.w3c.dom.Node;

public class GenLL <T>
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
    private ListNode current;//moveable iterator / current node of interest
    private ListNode previous;//previous.link==current
    private int size = 0;

    public GenLL()
    {
        head = current = previous = null;
    }

    public void add(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        
        if(head==null)
        {
            head = current = newNode;
            size = 1;
            return;//can use else too
        }

        ListNode temp = head;
        while(temp.link != null)
            temp = temp.link;
        temp.link = newNode;
        size++;
    }

    public void addAfterCurrent(T aData)
    {
        if(current == null)
            return;
        ListNode newNode = new ListNode(aData,current.link);
        current.link = newNode;
        size++;
    }

    public T getCurrent()
    {
        if(current == null)
            return null;
        return current.data;
    }

    public void setCurrent(T aData)
    {
        if(aData == null||current==null)
            return;
        current.data = aData;
    }

    public void goToNext()
    {
        if(current == null)
            return;
        previous = current;
        current = current.link;
    }

    public void reset()
    {
        current = head;
        previous = null;
    }

    public boolean hasMore()
    {
        return current != null;
    }

    public void removeCurrent()
    {
        if(current == null)
            return;
        if(current == head)
        {
            head = head.link;
            current = head;
            size = 0;
        }
        else
        {
            previous.link = current.link;
            current = current.link;
            size--;
        }
    }

    public void print()
    {
        ListNode temp = head;
        while(temp!=null)
        {
            System.out.println(temp.data);
            temp = temp.link;
        }
    }
    public int getSize()
    {
        return this.size;
    }
    public T getAt(int index)
    {
        if(index<0||index>=size)
            return null;
        ListNode temp = head;
        for(int i=0;i<index;i++)
            temp = temp.link;
        return temp.data;
    }
    public void setAt(int index, T aData)
    {
        if(index<0||index>=size||aData!=null)
            return;
        ListNode temp = head;
        for(int i=0;i<index;i++)
            temp = temp.link;
        temp.data = aData;
    }   
    public ListNode mergeSort(ListNode head)
    {
        // Base case : if head is null
        if (head == null || head.link == null) {
            return head;
        }
 
        // get the middle of the list
        ListNode middle = getMiddle(head);
        ListNode head2 = middle.link;
 
        // set the next of middle node to null
        middle.link = null;
 
        // Apply mergeSort on left list
        ListNode left = mergeSort(head);
 
        // Apply mergeSort on right list
        ListNode right = mergeSort(head2);
 
        // Merge the left and right lists
        ListNode sortedlist = merge(left, right);
        return sortedlist;
    }
    public ListNode getMiddle(ListNode head)
    {
        if (head == null)
            return head;
 
        ListNode slow = head, fast = head;
 
        while (fast.link != null && fast.link.link != null) {
            slow = slow.link;
            fast = fast.link.link;
        }
        return slow;
    }
    ListNode merge(ListNode head1, ListNode head2)
    {
        Sheep tempSh = new Sheep("none",0,0);
        ListNode merged = new ListNode((T)tempSh,null);
        ListNode temp = merged;
       
        // While head1 is not null and head2
        // is not null
        while (head1 != null && head2 != null) {
            if (((Sheep)head1.data).getArrivalTime() < ((Sheep)head2.data).getArrivalTime()) {
                temp.link = head1;
                head1 = head1.link;
            }
            else {
                temp.link = head2;
                head2 = head2.link;
            }
            temp = temp.link;
        }
       
        // While head1 is not null
        while (head1 != null) 
        {
            temp.link = head1;
            head1 = head1.link;
            temp = temp.link;
        }
        // While head2 is not null
        while (head2 != null) 
        {
            temp.link = head2;
            head2 = head2.link;
            temp = temp.link;
        }
        return merged.link;
    }
}
