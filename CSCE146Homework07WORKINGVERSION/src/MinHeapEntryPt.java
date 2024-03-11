/*
 * Written by Michelle Clark
 */
public class MinHeapEntryPt <T extends Comparable<T>>
{
    private T[] heap;
    public static final int DEF_SIZE = 128;
    private int lastIndex;//first null element
    private int size;
    public MinHeapEntryPt()
    {
        init(DEF_SIZE);
    }
    public MinHeapEntryPt(int size)
    {
        init(size);
    }
    private void init(int size)
    {
        if(size>0)
        {
            heap = (T[])(new Comparable[size]);
            size=1;
            lastIndex = 0;//first null element is the root
        }
        else
            init(DEF_SIZE);
    }
    public void add(T aData)
    {
        if(lastIndex >= heap.length)
            return;
        heap[lastIndex] = aData;//assign last null element to data
        bubbleUp();
        lastIndex++;
        size++;
    }
    private void bubbleUp()
    {
        int index = lastIndex;
        //no need to check if instanceof bc can assume all are Sheep
        boolean doneSwapping = false;
        //done this way to update the parent each time
        while(!doneSwapping)
        {
            Sheep parent = (Sheep) heap[((index-1)/2)];
            Sheep child = (Sheep) heap[index];
            if(index > 0 && parent.compareTo2(child)>0)//parent is greater than child must swap
            {
                //just like max but change sign
                T temp = heap[(index-1)/2];
                heap[(index-1)/2] = heap[index];
                heap[index] = temp;
                index = ((index-1)/2);  
            }
            //indicates we are in the right location
            else
                doneSwapping = true;
        }
    }
    public T remove()
    {
        if(lastIndex == 0)
            return null;//empty tree
        T ret = heap[0];
        heap[0] = heap[lastIndex-1];//lastIndex is first null element
        heap[lastIndex-1]=null;
        lastIndex--;
        size--;
        bubbleDown();
        return ret;
    }
    private void bubbleDown()
    {
        int index = 0;
        while(index*2+1 < lastIndex)
        {
            int smallIndex = index*2+1;
            Sheep leftChild = (Sheep) heap[index*2+1];
            Sheep rightChild = (Sheep) heap[index*2+2];
            if(index*2+2 < lastIndex && leftChild.compareTo2(rightChild)>0)
                smallIndex = index*2+2;
            Sheep oldSmallest = (Sheep) heap[index];
            Sheep newSmallest = (Sheep) heap[smallIndex];
            if(oldSmallest.compareTo2(newSmallest)>0)
            {
                T temp = heap[index];
                heap[index] = heap[smallIndex];
                heap[smallIndex] = temp;
            }
            else
                break;
            index = smallIndex;
        }
    }
    public void print()
    {
        //this is the correct way bc it's breadth first!
        for(T h : heap)
        {
             if(h==null)
                 break;
            System.out.println(h.toString());
        }
        // for(int i=0;i<=size/2;i++)
        // {
        //     System.out.print("PARENT: "+heap[i]+" LEFT CHILD: "+heap[2*i+1]+" RIGHT CHILD: "+heap[2*i+2]+"\n"+"\n");
        // }
    }
    public void heapSort(T[] array)
    {
        if(array==null)
            return;
        MinHeapEntryPt<T> mHeap = new MinHeapEntryPt<T>(array.length);
        for(int i=0;i<array.length;i++)
            mHeap.add(array[i]);
        for(int i=0;i<array.length;i++)
            array[i]=mHeap.remove();
    }
    public int getSize()
    {
        return this.size;
    }
    public boolean hasMore()
    {
        return heap[0]!=null;
    }
    public T peekHead()
    {
        return heap[0];
    }
}
