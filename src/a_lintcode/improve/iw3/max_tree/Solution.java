package a_lintcode.improve.iw3.max_tree;

import java.util.Stack;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] A) {
        Stack<TreeNode> stack = new Stack<>();
        for (int a: A) {
            TreeNode cur = new TreeNode(a);
            while(!stack.isEmpty() && a > stack.peek().val) {
                TreeNode node = stack.pop();
                cur.left = node;
            }
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }

            stack.push(cur);
        }
        TreeNode res = null;
        while(!stack.isEmpty()) {
            res = stack.pop();
        }
        return res;
    }

    class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 6, 0, 3, 1};
        Solution s = new Solution();
        TreeNode root = s.maxTree(nums);
        System.out.println(root);
    }
}

