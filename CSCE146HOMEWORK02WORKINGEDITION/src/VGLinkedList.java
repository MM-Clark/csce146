/*
 * Written by Michelle Clark
 */
public class VGLinkedList <T>
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

    public VGLinkedList()
    {
        head = current = previous = null;
    }

    public void add(T aData)
    {
        ListNode newNode = new ListNode(aData, null);
        
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
        ListNode newNode = new ListNode(aData,current.link);
        current.link = newNode;
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
    
    public VGLinkedList<VideoGame> findMatch(VideoGame other, String name, String console,VGLinkedList<VideoGame> results)
    {
        current = head;
        
        VGLinkedList<VideoGame> copyOfResults = new VGLinkedList<>();
        while(current!=null)
        {
            VideoGame temp = (VideoGame) current.data;
            //for each index, if the name and console are both '*' then we want to store the value
            //regardless of its name or console
            if(name.contains("*")&&console.contains("*"))
            {
                VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                System.out.println(ret.toString());
                copyOfResults.add(ret);
            }
            //otherwise, if only name contains "*", we want to check if the VideoGame contains in console 
            //to the search VideoGame
            else if(name.contains("*"))
            {
                if(temp.containsConsole(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
            //otherwise if only console contains "*" we want to see if the VideoGame contains the name
            //of the search VideoGame
            else if(console.contains("*"))
            {
                if(temp.containsName(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
            //otherwise if neither name or console contains "*" we want to make sure the result
            //contains both name and console
            else
            {
                if(temp.containsName(other)&&temp.containsConsole(other))
                {
                    VideoGame ret = new VideoGame(temp.getName(),temp.getConsole());
                    System.out.println(ret.toString());
                    copyOfResults.add(ret);
                }
            }
            current=current.link;
        }
        //informing the user if size is 0
        if(copyOfResults.getSize()==0)
            System.out.println("~la busca devuelve nada; lo siento por su perdida~\n(the search returned nothing, I'm sorry for your loss)");
        return copyOfResults;
    }
    //functional code
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
        if(head==null)
            return 0;
        int size = 1;
        current = head;
        while(current.link!=null)
        {
            size++;
            current = current.link;
        }
        return size;
    }
}
