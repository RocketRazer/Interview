package airbnb;


import java.util.*;

/**
 * https://en.wikipedia.org/wiki/Collatz_conjecture
 * 考拉兹猜想
 * 给你公式，比如偶数的话除2，奇数的话就变成3*n+1，对于任何一个正数，数学猜想是最终他会变成1。
 * 每变一步步数加1，给一个上限，让找出范围内最长步数
 * <p>
 * 记忆化搜索
 * <p>
 * 这题如果follow up还可以考虑输出最长的序列，那么我们就需要一个map来保存 integer -> list(integer)
 */
public class CollatzConjecture {
    /**
     * find the longest steps number within n
     *
     * @param n the upper limit
     * @return
     */
    public int findLongestSteps(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(1, 1);

        int longest = 1;

        for (int i = 1; i <= n; i++) {
            longest = Math.max(longest, getSteps(i, 0, cache));
        }

        return longest;
    }


    private int getSteps(int n, int preSteps, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return preSteps + cache.get(n);
        }

        int curSteps = n % 2 == 0 ? getSteps(n / 2, preSteps + 1, cache) : getSteps(3 * n + 1, preSteps + 1, cache);
        cache.put(n, curSteps - preSteps);

        return curSteps;
    }


    /**
     * Find longest Path
     * @param n
     * @return
     */
    public List<Integer> findLongestPath(int n) {
        List<Integer> longest = new ArrayList<>();

        Map<Integer, List<Integer>> cache = new HashMap<>();
        cache.put(1, Arrays.asList(1));


        for (int i = 1; i <= n; i++) {
            List<Integer> path = getPath(i, cache);

            if (path.size() > longest.size()) {
                longest = path;
            }
        }

        return longest;
    }


    public List<Integer> getPath(int n, Map<Integer, List<Integer>> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }


        List<Integer> curPath = new ArrayList<>();
        curPath.add(n);

        List<Integer> followingPath;
        if (n % 2 == 0) {
            followingPath = getPath(n /2, cache);
        } else {
            followingPath = getPath(n * 3 + 1, cache);
        }
        curPath.addAll(followingPath);
        cache.put(n, curPath);

        return curPath;
    }


    /**
     * Test
     * @param args
     */
    public static void main(String[] args) {
        CollatzConjecture cc = new CollatzConjecture();

        // 1
        // 2 -> 1
        // 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
        // 4 -> 2 -> 1
        // 5 -> 16 -> 8 -> 4 -> 2 -> 1
        // 6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
        // 7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
        // ...

        /**
         * find longest steps
         */
        for (int i = 1; i <= 7; i++) {
            System.out.println(cc.findLongestSteps(i));
        }

        System.out.println("Longest Path from i = 1 ~ 7");
        /**
         * find longest path
         */
        for (int i = 1; i <= 7; i++) {
            System.out.println(cc.findLongestPath(i));
        }
    }
}