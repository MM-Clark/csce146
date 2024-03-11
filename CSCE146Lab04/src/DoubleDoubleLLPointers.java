/*
 * Written by Michelle Clark
 */
public class DoubleDoubleLLPointers
{

    private class ListNode
    {
        double data;
        ListNode prev;   
        ListNode next;
        
        //paramaterized constructor
        public ListNode(double data, ListNode prev, ListNode next)
        {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private ListNode head;
    private ListNode current;
    private ListNode previous;
    private ListNode next;
    //yes this pointer adds to memory used but it's okay
    private ListNode tail;
    
    private int size;

    public DoubleDoubleLLPointers()
    {
        head = current = previous = next = tail = null;
        this.size=0;
    }

    public void gotoNext()
    {
        if(current != null)
        {
            //scooch each forward by one
            previous = current;
            current = current.next;
            if(current!=null && current.next != null)
                next = current.next;
        }
    }
    //PREV METHOD
    public void gotoPrev()
    {
        //if the current node is not the head not null, we can go to the previous node
        if(current==head)
        {
            return;
        }
        //if the current has previous and is not equal to null, set to previous
        if(current!=null && this.hasPrev())
        {
            next = current;
            current = previous;
            if(current==head)
            {
                previous = null;
            }
            else
            {
                previous = current.prev;
            }
        }
    }
    //GO TO END METHOD
    public void gotoEnd()
    {   
        current = tail;
        previous = tail.prev;
        next = null;
        //less time efficient way to access tail by traveling through the entire list
        // while(current.next!=null)
        // {
        //     previous = current;
        //     current = current.next;
        // }
    }

    public double getCurrent()
    {
        //cannot return null because returns double
        if(current == null)
            return 0;
        return current.data;
    }

    public void setCurrent(double aData)
    {
        if(aData==0||current==null)
            return;
        current.data = aData;
    }

    public void add(double data)
    {
        ListNode newNode = new ListNode(data,null,null);
        //if the list is empty, add to front
        if(head==null)
        {
            head = current = tail = newNode;
            return;
        }
        //otherwise find the first empty spot to add data into the LinkedList
        ListNode temp = head;
        while(temp.next != null)
             temp = temp.next;
        temp.next = newNode;
        tail=newNode;
        newNode.prev = temp;
        
        //must increase size
        this.size++;
    }

    public void addAfterCurrent(double aData)
    {
        //current is null
        if(current==null)
        {
            return;
        }
        //current is at head
        ListNode newNode = new ListNode(aData, current, current.next);
        current.next = newNode;
        //if the next index is null, current is the last index or tail
        if(current.next==null)
            tail=current;
        this.size++;
    }

    public void reset()
    {
        //sets current back to head
        current = head;
        //must make prev null
        previous = null;
        //next will be the next of head
        next = head.next;
    }

    public boolean hasMore()
    {
        return current != null;
    }

    public boolean hasPrev()
    {
        return current != head;
    }

    public void remove(double aData)
    {
        reset();
        while(hasMore())
        {
            if(aData==current.data)
            {
                removeCurrent();
            }
            gotoNext();
        }
    }

    public void deleteAtBeginning()
    {
        if(head==null)
            return;
        if(head==tail)
        {
            head=null;
            tail=null;
            return;
        }
        ListNode temp = head;
        head = head.next;
        head.prev=null;
        temp.next=null;
        //must set current forward one so we will access third element next
        //implying the user can't go outside of the linked list in this direction
        current = head;
    }

    public void deleteAtEnd()
    {
        if(tail==null)
            return;
        if(head==tail)
        {
            head=null;
            tail=null;
            return;
        }
        ListNode temp = tail;
        tail = tail.prev;
        tail.next = null;
        temp.prev=null;
    }

    public void removeCurrent()
    {
        //set to only run if we are deleting in middle of list
        boolean middle = true;
        //if head removed, reinstate prev, remove current
        if(head==null)
        {
            System.out.println("nothing to delete");
            return;
        }
        if(current==null)
        {
            System.out.println("sorry we messed up badly, position is wrong");
        }
        if(current == head)
        {
            //TODO: implement beginning
            deleteAtBeginning();
            middle=false;
        }
        //go back one, reset tail ref, set next to null
        if(current==tail)
        {
            deleteAtEnd();
            middle=false;
        }
        if(middle)
        {
            previous.next=current.next;
            next.prev=current.prev;
            current=current.next;
            previous=current.prev;
            next=current.next;
        }

        if(this.size>0)
            size--;
    }

    public int getSize()
    {
        return this.size;
    }

    public void print()
    {
        ListNode temp = head;
        //int number = 1;
        if(head==null)
        {
            System.out.println("Doubly linked list is empty");
            return;
        }
        while(temp!=null)
        {
            //way to number the list
            //System.out.println(number+". "+temp.data);
            System.out.println(temp.data);
            temp = temp.next;
            //number++;
        }
    }

    boolean contains(double target)
    {
        boolean found = false;
        //here we traverse through the list, initializing our temp node to the head node
        ListNode temp = head;
        while((temp != null) && !found)
        {
            //if the GroceryItem is equal to the target GroceryItem we return that found is true
            if(temp.data==(target))
                found = true;
            //otherwise we keep traversing the list
            else
                temp = temp.next;
        }
        return found;
    } 
}
