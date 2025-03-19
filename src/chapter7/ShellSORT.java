package chapter7;

public class ShellSORT {
    // shellsort, using shells (poor) increments
    // a an array of comparable items
    public static <T extends Comparable<? super T >> void shellsort(T [] a)
    {
        int j;
        for(int gap=a.length/2; gap> 0; gap/=2)
            for(int i = gap; i <a.length; i++)
            {
                T temp= a[i];
                for(j=i; j>= gap &&temp.compareTo(a[j-gap]) < 0; j-= gap)
                    a[j] = a[j-gap];
                a[j]=temp;
            }
    }
}
