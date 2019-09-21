package amazon.oa;

import java.util.*;

/**
 * Closest two sum, 往卡车里装货/选择适合音乐.
 *
 * 大概就是给一个list和一个 target，然后从list里选出选出两个数个和不大于target(或者不大于target - 30 (,= target -30))。
 * 如果有多组输满足要求，就返 回包含最大数的那一组。下面code里的input array应该换成list
 *
 *
 *
 * 第一个是truckSpace，给你一个List<Integer>packageSize，然后给你一个truckSpace，
 * 需要你从这个list里面选出两个distinct number，加在一起的值要等于（exactly） truckSpace -30.
 * 这个地方我要说一下，因为我整理别人的题，我看到的是说要小于等于truckSpace -30， 但是我这个题是两个number加起来要正好等于 truckSpace -30。
 * 返回值是两个number在list里面的index，并且如果有2个或以上result，返回那个packageSize最大的那个。
 */
public class CloestTwoSum {

    /**
     * Find two packages whose size sum equals to the truck space - 30
     * @param packageSize
     * @param truckSpace
     * @return the indexes of the two packages
     */
    public static int[] twoSumExact(int[] packageSize, int truckSpace) {
        int[] res = new int[2];

        if (packageSize == null || packageSize.length < 2) return res;

        int target = truckSpace - 30;

        Map<Integer, Integer> idxByRemainValue = new HashMap<>();
        for (int i = 0; i < packageSize.length; i++) {

            if (idxByRemainValue.containsKey(packageSize[i])) {
                res[0] = idxByRemainValue.get(packageSize[i]);
                res[1] = i;
                break;
            } else {
                idxByRemainValue.put(target - packageSize[i], i);
            }
        }

        return  res;
    }


    /**
     *
     *
     Two sum 变形: foreground apps + background apps <= memory/无人机 送货。跟第一个不同这个输入是两个list，从两个list里面各选出一个数的和不 大于target
     - 可以用两个loop暴力解，据说test全过
     - 先排序然后twopointers，一个从头开始一个从尾，据说不能通过所有
     测试。我猜原因是比如两个list
     List1 [[1, 20], [2,20], [3,35], [4,40], [5,55]]
     List2 [[2, 25], [3,30], [4,50], [5,70], [6,70]]
     Target是100
     我猜正确输出应该是[[1,5], [1,6], [2,5], [2,6], [4,4]]，但如果用two pointer，前四个里一定会漏掉一个
     - 先loop一个list然后binarysearch另一个，亲测这种解法我能想到的test case都能过
     以上都是我看别人面经自己yy，不知道有没有理解错的地方
     *
     *
     */
    public int[] twoSumLessThan(int[] packageSize, int truckSpace) {
        int[] res = new int[2];
        if (packageSize == null || packageSize.length < 2) return res;

        int target = truckSpace - 30;

        return res;
    }


    public static void main(String[] args) {

        // exact equal
        int[] packageSize = new int[]{10, 20, 50, 60, 80, 90};
        int[] res = twoSumExact(packageSize, 100);
        System.out.println(Arrays.toString(res));

        String[] words = new String[]{ "z1", "b2", "a1", "d3"};
       Arrays.sort(words, 1, words.length);

        String str = " Hello I'm your String";
        String[] splitStr = str.split(" ", 2);
    }


    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new CustomerComparator());
        return logs;
    }

    class CustomerComparator implements Comparator<String>{
        public int compare(String a, String b) {
            String[] aStrs = a.split(" ");
            String[] bStrs = b.split(" ");

            boolean a_isDigitLog = Character.isDigit(aStrs[1].charAt(0));
            boolean b_isDigitLog = Character.isDigit(bStrs[1].charAt(0));

            if (!a_isDigitLog && !b_isDigitLog) {
                if (aStrs[1].equals(bStrs[1])) {
                    return aStrs[0].compareTo(bStrs[0]);
                } else {
                    return aStrs[1].compareTo(bStrs[1]);
                }
            } else {
                if (a_isDigitLog) {
                    return 1;
                } else {
                    return -1;
                }
            }


        }
    }
}
