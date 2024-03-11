/*
 * Written by Michelle Clark
 */
public class Test 
{
    public static double[] testVals = {0,10.0,3.5,45,6.7};
    public static void main(String[] args) 
    {
        DoubleDoubleLL test2 = new DoubleDoubleLL();
        for(int i=0;i<testVals.length;i++)
            test2.add(testVals[i]);
        
        test2.gotoNext();
        test2.print();
        test2.gotoPrev();
        test2.gotoPrev();
        test2.setCurrent(9);
        System.out.println(test2.getCurrent());
        test2.gotoNext();
        System.out.println(test2.getCurrent());
        System.out.println(test2.hasMore());
        System.out.println(test2.hasPrev());
        test2.removeCurrent();
        test2.gotoEnd();
        System.out.println(test2.getSize());
        System.out.println(test2.contains(10.0));
        test2.print();
    }
}
