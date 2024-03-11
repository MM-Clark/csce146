/*
 * Written by Michelle Clark
 */
//takes interface and extends to type comparable, so binary must use comparable interface
public class LinkedBST <T extends Comparable<T>>//must redefine t to make sure comparable
{
    private class Node
    {
        T data;
        Node leftChild;
        Node rightChild;

        public Node(T aData)
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
    public void add(T aData)
    {
        if(root==null)
            root = new Node(aData);
        else
            //recursively add the element
            add(root,aData);
    }
    //separate method
    private Node add(Node aNode, T aData)
    {
        //halting condition
        if(aNode == null)
            //we construct the node with the data
            aNode = new Node(aData);//reference to node we want to add
        //if node not null, if the data we're trying to add was less than the data we compared to go left
        else if(aData.compareTo(aNode.data)<0)//GO LEFT
            aNode.leftChild = add(aNode.leftChild,aData);//recursive calls until finds left leaf
        else if(aData.compareTo(aNode.data)>0)//GO RIGHT
            aNode.rightChild = add(aNode.rightChild,aData);
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
        if(aNode == null)
            return;
        printPostorder(aNode.leftChild);
        printPostorder(aNode.rightChild);
        System.out.println(aNode.data);
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
    public boolean search(T aData)
    {
        return search(root,aData);
    }
    private boolean search(Node aNode, T aData)
    {
        if(aNode==null)
            return false;//didn't find val
        //the compare to returns an integer val, if it is less than will be less than zero
        else if(aData.compareTo(aNode.data)<0)//go left
            return search(aNode.leftChild,aData);
        else if(aData.compareTo(aNode.data)>0)//go right
            return search(aNode.rightChild,aData);
        else//they were equal
            return true;
    }
    public void remove(T aData)
    {
        root = remove(root,aData);
    }
    private Node remove(Node aNode, T aData)
    {
        //find node that contians this info, could implement search but we won't for simplicity
        //FIND THE NODE
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
    private Node findMinInTree(Node aNode)
    {
        if(aNode==null)
            return null;
        else if(aNode.leftChild==null)//is leaf
            return aNode;
        else
            return findMinInTree(aNode.leftChild);
    }
}
