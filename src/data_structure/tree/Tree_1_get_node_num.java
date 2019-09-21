package data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. 求二叉树中的节点个数: getNodeNumRec（递归），getNodeNum（迭代）
 */

public class Tree_1_get_node_num {

    /**
     * null返回0，然后把左右子树的size加上即可。
     * */
    public static int getNodeNumRec(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return getNodeNumRec(root.left) + getNodeNumRec(root.right) + 1;
    }

    /**
     *  求二叉树中的节点个数迭代解法O(n)：基本思想同LevelOrderTraversal， 
     *  即用一个Queue，在Java里面可以用LinkedList来模拟  
     */
    public static int getNodeNum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        int cnt = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.offer(node.left);
            }

            if (node.right != null) {
                q.offer(node.right);
            }

            cnt++;
        }

        return cnt;
    }
}
