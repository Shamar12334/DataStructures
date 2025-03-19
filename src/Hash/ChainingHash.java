
import java.util.LinkedList;
import java.util.List;

public class ChainingHash <T>
{
    // contructs hash table
    public ChainingHash()
    {
        this(DEFAULT_TABLE_SIZE);
    }
    // contruct the hash table and approximates table size
    public ChainingHash(int size)
    {
        theLists = new LinkedList[nextPrime(size)];
        for(int i =0; i < theLists.length; i++)
            theLists[i] = new LinkedList<>();
    }
    //insert into the hash table. if  the item is
    // already present,then do nothing
    // x the item to insert
    public void insert(T x)
    {
        List<T> whichList = theLists[myHash(x)];
        if(!whichList.contains(x))
        {
            whichList.add(x);
            //rehash, see section 5.5
            if(++currentSize > theLists.length)
                rehash();
        }
    }
    // remove from the hash table
    // x the item to remove
    public void remove(T x)
    {
        List<T> whichList = theLists[myHash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }
    // find an item in the hash Table
    // x the item to search for
    // true if x is not found
    public Boolean contains(T x)
    {
        List<T> whichList = theLists[myHash(x)];
        return whichList.contains(x);
    }
    //make the logic empty
    public void makeEmpty()
    {
        for(int i= 0; i< theLists.length; i++)
            theLists[i].clear();
        currentSize= 0;
    }
    private static final int DEFAULT_TABLE_SIZE=101;
    private List<T>[] theLists;
    private int currentSize;
    private void rehash(){/*5.22*/}
    private int myHash(T x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if(hashVal< 0)
            hashVal+= theLists.length;
        return hashVal;
    }
//    private static int nextPrime(int n){/*see code online*/}
//    private static boolean isPrime(int n){/*see code online*/}
}
