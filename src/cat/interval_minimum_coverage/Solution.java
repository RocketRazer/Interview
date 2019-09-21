package cat.interval_minimum_coverage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 *
 *
 1668. Interval Minimum Coverage
 cat-only-icon
 CAT Only
 中文English
 Given n intervals, Output the minimal point numbers so that any one of the intervals contains at least one point.

 Example
 Given a=[(1,5),(4,8),(10,12)], return 2.

 Explain:
 Choose two points: 5,10,
 The first interval [1, 5] contains 5,
 The second interval [4,8] contains 5,
 The third interval [10,12] contains 10.
 Givena=[(1,5),(4,8),(5,12)], return 1.

 Explain:
 Choose one point: 5.
 The first interval [1, 5] contains 5,
 The second interval [4,8] contains 5,
 The third interval [5,12] contains 5.
 Notice
 1 \leq n \leq 1e41≤n≤1e4
 The maximum interval does not exceed 1e51e5

 */

/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

public class Solution {
    /**
     * @param a: the array a
     * @return: return the minimal points number
     */
    public int getAns(List<Interval> a) {
        if (a == null || a.size() == 0) {
            return 0;
        }

        Collections.sort(a, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                if (a.start == b.start) {
                    return a.end - b.end;
                } else {
                    return a.start - b.start;
                }
            }
        });

        int count = 1;

        int preEnd = a.get(0).end;
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i).start <= preEnd) {
                if (preEnd <= a.get(i).end) {
                    continue;
                } else {
                    preEnd = a.get(i).end;
                }
            } else {
                count++;
                preEnd = a.get(i).end;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        //(38,98),(12,73),(61,87),(77,87),(94,100),(13,83),(5,89),(53,71)

        List<Interval> list = new ArrayList<>();
        list.add(new Interval(38,98));
        list.add(new Interval(12,73));
        list.add(new Interval(61,87));
        list.add(new Interval(77,87));
        list.add(new Interval(94,100));
        list.add(new Interval(13,83));
        list.add(new Interval(5,89));
        list.add(new Interval(53,71));

        System.out.println(s.getAns(list));
    }
}

/**
 * Definition of Interval:
 *
 */

class Interval {
    int start, end;
     Interval(int start, int end) {
         this.start = start;
         this.end = end;
     }
}