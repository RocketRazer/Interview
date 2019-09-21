package a_lintcode.basic.wk2;

import java.util.ArrayList;

/**
 * 题目：

 Given a big sorted array with positive integers sorted by ascending order. The array is so big so that you can not get the length of the whole array directly,
 and you can only access the kth number by ArrayReader.get(k) (or ArrayReader->get(k) for C++). Find the first index of a target number.

 Your algorithm should be in O(log k), where k is the first index of the target number.
 Return -1, if the number doesn't exist in the array.

 *
 *  exponential backoff
 *  dynamic arrays 当发现数组的size不够大的时候就变大
 *
 * */
public class SearchInABigSortedArray {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */

    public int searchBigSortedArray(ArrayReader reader, int target) {

        int index = 1;

        while (reader.get(index - 1) != -1 && reader.get(index - 1) < target) {
            index = index * 2;
        }

        // target 大到不在数组里
        if (reader.get(index - 1) == -1) {
            return -1;
        }

        int start = 0;      // could we use index / 2 ?
        int end = index - 1; // why -1?

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            if (reader.get(mid) > target) {
                end = mid;
            } else if (reader.get(mid) < target) {
                start  = mid;
            } else if (reader.get(mid) == target){
                end = mid;
            }
        }

        if ((reader.get(start) == target)) {
            return  start;
        }

        if ((reader.get(end) == target)) {
            return  end;
        }

        return -1;
    }




    class ArrayReader {
        private ArrayList<Integer> arrayList = new ArrayList();

        public int get (int n) {
            return arrayList.get(n);
        }
    }
}
