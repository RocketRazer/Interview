package a_lintcode.basic.wk6.kthelement;

public class Solution {
    public int kthLargestElement(int k, int[] nums) {
        int low = 0, high = nums.length -1;
        while(low <= high){
            int pivot = nums[high];
            int index = low-1;
            for(int i = low; i < high; i++){
                if(nums[i] > nums[high]){
                    swap(nums, i, ++index);
                }
            }
            swap(nums, ++index, high);
            if(index == k - 1){
                return nums[index];
            }
            if (index < k -1){
                low = index + 1;
            } else{
                high = index - 1;
            }
        }
        return -1;
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.kthLargestElement(3, new int[]{9,3,2,4,8}));
    }
}
