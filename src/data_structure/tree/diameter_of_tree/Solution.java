package data_structure.tree.diameter_of_tree;

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
     * @param root: a root of binary tree
     * @return: return a integer
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // write your code here
        if (root == null) {
            return 0;
        }

        findMostEdges(root);

        return maxDiameter;
    }

    private int maxDiameter = 0;

    private int findMostEdges(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 0;
        }

        int leftEdges = findMostEdges(root.left);
        int rightEdges = findMostEdges(root.right);

        int curMostEdges = Math.max(leftEdges, rightEdges) + 1;

        int curDiameter = 0;
        if (root.left != null) {
            curDiameter += leftEdges + 1;
        }

        if (root.right != null) {
            curDiameter += rightEdges + 1;
        }


        maxDiameter = Math.max(maxDiameter, curDiameter);

        return curMostEdges;
    }

    /**
     *
     *     1
     *    / \
     *   2   3
     *  / \
     * 4  5
     *
     *  answer: 3
     * [4,2,1,3] or [5,2,1,3]
     */

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;

        Solution s = new Solution();
        System.out.println(s.diameterOfBinaryTree(r1));
    }

}
