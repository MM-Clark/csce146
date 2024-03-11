/*
 * Written by Michelle Clark
 */
public class MinHeap <T extends Comparable<T>>
{
    private T[] heap;
    public static final int DEF_SIZE = 128;
    private int lastIndex;//first null element
    private int size;
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
            if(index > 0 && parent.compareTo(child)>0)//parent is greater than child must swap
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
            if(index*2+2 < lastIndex && leftChild.compareTo(rightChild)>0)
                smallIndex = index*2+2;
            Sheep oldSmallest = (Sheep) heap[index];
            Sheep newSmallest = (Sheep) heap[smallIndex];
            if(oldSmallest.compareTo(newSmallest)>0)
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
        MinHeap<T> mHeap = new MinHeap<T>(array.length);
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
    // public void printHeap()
    // {
    //     //TODO: figure out to format this guy
    //     int size = heap.length;
    //     int maxDepth = (int) (Math.log(size) / Math.log(2));
    //     int layerLength = 0;
    //     int d = -1;

    //     StringBuilder hsb = new StringBuilder();//heap string builder
    //     for(d=maxDepth;d>=0;d--)//number of layers built backwards
    //     {
    //         layerLength = (int) Math.pow(2, d);//numbers per layer

    //         StringBuilder line = new StringBuilder();//line string builder
    //         for(int i=layerLength;i<(int) Math.pow(2,maxDepth-d);i++)
    //         {
    //             //add indents before last layer
    //             if(d!=maxDepth)
    //             {
    //                 line.append(" ".repeat((int) Math.pow(2,maxDepth-d)));
    //             }
    //             //extra spaces for long lines
    //             int loops = maxDepth-d;
    //             if(loops>=2)
    //             {
    //                 loops-=2;
    //                 while(loops>=0)
    //                 {
    //                     line.append(" ".repeat((int) Math.pow(2,loops)));
    //                     loops--;
    //                 }
    //             }
    //             //insert data
    //             if(i<=size)
    //             {
    //                 Sheep temp = (Sheep) heap[i];
    //                 //add leading zeros
    //                 if(temp!=null)
    //                     line.append(String.format("%-2s",temp.toString()));
    //             }
    //             else
    //             {
    //                 line.append("--");
    //             }

    //             line.append(" ".repeat((int) Math.pow(2,maxDepth-d)));//after spaces
    //             //extra spaces for long lines
    //             loops = maxDepth - d;
    //             if(loops>=2)
    //             {
    //                 loops-=2;
    //                 while(loops>=0)
    //                 {
    //                     line.append(" ".repeat((int) Math.pow(2,loops)));
    //                     loops--;
    //                 }
    //             }
    //         }
    //         hsb.insert(0, line.toString()+"\n");//prepend line
    //     }
    //     System.out.println(hsb.toString());
    // }
    // public void displayHeap()
    // {
    //     System.out.print("heapArray: ");    // array format
    //     int size = heap.length;
    //     for(int m=0; m<size; m++)
    //     if(heap[m] != null)
    //     {
    //         Sheep tempM = (Sheep) heap[m];
    //         System.out.print( tempM.toString() + " ");
    //     }
    //     else
    //         System.out.print( "-- ");
    //     System.out.println();
    //                                       // heap format
    //     int nBlanks = 32;
    //     int itemsPerRow = 1;
    //     int column = 0;
    //     int j = 0;                          // current item
    //     String dots = "...............................";
    //     System.out.println(dots+dots);      // dotted top line

    //     while(size > 0)              // for each heap item
    //     {
    //     if(column == 0)                  // first item in row?
    //         for(int k=0; k<nBlanks; k++)  // preceding blanks
    //            System.out.print(' ');
    //                                       // display item
    //     Sheep tempJ = (Sheep) heap[j];
    //     System.out.print(tempJ.toString());

    //     if(++j == size)           // done?
    //         break;

    //     if(++column==itemsPerRow)        // end of row?
    //     {
    //         nBlanks /= 2;                 // half the blanks
    //         itemsPerRow *= 2;             // twice the items
    //         column = 0;                   // start over on
    //         System.out.println();         //    new row
    //     }
    //     else                             // next item on row
    //         for(int k=0; k<nBlanks*2-2; k++)
    //            System.out.print(' ');     // interim blanks
    //     }  // end for loop
    //     System.out.println("\n"+dots+dots); // dotted bottom line
    // }  // end displayHeap()
}
