package data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_14_is_complete_binary_tree {
    /** Complete binary tree
             4
          /    \
         2     6
        / \   /
       1   3 5
    */
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);

        r4.left = r2;
        r2.left = r1;
        r2.right = r3;

        r4.right = r6;
        r6.left = r5;

        System.out.println(Tree_14_is_complete_binary_tree.isCompleteBinaryTree(r4));
//        System.out.println(Tree_14_is_complete_binary_tree.isCompleteBinaryTreeRec(r4));
    }


    /**
     * 14. 判断二叉树是不是完全二叉树：isCompleteBinaryTree, isCompleteBinaryTreeRec
     * 进行level traversal, 一旦遇到一个节点的左节点为空，后面的节点的子节点都必须为空。而且不应该有下一行，其实就是队列中所有的
     * 元素都不应该再有子元素。
     * */
    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        TreeNode dummyNode = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList<TreeNode>();

        q.offer(root);
        q.offer(dummyNode);

        // if this is true, no node should have any child.
        boolean noChild = false;

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == dummyNode) {
                if (!q.isEmpty()) {
                    q.offer(dummyNode);
                }
                // Dummy node不需要处理。
                continue;
            }

            if (cur.left != null) {
                // 如果标记被设置，则Queue中任何元素不应再有子元素。
                if (noChild) {
                    return false;
                }
                q.offer(cur.left);
            } else {
                // 一旦某元素没有左节点或是右节点，则之后所有的元素都不应有子元素。
                // 并且该元素不可以有右节点.
                noChild = true;
            }

            if (cur.right != null) {
                // 如果标记被设置，则Queue中任何元素不应再有子元素。
                if (noChild) {
                    return false;
                }
                q.offer(cur.right);
            } else {
                // 一旦某元素没有左节点或是右节点，则之后所有的元素都不应有子元素。
                noChild = true;
            }
        }

        return true;
    }

    /**
     * 14. 判断二叉树是不是完全二叉树：isCompleteBinaryTreeRec
     *
     *
     *    我们可以分解为：
     *    CompleteBinary Tree 的条件是：
     *    1. 左右子树均为Perfect binary tree, 并且两者Height相同
     *    2. 左子树为CompleteBinaryTree, 右子树为Perfect binary tree，并且两者Height差1
     *    3. 左子树为Perfect Binary Tree,右子树为CompleteBinaryTree, 并且Height 相同
     *
     *    Base 条件：
     *    (1) root = null: 为perfect & complete BinaryTree, Height -1;
     *
     *    而 Perfect Binary Tree的条件：
     *    左右子树均为Perfect Binary Tree,并且Height 相同。
     *
     *
     *   A perfect binary tree (sometimes proper binary tree or full binary tree)
     *   is a tree in which every node other than the leaves has two children
     *
     *   A complete binary tree is a binary tree in which every level, except possibly the last,
     *   is completely filled, and all nodes are as far left as possible
     * */

    public static boolean isCompleteBinaryTreeRec(TreeNode root) {
        return isCompleteBinaryTreeRecHelp(root).isCompleteBT;
    }

    private static class ReturnBinaryTree {
        boolean isCompleteBT;
        boolean isPerfectBT;
        int height;

        ReturnBinaryTree(boolean isCompleteBT, boolean isPerfectBT, int height) {
            this.isCompleteBT = isCompleteBT;
            this.isPerfectBT = isPerfectBT;
            this.height = height;
        }
    }

    /**
     * 我们可以分解为：
     *    CompleteBinary Tree 的条件是：
     *    1. 左右子树均为Perfect binary tree, 并且两者Height相同
     *    2. 左子树为CompleteBinaryTree, 右子树为Perfect binary tree，并且两者Height差1
     *    3. 左子树为Perfect Binary Tree,右子树为CompleteBinaryTree, 并且Height 相同
     *
     *    Base 条件：
     *    (1) root = null: 为perfect & complete BinaryTree, Height -1;
     *
     *    而 Perfect Binary Tree的条件：
     *    左右子树均为Perfect Binary Tree,并且Height 相同。
     * */
    public static ReturnBinaryTree isCompleteBinaryTreeRecHelp(TreeNode root) {
        ReturnBinaryTree ret = new ReturnBinaryTree(true, true, -1);

        if (root == null) {
            return ret;
        }

        ReturnBinaryTree left = isCompleteBinaryTreeRecHelp(root.left);
        ReturnBinaryTree right = isCompleteBinaryTreeRecHelp(root.right);

        // 树的高度为左树高度，右树高度的最大值+1
        ret.height = 1 + Math.max(left.height, right.height);

        // set the isPerfectBT
        ret.isPerfectBT = false;
        if (left.isPerfectBT && right.isPerfectBT && left.height == right.height) {
            ret.isPerfectBT = true;
        }

        // set the isCompleteBT.
        /**
         * CompleteBinary Tree 的条件是：
         *    1. 左右子树均为Perfect binary tree, 并且两者Height相同(其实就是本树是perfect tree)
         *    2. 左子树为CompleteBinaryTree, 右子树为Perfect binary tree，并且两者Height差1
         *    3. 左子树为Perfect Binary Tree,右子树为CompleteBinaryTree, 并且Height 相同
         * */
        ret.isCompleteBT = ret.isPerfectBT
                || (left.isCompleteBT && right.isPerfectBT && left.height == right.height + 1)
                || (left.isPerfectBT && right.isCompleteBT && left.height == right.height);

        return ret;
    }
}
