/*
 * Written by Michelle Clark
 */
public class LLQueue implements QueueI
{
    //singly linked list
    private class Node
    {
        Sort data;
        Node link;
        public Node(Sort aData, Node aLink)
        {
            data = aData;
            link = aLink;
        }
    }

    private Node head;
    private Node tail;
    private Node current;
    //don't need current or previous
    public LLQueue()
    {
        head = tail = null;
    }

    public void enqueue(Sort aData)
    {
        Node newNode = new Node(aData,null);
        if(head == null)
        {
            head = tail = newNode;
            return;
        }
        //point to something
        tail.link = newNode;
        tail = tail.link;
    }

    public Sort dequeue()
    {
        if(head == null)
            return null;
        Sort ret = head.data;
        head = head.link;
        return ret;
    }

    public Sort peek()
    {
        if(head==null)
            return null;
        return head.data;
    }

    public void print()
    {
        for(Node temp = head; temp != null; temp = temp.link)
            System.out.println(temp.data);
    }

    public int getSize()
    {
        int size=0;
        for(Node temp = head;temp!=null;temp=temp.link)
            size++;
        return size;
    }

    public void reset()
    {
        current = head;
    }

    public Sort getCurrent()
    {
        return current.data;
    }

    public Node merge(Node left, Node right)
    {
        //creating an auxiliary head node storing the 
        //head of the linked link to be returned and 
        //another pointer to remember where the last 
        //added node was
        Node dummyHead = new Node(null,null);
        Node current = dummyHead;
        
        while(left!=null && right!=null)
        {
            //because java doesn't like direct use of the method with left.data or right.data
            Sort leftS = left.data;
            Sort rightS = right.data;
            //while we're here might as well store the Sorts.getSorts() too to speed up processing time
            int leftSorts = leftS.getSorts();
            int rightSorts = rightS.getSorts();
            
            //if the left is smaller or equal to right
            if(leftSorts<=rightSorts)
            {
                //assign to left val
                current.link = left;
                left = left.link;
                current = current.link;
            }
            //if right smaller
            else
            {
                //assign to right value
                current.link = right;
                right = right.link;
                current = current.link;
            } 
        }
        while(left!=null)
        {
            current.link = left;
            left = left.link;
            current = current.link;
        }
        while(right!=null)
        {
            current.link=right;
            right=right.link;
            current=current.link;
        }
        return dummyHead.link;
    }
    //function to recursively divide the linked list
    public Node mergeSort(Node start)
    {
        if(start.link==null)
        {
            //only 1 element in current list so return as is
            return start;
        }
        //find middle of list
        Node mid=tortoiseAndHareApproach(start);
        Node startOfRight = mid.link;
        //breaking the linked list into two parts
        mid.link=null;

        Node left = mergeSort(start);
        Node right = mergeSort(startOfRight);

        Node newHead = merge(left,right);

        return newHead;
    }
    //interest function to calc midpoint of linked list
    public Node tortoiseAndHareApproach(Node head)
    {
        Node slow = head;
        Node fast = head.link;
        while(fast!=null && fast.link!=null)
        {
            slow = slow.link;
            //moves twice as fast to find end of list
            fast = (fast.link).link;
        }
        //slow must be point at the middle element of list
        return slow;
    }
}
