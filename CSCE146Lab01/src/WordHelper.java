/*
 * Written by Michelle Clark
 */
public class WordHelper 
{
    public static String[] sortByVowels(String array[])
    {
        String word ="";
        int count = 0;
        int lengthOfArray = array.length;
        String temp;
        int lengthOfWord;

        int[]temporaryVals=new int[array.length];
        String[]sortedArray= array.clone();
        //add space to end of words to indicate the end of the word
        for(int i=0;i<sortedArray.length;i++)
        {
            sortedArray[i]+=" ";
        }

        for(int i=0;i<lengthOfArray;i++)
        {
            temp=sortedArray[i];
            lengthOfWord = temp.length();
            for(int j=0;j<lengthOfWord;j++)
            {
                //makes case not matter because the entry character and comparison character are both uppercase
                char ch = Character.toUpperCase(sortedArray[i].charAt(j));
                //check if vowel and increase count if so
                if(ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'||ch=='Y')
                {
                    count++; 
                }
                //if the character is a space, we know we've hit the end of the word
                if(ch==' ')
                {
                    //so we store count as a temp value then assign the corresponding index
                    //of temporary vals to be temp2 
                    int temp2 = count;
                    temporaryVals[i]=temp2;
                    //we reset our word placeholder as well as count
                    word="";
                    count=0;
                }
                else
                {
                    //shows progression of word for debugging
                    word = word+ch;
                }
            }
        }

        boolean hasSwapped = true;

        while(hasSwapped)
        {
            //if no swaps occur we will exit the loop
            hasSwapped = false;
            for(int i=0;i<temporaryVals.length-1;i++)
            {
                //performs bubble sort on the array
                if(temporaryVals[i]>temporaryVals[i+1])
                {
                    //storing the count and string temporarily
                    int tempCount = temporaryVals[i];
                    String tempString = sortedArray[i];

                    //assigning left to right index vals
                    temporaryVals[i]=temporaryVals[i+1];
                    sortedArray[i]=sortedArray[i+1];

                    //assigning the right index vals to temp vals which were previous vals of left index
                    temporaryVals[i+1]=tempCount;
                    sortedArray[i+1]=tempString;

                    //indicates to continue loop
                    hasSwapped = true;
                }
            }
        }

        //take out the extra spaces added to the words
        for(int i=0;i<sortedArray.length;i++)
        {
            String holder = sortedArray[i];
            holder=holder.substring(0,holder.length()-1);
            sortedArray[i]=holder;
        }

        return sortedArray;
    }

    public static String[] sortByConsonants(String array[])
    {
        String word ="";
        int count = 0;
        int lengthOfArray = array.length;
        String temp;
        int lengthOfWord;

        int[]temporaryVals=new int[array.length];
        String[]sortedArray= array.clone();
        //add space to end of words
        for(int i=0;i<sortedArray.length;i++)
        {
            sortedArray[i]+=" ";
        }

        for(int i=0;i<lengthOfArray;i++)
        {
            temp=sortedArray[i];
            lengthOfWord = temp.length();
            for(int j=0;j<lengthOfWord;j++)
            {
                char ch = Character.toUpperCase(sortedArray[i].charAt(j));
                if(ch=='A'||ch=='E'||ch=='I'||ch=='O'||ch=='U'||ch=='Y')
                {
                    count+=0; 
                }
                //if ch is not a val or space, it must be a consonsant, could indicate if not special char 
                //but isn't necessary in this list of words
                else if (ch!=' ') 
                {
                    count++;
                }
                if(ch==' ')
                {
                    temporaryVals[i]=count;
                    word="";
                    count=0;
                }
                else
                {
                    word = word+ch;
                }
            }
        }

        boolean hasSwapped = true;

        while(hasSwapped)
        {
            hasSwapped = false;
            for(int i=0;i<temporaryVals.length-1;i++)
            {
                if(temporaryVals[i]>temporaryVals[i+1])
                {
                    int tempCount = temporaryVals[i];
                    String tempString = sortedArray[i];

                    temporaryVals[i]=temporaryVals[i+1];
                    sortedArray[i]=sortedArray[i+1];

                    temporaryVals[i+1]=tempCount;
                    sortedArray[i+1]=tempString;

                    hasSwapped = true;
                }
            }
        }

        //take out the extra spaces added to the words
        for(int i=0;i<sortedArray.length;i++)
        {
            String holder = sortedArray[i];
            holder=holder.substring(0,holder.length()-1);
            sortedArray[i]=holder;
        }

        return sortedArray;
    }   

    public static String[] sortByLength(String []words)
    {
        String[] words1 = words.clone();
        //this uses insertion sort 
        int length = words1.length;

        for(int i=0;i<length;i++)
        {
            //taking a temporary string of words at the index
            //store temporary string of words at i
            String temp = words1[i];

            //insert s[j] at correct position
            //j is the position left of i
            int j = i - 1;
            
            //comparing to all left indices while words at i's length is less than words at j's length
            while(j >= 0 && temp.length() < words1[j].length())
            {
                //swap the righter with lefter value
                words1[j+1] = words1[j];
                j--;
            }
            words1[j+1] = temp;
        }
        return words1; 
    }

}
