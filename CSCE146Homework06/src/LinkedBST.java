/*
 * Written by Michelle Clark
 */
//takes interface and extends to type comparable, so binary must use comparable interface
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class LinkedBST <Shape extends Comparable <Shape>>//must redefine t to make sure comparable
{
    public static String fileName = "./output.txt";
    private class Node
    {
        Shape data;
        Node leftChild;
        Node rightChild;

        public Node(Shape aData)
        {
            data = aData;
            leftChild = rightChild = null;
        }
    }
    private Node root;//head
    public LinkedBST()
    {
        root = null;
    }
    public void add(Shape aData)
    {
        if(root==null)
            root = new Node(aData);
        else
            //TODO recursively add the element
            add(root,aData);
    }
    //separate method
    private Node add(Node aNode, Shape aData)
    {
        //TODO: ensure repeats can't be added
        //halting condition
        if(aNode == null)
            //we construct the node with the data
            aNode = new Node(aData);//reference to node we want to add
        //if node not null, if the data we're trying to add was less than the data we compared to go left
        else
        {
            //creating an int to store compareTo to make program run a bit faster
            int compareVal = aData.compareTo(aNode.data);
            if(compareVal<0)//GO LEFT
                aNode.leftChild = add(aNode.leftChild,aData);//recursive calls until finds left leaf
            else if(compareVal>0&&compareVal<1999)//GO RIGHT; error check implemented with less than 1999; will only ever return -1,1
                aNode.rightChild = add(aNode.rightChild,aData);
        }
        return aNode;
    }
    //callable method
    public void printPreorder()
    {
        printPreorder(root);
    }
    //recursive method
    public void printPreorder(Node aNode)
    {
        if(aNode == null)//halting condition
            return;
        System.out.println(aNode.data);//PROCESS
        printPreorder(aNode.leftChild);//LEFT
        printPreorder(aNode.rightChild);//RIGHT
    }

    public void printInorder()
    {
        printInorder(root);
    }
    private void printInorder(Node aNode)
    {
        if(aNode == null)
            return;
        printInorder(aNode.leftChild);//LEFT
        System.out.println(aNode.data);//PROCESS
        printInorder(aNode.rightChild);//RIGHT
    }

    public void printPostorder()
    {
        printPostorder(root);
    }
    private void printPostorder(Node aNode)
    {
        //TODO: check on meee
        if(aNode == null)
            return;
        printPostorder(aNode.leftChild);//LEFT
        printPostorder(aNode.rightChild);//RIGHT
        System.out.println(aNode.data);//PROCESS
    }

    public void printBFS()
    {
        printBFS(root);
    }
    private void printBFS(Node aNode)
    {
        if(aNode == null)
            return; 
    }
    
    public boolean search(Shape aData)
    {
        return search(root,aData);
    }
    private boolean search(Node aNode, Shape aData)
    {
        if(aNode==null)
            return false;//didn't find val
        else
        {
            //again making more efficient for debugging purposes
            int compareVal = aData.compareTo(aNode.data);
            //the compare to returns an integer val, if it is less than will be less than zero
            if(compareVal<0)//go left
                return search(aNode.leftChild,aData);
            else if(compareVal>0)//go right
                return search(aNode.rightChild,aData);
            else//they were equal
                return true;
        }
    }

    public boolean remove(Shape aData)
    {
        root = remove(root,aData);
        boolean removed = false;
        if(root.data==null)
            removed = false;
        else
            removed = true;
        return removed;
    }
    private Node remove(Node aNode, Shape aData)
    {
        //find node that contians this info, could implement search but we won't for simplicity
        //FIND THE NODE
        //TODO: we currently seem to remove the rectangle of len 5
        if(aNode==null)
            return null;
        else if(aData.compareTo(aNode.data)<0)
            aNode.leftChild = remove(aNode.leftChild,aData);
        else if(aData.compareTo(aNode.data)>0)
            aNode.rightChild = remove(aNode.rightChild,aData);
        else//found because = 0
        {
            if(aNode.rightChild==null)
                return aNode.leftChild;//if left child null will replace ref, if node did exist replace with left child
            else if(aNode.leftChild==null)
                return aNode.rightChild;//we know it's not null because would have halted above, exactly one child return ref
            //finding smallest val in right subtree
            Node temp = findMinInTree(aNode.rightChild);//find node with smallest val in nodes right subtree
            aNode.data = temp.data;//have replaced data with smallest val in subtree, have dup now
            aNode.rightChild = remove(aNode.rightChild,temp.data);//temp.data is the data we are trying to remove now
        }
        return aNode;
    }
    public void removeAboveArea(Double aData)
    {
        root = removeAboveArea(root,aData);
    }
    private Node removeAboveArea(Node in, Double aData)
    {
        //TODO: FIX MEEEE
        Node aNode = in;
        double nodeArea=-1;
        
        if(aNode==null)
            return null;
        //finding the node: step one must cast the shape it is to compare areas
        if(aNode.data instanceof Rectangle)
        {
            nodeArea = ((Rectangle)aNode.data).getArea();
        }
        else if(aNode.data instanceof Circle)
        {
            nodeArea = ((Circle)aNode.data).getArea();
        }
        else if(aNode.data instanceof RightTriangle)
        {
            nodeArea = ((RightTriangle)aNode.data).getArea();
        }

        //must first fix left and right subtrees of node
        aNode.leftChild = removeAboveArea(aNode.leftChild, aData);
        aNode.rightChild = removeAboveArea(aNode.rightChild, aData);

        //now fix the node
        //if node is above area then delete it
        if(nodeArea>aData)
            return deleteNode(aNode);

        //root out of range
        return aNode;
    }
    private Node deleteNode(Node root)
    {
        //node with only one child or no child
        if(root.leftChild==null)
        {
            Node child = root.rightChild;
            root = null;
            return child;
        }
        else if(root.rightChild==null)
        {
            Node child = root.leftChild;
            root = null;
            return child;
        }

        //node with 2 children case, must get inorder successor of right subtree
        Node next = leftMost(root.rightChild);
        //copy inorder successors data to parent node
        root.data = next.data;
        //delete inorder successor
        root.rightChild = deleteNode(root.rightChild);

        return root;
    }

    private Node leftMost(Node root)
    {
        if(root==null)
            return null;
        while(root.leftChild != null)
            root = root.leftChild;
        return root;
    }

    private Node findMinInTree(Node aNode)
    {
        if(aNode==null)
            return null;
        else if(aNode.leftChild==null)//is leaf
            return aNode;
        else
            return findMinInTree(aNode.leftChild);
    }

    public Shape findMaxInTree()
    {
        Node aNode = root;
        Node temp = findMaxInTree(aNode);
        Shape ret = temp.data;
        return ret;
    }
    private Node findMaxInTree(Node aNode)
    {
        if(aNode==null)
            return null;
        else if(aNode.rightChild==null)//is leaf
            return aNode;
        else
            return findMaxInTree(aNode.rightChild);
    }

    public void writeTreeInorder(String outFile) throws IOException
    {
        fileName = outFile;
        Node aNode = root;
        writeTreeInorder(aNode);
    }
    public void writeTreeInorder(Node root) throws IOException
    {
        try
        {
            //setting up the output printwriter
            FileWriter output = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(output);
            writeInorder(root, bw);
            bw.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
    
    public void writeInorder(Node focusNode, BufferedWriter bw) throws IOException
    {
        if(focusNode==null)
            return;
        writeInorder(focusNode.leftChild, bw);
        if(focusNode.data instanceof Rectangle)
        {
            Rectangle fNode = (Rectangle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof Circle)
        {
            Circle fNode = (Circle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof RightTriangle)
        {
            RightTriangle fNode = (RightTriangle)focusNode.data;
            bw.write((fNode).toFile());
        }
        writeInorder(focusNode.rightChild,bw);
    }

    public void writeTreePreorder(String outFile) throws IOException
    {
        fileName = outFile;
        Node aNode = root;
        writeTreePreorder(aNode);
    }
    public void writeTreePreorder(Node root) throws IOException
    {
        try
        {
            //setting up the output printwriter
            FileWriter output = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(output);
            writePreorder(root, bw);
            bw.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
    
    public void writePreorder(Node focusNode, BufferedWriter bw) throws IOException
    {
        if(focusNode==null)
            return;
        if(focusNode.data instanceof Rectangle)
        {
            Rectangle fNode = (Rectangle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof Circle)
        {
            Circle fNode = (Circle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof RightTriangle)
        {
            RightTriangle fNode = (RightTriangle)focusNode.data;
            bw.write((fNode).toFile());
        }
        writePreorder(focusNode.leftChild, bw);
        writePreorder(focusNode.rightChild,bw);
    }

    public void writeTreePostorder(String outFile) throws IOException
    {
        fileName = outFile;
        Node aNode = root;
        writeTreePostorder(aNode);
    }
    public void writeTreePostorder(Node root) throws IOException
    {
        try
        {
            //setting up the output printwriter
            FileWriter output = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(output);
            writePostorder(root, bw);
            bw.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
    
    public void writePostorder(Node focusNode, BufferedWriter bw) throws IOException
    {
        if(focusNode==null)
            return;
        writePostorder(focusNode.leftChild, bw);
        writePostorder(focusNode.rightChild,bw);
        if(focusNode.data instanceof Rectangle)
        {
            Rectangle fNode = (Rectangle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof Circle)
        {
            Circle fNode = (Circle)focusNode.data;
            bw.write((fNode).toFile());
        }
        if(focusNode.data instanceof RightTriangle)
        {
            RightTriangle fNode = (RightTriangle)focusNode.data;
            bw.write((fNode).toFile());
        }
    }
}
