package data_structure.linked_hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {

        /**
         * The entries of a LinkedHashMap can be iterated either in the order the keys were first added to
         * the Map (that's the default behavior) or according to access order
         * (i.e. the most recently accessed entry will be the last entry iterated over).
         *
         * By passing true to the accessOrder parameter in that constructor,
         * you are saying you wish to iterate over the entries according to access order (and not insertion order).
         *
         *
         * Default initial capacity of the HashMap takes is 16 and load factor is 0.75f (i.e 75% of current map size).
         * The load factorrepresents at what level the HashMap capacity should be doubled. For example product of capacity
         * and load factor as 16 * 0.75 = 12 . Every time 12 more elements added to map.
         */
        Map<Integer,String> insertOrder = new LinkedHashMap<>(16,0.75f,false);
        Map<Integer,String> accessOrder = new LinkedHashMap<>(16,0.75f,true);

        insertOrder.put (1,"a");
        insertOrder.put (3,"c");
        insertOrder.put (2,"b");
        String v = insertOrder.get(3);

        // {1=a, 3=c, 2=b}
        System.out.println(insertOrder);


        accessOrder.put (1,"a");
        accessOrder.put (3,"c");
        accessOrder.put (2,"b");
        v = accessOrder.get(3);


        //{1=a, 2=b, 3=c}
        System.out.println(accessOrder);
    }
}
