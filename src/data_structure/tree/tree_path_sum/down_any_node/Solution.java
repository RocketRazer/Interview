package data_structure.tree.tree_path_sum.down_any_node;

import data_structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        this.res = new ArrayList<>();
        this.target = target;

        if (root == null) {
            return res;
        }


        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, root.val, path);

        return res;
    }

    List<List<Integer>> res;
    private int target;

    private void dfs(TreeNode root, int sum, List<Integer> path) {
        if (root == null) {
            return;
        }


        // down way: any node to end node
        int curSum = 0;
        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = path.size() - 1; i  >= 0; i--) {
            curSum += path.get(i);
            tmp.addFirst(path.get(i));
            if (curSum == target) {
                res.add(new ArrayList<>(tmp));
            }
        }

        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, sum + root.left.val, path);
            path.remove(path.size()-1);
        }

        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, sum + root.right.val, path);
            path.remove(path.size()-1);
        }
    }




    /**
     *
     *     1
     *    / \
     *   2   3
     *  /   /
     * 4   2
     *
     * res: [2, 4] [1, 3, 2]
     */

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r11 = new TreeNode(11);
        TreeNode r2 = new TreeNode(2);
        TreeNode r13 = new TreeNode(13);
        TreeNode r5 = new TreeNode(5);
        TreeNode r5_2 = new TreeNode(5);
        TreeNode r4 = new TreeNode(4);
        TreeNode r7 = new TreeNode(7);
        TreeNode r8 = new TreeNode(8);

        r5.left = r4;
        r5.right = r8;
        r4.left = r11;
        r11.left = r7;
        r11.right = r2;

        r8.left = r13;
        r13.left = r5_2;
        r13.right = r1;
        r8.right = r4;



        Solution s = new Solution();
        System.out.println(s.binaryTreePathSum2(r5, 22));
    }
}