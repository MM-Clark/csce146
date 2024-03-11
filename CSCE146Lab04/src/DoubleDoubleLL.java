/*
 * Written by Michelle Clark
 */
public class DoubleDoubleLL
{
    //VERSION IMPLEMENTED w/o pointers, have version with pointers too if preferred
    //was told this would be more memory efficient, so we did this way
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
    
    //yes this pointer adds to memory used but it's okay think of the time saved
    private ListNode tail;
    
    private int size;

    public DoubleDoubleLL()
    {
        head = current = tail = null;
        this.size=0;
    }

    public void gotoNext()
    {
        if(current != null)
        {
            //scooch each forward by one
            current = current.next;  
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
            current = current.prev;
        }
    }
    //GO TO END METHOD
    public void gotoEnd()
    {   
        current = tail;
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
            newNode.prev=null;
            newNode.next=null;
            size=1;
            return;
        }
        //otherwise find the first empty spot to add data into the LinkedList
        ListNode temp = head;
        while(temp.next != null)
             temp = temp.next;
        temp.next = newNode;
        tail=newNode;
        newNode.prev = temp;
        newNode.next=null;
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
        //allocate node, put in data
        ListNode newNode = new ListNode(aData, current, current.next);
        //make next of new node next of prev node
        newNode.next=current.next;
        //make next of prev node new node
        current.next = newNode;
        //make prev node previous of new node
        newNode.prev=current;
        //if the next index is null, current is the last index or tail
        if(current.next==null)
            tail=current;
        //change previous of newNode's next node
        if(newNode.next!=null)
            newNode.next.prev=newNode;
        this.size++;
    }

    public void reset()
    {
        //sets current back to head
        current = head;
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

    public void removeCurrent()
    {
        // Base case 
        if (head == null || current == null) { 
            return; 
        } 
  
        // If node to be deleted is head node 
        if (head == current) 
        { 
            //removes by literally just making the value outside range of linked list 
            head = head.next; 
        } 
  
        // Change next only if node to be deleted 
        // is NOT the last node 
        if (current.next != null) 
        { 
            current.next.prev = current.prev; 
        } 
  
        // Change prev only if node to be deleted 
        // is NOT the first node 
        if (current.prev != null) 
        { 
            current.prev.next = current.next; 
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
