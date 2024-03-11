/*
 * Written by Michelle Clark
 */
//extends type comparable to allow us to compare elements
public class MinHeap <T extends Comparable<T>>
{
    private T[] heap;
    public static final int DEF_SIZE = 128;
    private int lastIndex;//first null element
    public MinHeap()
    {
        init(DEF_SIZE);
    }
    public MinHeap(int size)
    {
        init(size);
    }
    private void init(int size)
    {
        if(size>0)
        {
            //cannot be type object; must be comparable
            heap = (T[])(new Comparable[size]);
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
    }
    private void bubbleUp()
    {
        int index = lastIndex;
        //no need to check if instanceof bc all are Orders in heap
        //must cast to child type to override T comparator bc that's just how java works
        //otherwise you will use the T comparator
        int parentIdx = (index-1)/2;
        Order parent = (Order) heap[(parentIdx)];
        Order child = (Order) heap[index];
        while(index > 0 && parent.compareTo(child)>0)//parent is greater than child must swap
        {
            //just like max but change sign
            T temp = heap[(index-1)/2];
            heap[(index-1)/2] = heap[index];
            heap[index] = temp;
            index = (index-1)/2;
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
        bubbleDown();
        return ret;
    }
    private void bubbleDown()
    {
        int index = 0;
        while(index*2+1 < lastIndex)
        {
            //left child index
            int smallIndex = index*2+1;
            Order leftChild = (Order)heap[index*2+1];
            Order rightChild = (Order)heap[index*2+2];
            //if the right index is less than left index we set it to the smallest index
            if(index*2+2 < lastIndex && leftChild.compareTo(rightChild)>0)
                smallIndex = index*2+2;
            
            Order smallOrder = (Order)heap[smallIndex];
            Order peak = (Order)heap[index];
            //if the parent is greater than the child then we swap
            if(peak.compareTo(smallOrder)>0)
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
        for(T h : heap)
        {
            if(h==null)
                break;
            System.out.println(h);
        }
    }
    public void heapSort(T[] array)
    {
        if(array==null)
            return;
        MinHeap<T> mHeap = new MinHeap<T>(array.length);
        for(int i=0;i<array.length;i++)
            mHeap.add(array[i]);
        for(int i=0;i<array.length;i++)
            array[i]=mHeap.remove();
    }
}
