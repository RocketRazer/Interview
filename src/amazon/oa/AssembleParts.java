package amazon.oa;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 第一题：零件组装题。题目意思就是有一堆零件，每个零件的尺寸和组装需要的时间是一样的。
 输入各个零件的尺寸的list，要求输出最短的总的 accumulated 组装时间。这么说估计也很难描述清楚，直接上例子：

        比如输入的list是 {8， 4， 6， 12}。
        1. 先选 4 和 6组装到一起，形成 size 为 10 的新零件。目前为止耗时为10。零件的 list 变为 {8， 10， 12}
        2. 再选 8 和 10 组装到一起，形成 size 为 18 的新零件。目前为止耗时为 10 + 18 = 28。零件的 list 变为 {12， 18}
        3. 最后 把 12 和 18 组装到一起，形成 size 为 30 的新零件。目前为止耗时为 10 + 18 + 30 = 58。
        最后输出 58 就可以了。

        解题思路：把所有零件先放到 min-heap (PriorityQueue for Java)中。然后每次 poll 两个最小的，加起来形成新零件，然后放回到min-heap中。
        如此循环直至 min-heap 中只有一个零件为止。在循环过程中记录总的累积时间就行了。这个题一定要秒掉，为后面的第二题赢得时间。

*/

public class AssembleParts {

    /**
     * get the minimum assemble cost of parts
     * @param parts
     * @return
     */
    public static int assembleCost(List<Integer> parts) {
        int cost = 0;
        if (parts == null || parts.size() == 0)
            return cost;

        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int p : parts) {
            pq.offer(p);
        }

        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();

            cost += first + second;

            pq.offer(first + second);
        }

        return cost;
    }


    public static void main(String[] args) {

        int[] partsArr = new int[]{8,4,6,12};

        List<Integer> parts = Arrays.stream(partsArr).boxed().collect(Collectors.toList());

        System.out.println(assembleCost(parts));
    }


}
