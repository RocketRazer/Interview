package airbnb.onsite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给一组meetings(每个meeting由start和end时间组成)。求出在所有输入meeting时间段内没有会议，也就是空闲的时间段.
 * 每个subarray都已经sort好。N个员工，每个员工有若干个interval表示在这段时间是忙碌的。求所有员工都不忙的intervals。
 * For example:
 * [
 * [[1, 3], [6, 7]],
 * [[2, 4]],
 * [[2, 5], [9, 12]]
 * ]
 * Output
 * [[4, 6], [7, 9]]
 *
 *
 * follow up:
 * 求不少于k个员工空闲的时间段（改一下check count的条件就可以了）
 */
public class EmployeeFreeTime {
    /**
     * find out all the intervals that has no meetings
     */
    public static List<Interval> getAvailableIntervals(List<List<Interval>> intervals) {
        List<Interval> res = new ArrayList<>();

        List<Point> points = new ArrayList<>();
        for (List<Interval> intervalList : intervals) {
            for (Interval interval : intervalList) {
                points.add(new Point(interval.start, true));
                points.add(new Point(interval.end, false));
            }
        }

        Collections.sort(points);

        int count = 0;
        Integer availableEnd = null;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.isStart) {
                //availableEnd < point.time 保证 start 和 end是同一个时间点的话不会被加到result里去
                if (availableEnd != null && count == 0 && availableEnd < point.time) {
                    res.add(new Interval(availableEnd, point.time));
                }
                count++;
            } else { // end point
                count--;
                //注意这里还有check availableEnd == null 第一次
                if (availableEnd == null || point.time > availableEnd) {
                    availableEnd = point.time;
                }
            }
        }

        return res;
    }

    /**
     * find all the intervals that has least k people have no meetings
     *
     * @param intervals
     * @param k
     * @return
     */
    public static List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
        List<Interval> res = new ArrayList<>();

        List<Point> points = new ArrayList<>();
        for (List<Interval> intervalList : intervals) {
            for (Interval interval : intervalList) {
                points.add(new Point(interval.start, true));
                points.add(new Point(interval.end, false));
            }
        }

        Collections.sort(points);

        int count = 0;
        Integer availableStartTime = null;
        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (point.isStart) {
                count++;
                // 如果开始时间为null 且当前不工作的人数 >= k, set availableStartTime 为当前时间
                if (availableStartTime == null && intervals.size() - count >= k) {
                    availableStartTime = point.time;
                }

                // 只有加点点时候才有可能超出工作的人数
                if (intervals.size() - count < k && availableStartTime != null) {
                    res.add(new Interval(availableStartTime, point.time));
                    availableStartTime = null;
                }

            } else {
                count--;
                // 如果开始时间为null 且当前不工作的人数 >= k, set availableStartTime 为当前时间
                if (availableStartTime == null && intervals.size() - count >=  k) {
                    //这里只需要availableStart == null 才更新， 如果不是null 说前面有更小的start time
                    availableStartTime = point.time;
                }

                // handler the last end point
                if (i == points.size() - 1 && availableStartTime != null && intervals.size() - count >=  k) {
                    //注意这里还有check availableStart 和现在这个点是不是相同的， 如果是相同的就不用加了
                    if (availableStartTime != point.time) {
                        res.add(new Interval(availableStartTime, point.time));
                    }
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        List<List<Interval>> intervals = new ArrayList<>();
        List<Interval> inter1 = new ArrayList<>();
        inter1.add(new Interval(1, 3));
        inter1.add(new Interval(6, 7));
        inter1.add(new Interval(11, 12));
        List<Interval> inter2 = new ArrayList<>();
        inter2.add(new Interval(2, 4));
        inter2.add(new Interval(6, 8));
        inter2.add(new Interval(10, 12));
        List<Interval> inter3 = new ArrayList<>();
        inter3.add(new Interval(2, 5));
        inter3.add(new Interval(9, 12));
        intervals.add(inter1);
        intervals.add(inter2);
        intervals.add(inter3);

        /**
         * find out all the intervals that has no meetings
         * ans:
         * [5,6]
         * [8,9]
         */
        //List<Interval> res = getAvailableIntervals(intervals);


        /**
         * find all the intervals that has least k people have no meeting
         *
         * ans :
         * [1,2]
         * [4,6]
         * [7,10]
         */
        List<Interval> res = getAvailableIntervals(intervals, 1);

        for (Interval inter : res) {
            System.out.println("[" + inter.start + "," + inter.end + "]");
        }
    }
}

class Interval {
    int start;
    int end;

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

class Point implements Comparable<Point> {
    int time;
    boolean isStart;

    Point(int time, boolean isStart) {
        this.time = time;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Point that) {
        if (this.time != that.time || this.isStart == that.isStart) { // 如果time 不相等或都是start time
            return this.time - that.time;
        } else { // 如果time 相等， start time 排后面
            return this.isStart ? -1 : 1;
        }
    }
}
