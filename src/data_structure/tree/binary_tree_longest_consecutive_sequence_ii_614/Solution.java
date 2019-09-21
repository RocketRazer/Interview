package data_structure.tree.binary_tree_longest_consecutive_sequence_ii_614;

import data_structure.tree.TreeNode;

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
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return caculateMax(root).max;
    }


    private ResultWrapper caculateMax(TreeNode root) {
        if (root == null) {
            return new ResultWrapper(0, 0, 0);
        }


        ResultWrapper left = caculateMax(root.left);
        ResultWrapper right = caculateMax(root.right);

        int leftUp = 0, leftDown = 0, rightUp = 0, rightDown = 0;

        if (root.left != null) {
            if (root.val - 1 == root.left.val) {
                leftDown = left.maxDown + 1;
            }


            if (root.val + 1 == root.left.val) {
                leftUp = left.maxUp + 1;
            }
        }

        if (root.right != null) {
            if (root.val - 1 == root.right.val) {
                rightDown = right.maxDown + 1;
            }


            if (root.val + 1 == root.right.val) {
                rightUp = right.maxUp + 1;
            }
        }

        int curMax = Math.max(leftUp + rightDown + 1, leftDown + rightUp + 1);

        return new ResultWrapper(Math.max(leftUp, rightUp),
                Math.max(leftDown, rightDown),
                curMax);
    }

    class ResultWrapper {
        int max;

        // max up 和 max down是不包括root的
        int maxUp;
        int maxDown;

        public ResultWrapper(int maxUp, int maxDown, int max) {
            this.maxDown = maxDown;
            this.maxUp = maxUp;
            this.max = max;
        }
    }

    /**
     *
     *     1
     *    / \
     *   2   0
     *  /
     * 3
     *
     *  answer: 4
     *
     */

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r0 = new TreeNode(0);

        r1.left = r2;
        r2.left = r3;
        r1.right = r0;

        Solution s = new Solution();
        System.out.println(s.longestConsecutive2(r1));
    }
}

