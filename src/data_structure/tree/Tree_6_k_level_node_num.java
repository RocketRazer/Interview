package data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_6_k_level_node_num {
    /**
     *  6. 求二叉树第K层的节点个数：getNodeNumKthLevelRec, getNodeNumKthLevel
     * */
    public static int getNodeNumKthLevel(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        int level = 0;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        TreeNode dummy = new TreeNode(0);
        int cnt = 0; // record the size of the level.

        q.offer(dummy);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == dummy) {
                level++;
                if (level == k) {
                    return cnt;
                }
                cnt = 0; // reset the cnt;
                if (q.isEmpty()) {
                    break;
                }
                q.offer(dummy);
                continue;
            }

            cnt++;
            if (node.left != null) {
                q.offer(node.left);
            }

            if (node.right != null) {
                q.offer(node.right);
            }
        }

        return 0;
    }

    /**
     *  6. 求二叉树第K层的节点个数：getNodeNumKthLevelRec, getNodeNumKthLevel
     * */
    public static int getNodeNumKthLevelRec(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        // 将左子树及右子树在K层的节点个数相加.
        return getNodeNumKthLevelRec(root.left, k - 1) + getNodeNumKthLevelRec(root.right, k - 1);
    }
}
