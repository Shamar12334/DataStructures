package chapter7;

public class InsertSort {
    //simplest insertion sort
    // a an array of comparable items
    public static <T extends Comparable <? super T>> void insertionSort(T [] a)
    {
        int j;
        for(int p = 1; p < a.length; p++)
        {
            T temp=a[p];
            for(j=p; j> 0 && temp.compareTo(a[j-1]) < 0; j++)
                a[j] = a[j-1];
            a[j] =temp;
        }
    }
}
