package a_lintcode.basic.wk7.two_sum_diff_equal_to_target;

import java.util.*;


public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        
        // make target to positive number, 
        // since we already use bigger number to minus smaller number
        target = Math.abs(target);

        Node[] nodeArray = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodeArray[i] = new Node(i, nums[i]);
        }

        Arrays.sort(nodeArray, new Comparator<Node>(){
            public int compare(Node x, Node y) {
                if (x.val == y.val) {
                    return x.index - y.index;
                } else {
                    return x.val - y.val;
                }
            }
        });

        int right = 1;
        for (int left = 0; left < nums.length; left++) {
            if (right == left) {
                right++;
            }

            while (right < nums.length && nodeArray[right].val - nodeArray[left].val < target) {
                right++;
            }

            if (right < nums.length && nodeArray[right].val - nodeArray[left].val == target) {
                res[0] = Math.min(nodeArray[left].index, nodeArray[right].index) + 1;
                res[1] = Math.max(nodeArray[left].index, nodeArray[right].index) + 1;
                break;
            }
        }


        return res;
    }

    class Node {
        int index;
        int val;

        public Node(int index, int val) {
            this.val = val;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 0, 1};

        System.out.printf(s.twoSum7(nums, 0).toString());
    }
}
