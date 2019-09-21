package cat.combination_judement_prime_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 1666. Combination and judgment prime number
 cat-only-icon
 CAT Only

 Give n integers and an integer k, you can choose k integers in the n integers and add them up, now let you calculate how many ways that the sum of the k integers is a prime number.

 Example
 Give a=[3,7,12,19], k=3, return 1

 Explanation:
 There are 4 ways
 3＋7＋12=22　　3＋7＋19＝29　　7＋12＋19＝38　　3＋12＋19＝34, and only 29 is a prime.
 Give a=[1,2,3], k=2, return 2

 Explanation:
 There are 3 ways
 1 + 2 = 3         1 + 3 = 4      2 + 3 =5
 and only 3 and 5 are primes.
 Notice
 n does not exceed 1010
 k does not exceed n
 */

public class Solution {
    /**
     * @param a: the n numbers
     * @param k: the number of integers you can choose
     * @return: how many ways that the sum of the k integers is a prime number
     */
    public int getWays(int[] a, int k) {
        // Write your code here
        if (a == null || a.length == 0 || k <= 0) {
            return 0;
        }

        // write your code here
        List<List<Integer>> result = new ArrayList<>();


        Arrays.sort(a);

        List<Integer> list = new ArrayList<>();
        dfs(0, k, a, list, result);

        return result.size();
    }


    private void dfs(int startIndex, int k, int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (k == 0) {
            int sum = 0;
            for (Integer i : list) {
                sum += i;
            }
            if (isPrime(sum)) {
                result.add(new ArrayList<>(list));
            }
            return;
        }


        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(i + 1, k-1, nums, list, result);
            list.remove(list.size() - 1);
        }
    }

    private boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            return true;
        }

        int m = n / 2;

        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = {1,2,3,4,5,6,7,8};

        s.getWays(a, 3);
    }
}

