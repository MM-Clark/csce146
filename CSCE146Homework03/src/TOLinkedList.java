/*
 * Written by Michelle Clark
 */
public class TOLinkedList <T>
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
    private ListNode current;
    private ListNode previous;

    public TOLinkedList()
    {
        head = current = previous = null;
    }

    public void add(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        //if empty list create head
        if(head==null)
        {
            head = current = newNode;
            return;
        }

        ListNode temp = head;
        while(temp.link != null)
            temp = temp.link;
        temp.link = newNode;
    }

    public void addAfterCurrent(T aData)
    {
        if(current == null)
            return;
        //creates node with node.next set to current.next, linking it to right part of list
        ListNode newNode = new ListNode(aData,current.link);
        //linking current to newNode
        current.link = newNode;
    }

    public T getCurrent()
    {
        if(current == null)
            return null;
        return current.data;
    }

    public T getAtIndex(int index)
    {
        int tempIndex = 0;
        ListNode temp = head;
        T ret=null;
        while(temp!=null)
        {
            if(tempIndex==index)
            {
                ret = temp.data;
                break;
            }
            else
            {
                tempIndex++;
                temp=temp.link;
            }
        }
        return ret;
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
        if(current == head)
        {
            head = head.link;
            current = head;
        }
        else
        {
            previous.link = current.link;
            current = current.link;
        }
    }

    public boolean contains(T data)
    {
        boolean contains=false;
        ListNode temp = head;
        while(temp!=null)
        {
            if(temp.data.equals(data))
            {
                contains=true;
                break;
            }
            temp=temp.link;
        }
        return contains;
    }

    public void removeAction(T remove)
    {
        ListNode current = head;
        while(current!=null)
        {
            if(current.data.equals(remove))
            {
                if(current == head)
                {
                    head = head.link;
                    current = head;
                }
                else
                {
                    previous.link = current.link;
                    current = current.link;
                }
                break;
            }
            previous = current;
            current=current.link;
        }
    }

    public void deleteList()
    {
        //deletes the entire linked list because of automatic garbage collection
    
        head = null;
    }
    //returning a boolean here will allow us to ensure that if the list is empty we can terminate printing immediately
    public void print(int i)
    {
        if(head==null)
        {
            System.out.println("empty list at priority "+i);
        }
        ListNode temp = head;
        while(temp!=null)
        {
            System.out.println(temp.data.toString());
            temp = temp.link;
        }
        
    }

    public T getData(int index)
    {
        ListNode temp = head;
        for(int i=0;i<index;i++)
            temp = temp.link;
        T data = temp.data;
        return data;
    }

    public int getSize()
    {
        int size = 0;
        ListNode temp = head;
        while(temp!=null)
        {
            size++;
            temp = temp.link;
        }
        return size;
    }
}
