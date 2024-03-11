/*
 * Written by Michelle Clark
 */
public class Sorter 
{
    public static final String sort = "SORT";
    public LLQueue sortSorts(LLQueue q)
    {
        LLQueue ret;
        ret = new LLQueue();
        //must be stored as int value to avoid depreciation with dequeueing
        int size = q.getSize();
        q.reset();
        for(int i=0;i<size;i++)
        {
            //getting the next string from the queue
            Sort temp1 = q.dequeue();
            String name = temp1.getName();
            int sorts = countSorts(name);
            //store the string and its number of sorts in class
            Sort temp = new Sort(name,sorts);
            ret.enqueue(temp);
        }
        ret.print();
        return ret;
        //now we need to count sorts from least to most with O(nlg(n))//(feta(nlg(n)) time complexity)
        //so we should use merge sort or quick sort or heap sort
    }
    
    public int countSorts(String name)
    {
        //convert the string to upper case to compare
        String compare = name.toUpperCase();
        int count = 0;

        //check for if the word contains "SORT"
        if(compare.contains(sort))
        {
            for(int i=0;i<compare.length();i++)
            {
                //maybe implement recursion later on
                if(compare.charAt(i)=='S')
                {
                    int j=i+1;
                    if(compare.charAt(j)=='O')
                    {
                        int k=i+2;
                        if(compare.charAt(k)=='R')
                        {
                            int m = i+3;
                            if(compare.charAt(m)==('T'))
                            {
                                count++;
                                //incremeting i up by 3 to skip over character o,r,t,
                                i+=3;
                            }
                        }
                    }
                }
                
            }
        }
        return count;
    }

    //void not int[]array return type
    //since int[] is an object type int[]a is a reference to the actual array
    //modifies array for us
    public void mergeSort(LLQueue in)
    {
        int size = in.getSize();
        //populating a string array with the queue data
        Sort[] a = new Sort[size];
        for(int i=0;i<size;i++)
        {
            a[i]=in.dequeue();
        }
        mergeSort(a);
        print(a);
    }
    public void mergeSort(Sort[] a)
    {
        int size = a.length;
        if(size<2)
            return;
        int mid = size / 2;
        //two arrays left right must find size
        int leftSize = mid;
        int rightSize = size - mid;

        //needs to become another class array
        Sort[] left = new Sort[leftSize];
        Sort[] right = new Sort[rightSize];;

        //populate left array
        for(int i=0;i<mid;i++)
            left[i]=a[i];
        
        //populate right array
        for(int i=mid;i<size;i++)
        {
            //accessing the first element of right
            right[i-mid]=a[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(left,right,a);
    }
    public void merge(Sort[] left,Sort[] right,Sort[] a)
    {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0;//left array's index
        int j = 0;//right array's index
        int k = 0;//merged arrays (a) index

        //examine vals on right and left hand side and determining smallest
        while(i<leftSize && j<rightSize)
        {
            if(left[i].getSorts() <= right[j].getSorts())
            {
                //assign to left val
                a[k] = left[i];
                i++;
                k++;
            }
            //indicates right is smaller
            else
            {
                //assign to right val
                a[k] = right[j];
                j++;
                k++;
            }
        }
        while(i<leftSize)
        {
            a[k]=left[i];
            i++;
            k++;
        }
        while(j<rightSize)
        {
            a[k]=right[j];
            j++;
            k++;
        }
    }
    public void print(Sort[] a)
    {
        int length = a.length;
        for(int i=0;i<length;i++)
        {
            Sort temp = a[i];
            System.out.println(temp.toStringName());
        }
    }
}
