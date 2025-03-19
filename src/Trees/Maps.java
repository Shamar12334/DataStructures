package Trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Maps
{
    public static void printHighChangeables(Map<String, List<String>> adjWords, int minWords)
    {
        for( Map.Entry <String, List<String>> entry : adjWords.entrySet())
        {
            List <String> words = entry.getValue();
            if(words.size() >= minWords)
            {
                System.out.println(entry.getKey()+ "(");
                System.out.println(words.size()+"):");
                for(String w : words)
                    System.out.println(" "+w);
                System.out.println();
            }
        }
    }
    //returns true if word1 and word2 are the same length\
    // and differ in only one character
    private static boolean oneCharOff(String word1, String word2)
    {
        if(word1.length() != word2.length())
            return false;
        int diffs= 0;
        for(int i=0; i < word1.length(); i++)
            if(word1.charAt(i) != word2.charAt(i))
                if(++diffs>1)
                    return false;
        return diffs ==1;
    }
    //computees map in which the key are worda and values are lists of words
    // that diffewr in only oione character from the corresponding
    // uses a quadratic algorithm (with appropriate Map)
//    public static Map<String, List<String >> computeAdjacentWords(List<String> theWords)
//        {
//            Map <String,List<String>> adjWords = new TreeMap<>();
//            Map<Integer,List<String>> wordsByLength = new TreeMap<>();
//            //group the words by their length
//            for(String w : theWords)
//                update(wordsByLength, w.length(),w);
//            //work on each group seperately
//            for(List<String> groupsWords : wordsByLength.values())
//            {
//                String [] words = new String [groupsWords.size()];
//                groupsWords.toArray(words);
//                for(int i=0; i< words.length; i++)
//                    for(int j=i+1; j< words.length; j++)
//                        if(oneCharOff(words[i],words[j]))
//                        {
//                            update(adjWords,words[i],words[j]);
//                            update(adjWords,words[j],words[i]);
//                        }
//            }
//            return adjWords;
//        }
    public static Map<String,List<String>>
 computeAdjacentWords( List<String> words )
 {
     Map<String,List<String>> adjWords = new TreeMap<>( );
     Map<Integer,List<String>> wordsByLength = new TreeMap<>( );

     // Group the words by their length
     for( String w : words )
         update( wordsByLength, w.length( ), w );

     // Work on each group separately
     for( Map.Entry<Integer,List<String>> entry : wordsByLength.entrySet( ) )
         {
         List<String> groupsWords = entry.getValue( );
         int groupNum = entry.getKey( );

         // Work on each position in each group
         for( int i = 0; i < groupNum; i++ )
             {
                // Remove one character in specified position, computing
               // representative. Words with same representative are
                // adjacent, so first populate a map ...
                Map<String,List<String>> repToWord = new TreeMap<>( );

                 for( String str : groupsWords )
                 {
                   String rep = str.substring( 0,i)+ str.substring( i + 1 );
                    update( repToWord, rep, str );
                 }

                // and then look for map values with more than one string
               for( List<String> wordClique : repToWord.values( ) )
                 if( wordClique.size( ) >= 2 )
                     for( String s1 : wordClique )
                        for( String s2 : wordClique )
                          if( s1 != s2 )
                             update( adjWords, s1, s2 );
               }
             }

     return adjWords;
     }
        private static <KeyType> void update(Map<KeyType,List<String>> m, KeyType key, String value)
        {
                List <String> lst = m.get(key);
                if(lst == null)
                {
                    lst = new ArrayList<>();
                    m.put(key,lst);
                }
                lst.add(value);
        }
}
