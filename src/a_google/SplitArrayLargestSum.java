package a_google;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;

        int largest = nums[0];
        int sum = 0;
        for (int num : nums) {
            largest = Math.max(largest, num);
            sum += num;
        }

        if (m >= n) {
            return largest;
        }

        int left = largest, right = sum;

        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            int numOfSub = caculateSubs(mid, nums);

            if (numOfSub == m) {
                right = mid;
            } else if (m > numOfSub) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (caculateSubs(left, nums) <= m) {
            return left;
        } else {
            return right;
        }
    }

    private int caculateSubs(int sum, int[] nums) {
        int subs = 1;
        int curSum = sum;
        for (int i = 0; i < nums.length; i++) {
            if (curSum >= nums[i]) {
                curSum -= nums[i];
            } else {
                curSum = sum;
                curSum -= nums[i];
                subs++;
            }
        }

        return subs;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        System.out.println(solution.splitArray(new int[]{2,3,1,1,1,1,1}, 5));
    }
}
