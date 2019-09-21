package data_structure.tree.bst_iterator;

import data_structure.tree.TreeNode;

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
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */


public class BSTIterator {
    Stack<TreeNode> stack;
    TreeNode next;
    /*
     * @param root: The root of binary tree.
     */public BSTIterator(TreeNode root) {
        // do intialization if necessary
        stack = new Stack<>();
        pushLeftToStack(root);
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here

        if (!hasNext()) {
            return null;
        } else {
            TreeNode cur = stack.pop();
            if (cur.right != null) {
                TreeNode temp = cur.right;
                pushLeftToStack(temp);
            }

            return cur;
        }
    }

    private void pushLeftToStack(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(-1);

        BSTIterator bstIterator = new BSTIterator(r1);
        System.out.println(bstIterator.next().val);
    }
}
