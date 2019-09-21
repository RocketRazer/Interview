package leetcode.missing_number;

public class Solution {
    public static int missingNumber(int[] nums) {
        int extra = -1;

        int p = 0;
        while (p < nums.length) {
            if (nums[p ] == -1) {
                p++;
            }

            if (nums[p] == nums.length) {
                extra = nums[p];
                nums[p] = -1;
            } else {
                while (p != nums[p] && nums[p] != -1 ) {
                    if (nums[p] == nums.length) {
                        extra = nums[p];
                        nums[p] = -1;
                    } else {
                        swap(p, nums[p], nums);
                    }
                }
            }
            p++;
        }


        if (extra == -1) {
            return nums.length;
        }

        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                res = i;
                break;
            }
        }

        return res;
    }

    private static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        System.out.println(missingNumber(nums));
    }
}
