package data_structure.tree.tree_path_sum.binary_tree_path;

import data_structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }

        dfs(root, root.val + "");

        return res;
    }


    private void dfs(TreeNode root, String path) {
        if (root == null) {
            return;
        }

        //leaf node
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }


        if (root.left != null) {
            dfs(root.left, path + "->" + root.left.val);
        }

        if (root.right != null) {
            dfs(root.right, path + "->" + root.right.val);
        }
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r5 = new TreeNode(5);

        r1.left = r2;
        r1.right = r3;
        r2.right = r5;


        Solution s = new Solution();
        System.out.println(s.binaryTreePaths(r1));
    }

}
