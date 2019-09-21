package data_structure.tree;

import static data_structure.tree.Tree_2_get_depth.getDepthRec;

public class Tree_9_isBalance {
    /**
     *
     *  9. 判断二叉树是不是平衡二叉树：isBalancedRec
     *     1. 左子树，右子树的高度差不能超过1
     *     2. 左子树，右子树都是平衡二叉树。
     *
     *  AVL: a self-balancing Binary Search Tree (BST)
     */
    public static boolean isBalancedRec(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 左子树，右子树都必须是平衡二叉树。
        if (!isBalancedRec(root.left) || !isBalancedRec(root.right)) {
            return false;
        }

        int dif = Math.abs(getDepthRec(root.left) - getDepthRec(root.right));
        if (dif > 1) {
            return false;
        }

        return true;
    }

}
