package data_structure.tree;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree_11_lca {
    /**
     * 11. 求二叉树中两个节点的最低公共祖先节点：
     * Recursion Version:
     * LACRec
     * 1. If found in the left tree, return the Ancestor.
     * 2. If found in the right tree, return the Ancestor.
     * 3. If Didn't find any of the node, return null.
     * 4. If found both in the left and the right tree, return the root.
     * */
    public static TreeNode LCARec(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }

        // If any of the node is the root, just return the root.
        if (root == node1 || root == node2) {
            return root;
        }

        // if no node is in the node, just recursively find it in LEFT and RIGHT tree.
        TreeNode left = LCARec(root.left, node1, node2);
        TreeNode right = LCARec(root.right, node1, node2);

        if (left == null) {  // If didn't found in the left tree, then just return it from right.
            return right;
        } else if (right == null) { // Or if didn't found in the right tree, then just return it from the left side.
            return left;
        }

        // if both right and right found a node, just return the root as the Common Ancestor.
        return root;
    }

    /**
     * 11. 求BST中两个节点的最低公共祖先节点：
     * Recursive version:
     * LCABst
     *
     * 1. If found in the left tree, return the Ancestor.
     * 2. If found in the right tree, return the Ancestor.
     * 3. If Didn't find any of the node, return null.
     * 4. If found both in the left and the right tree, return the root.
     * */
    public static TreeNode LCABstRec(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }

        // If any of the node is the root, just return the root.
        if (root == node1 || root == node2) {
            return root;
        }

        int min = Math.min(node1.val, node2.val);
        int max = Math.max(node1.val, node2.val);

        // if the values are smaller than the root value, just search them in the left tree.
        if (root.val > max) {
            return LCABstRec(root.left, node1, node2);
        } else if (root.val < min) {
            // if the values are larger than the root value, just search them in the right tree.
            return LCABstRec(root.right, node1, node2);
        }

        // min < root.val < max
        // if root is in the middle, just return the root.
        return root;
    }

    /**
     * 记录下path,并且比较之:
     * LCA
     * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
     * */
    public static TreeNode LCA_path(TreeNode root, TreeNode r1, TreeNode r2) {
        // If the nodes have one in the root, just return the root.
        if (root == null || r1 == null || r2 == null) {
            return null;
        }

        ArrayList<TreeNode> list1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> list2 = new ArrayList<TreeNode>();

        boolean find1 = hasPath(root, r1, list1);
        boolean find2 = hasPath(root, r2, list2);

        // If didn't find any of the node, just return a null.
        if (!find1 || !find2) {
            return null;
        }

        // 注意: 使用Iterator 对于linkedlist可以提高性能。
        // 所以 统一使用Iterator 来进行操作。
        Iterator<TreeNode> iter1 = list1.iterator();
        Iterator<TreeNode> iter2 = list2.iterator();

        TreeNode last = null;
        while (iter1.hasNext() && iter2.hasNext()) {
            TreeNode tmp1 = iter1.next();
            TreeNode tmp2 = iter2.next();

            if (tmp1 != tmp2) {
                return last;
            }

            last = tmp1;
        }

        // If never find any node which is different, means Node 1 and Node 2 are the same one.
        // so just return the last one.
        return last;
    }

    //DFS
    public static boolean hasPath(TreeNode root, TreeNode node, ArrayList<TreeNode> path) {
        if (root == null || node == null) {
            return false;
        }


        path.add(root);

        if (root == node) {
            return true;
        }

        if (hasPath(root.left, node, path) || hasPath(root.right, node, path)) {
            return true;
        }


        path.remove(path.size() -1);
        return false;
    }
}
