/*
 * Written by Michelle Clark
 */
//Personal Note: functional code
public class GroceryList <T>
{

    private class ListNode
    {
        GroceryItem data;
        ListNode link;  
         
        public ListNode()
        {
            //initializing values to null
            data = null;
            link=null;
        }

        //paramaterized constructor
        public ListNode(GroceryItem data, ListNode link)
        {
            this.data = data;
            this.link = link;
        }
    }

    private ListNode head;
    private ListNode current;
    private ListNode previous;
    //not really useful in this demonstration but good to have in general
    private int size;

    public GroceryList()
    {
        head = current = previous = null;
        this.size=0;
    }

    public void gotoNext()
    {
        //there is no next so return 
        if(current == null)
            return;
        //otherwise set previous to current and current to next (.link is like .next)
        previous = current;
        current = current.link;
    }

    public GroceryItem getCurrent()
    {
        if(current == null)
            return null;
        return current.data;
    }

    public void setCurrent(GroceryItem aData)
    {
        if(aData==null||current==null)
            return;
        current.data = aData;
    }

    public void addItem(GroceryItem data)
    {
        ListNode newNode = new ListNode(data,null);
        //if the data is null does nothing
        if(data==null)
            return;
        //if the list is empty, add to front
        if(head==null)
        {
            head = current = newNode;
            size = 1;
            return;
        }
        //otherwise find the first empty spot to add data into the LinkedList
        ListNode temp = head;
        while(temp.link != null)
            temp = temp.link;
        temp.link = newNode;
        this.size++;
    }

    public void addItemAfterCurrent(GroceryItem aData)
    {
        //if list is empty or data provided is null does nothing
        if(head==null||aData==null)
        {
            return;
        }
        //current is at head
        ListNode newNode = new ListNode(aData, current.link);
        current.link = newNode;
        
        this.size++;
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

    public boolean hasPrev()
    {
        return current != head;
    }

    public void removeCurrent()
    {
        if(current == head)
        {
            //chops off head for collection by gargbage collector
            head = head.link;
            current = head;
        }
        else
        {
            //next of previous is next of current
            previous.link = current.link;
            //current is next of current, relinks around current
            current = current.link;
        }
        if(this.size>0)
        {
            size--;
        }
    }

    public int getSize()
    {
        return this.size;
    }

    public void showList()
    {
        ListNode temp = head;
        //int number = 1;
        while(temp!=null)
        {
            //way to number the list
            //System.out.println(number+". "+temp.data);
            System.out.println(temp.data);
            temp = temp.link;
            //number++;
        }
    }

    public double totalCost()
    {
        //here I accidentally made totalCost an int at first, which result in it taking the floor value
        //so the total was $90 instead of 102.789 repeating
        double totalCost=0;
        ListNode temp = head;
        while(temp!=null)
        {
            //storing the value of the GroceryItem
            double cost=temp.data.getValue();
            //adding the value to the total cost
            totalCost+=cost;
            //moving to the next item on the list
            temp=temp.link;
        }
        return totalCost;
    }

    public boolean contains(GroceryItem target)
    {
        boolean found = false;
        //here we traverse through the list, initializing our temp node to the head node
        ListNode temp = head;
        while((temp != null) && !found)
        {
            GroceryItem dataAtPosition = temp.data;
            //if the GroceryItem is equal to the target GroceryItem we return that found is true
            if(dataAtPosition.equals(target))
                found = true;
            //otherwise we keep traversing the list
            else
                temp = temp.link;
        }
        return found;
    } 
}
