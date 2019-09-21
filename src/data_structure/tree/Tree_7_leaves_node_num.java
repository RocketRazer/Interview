package data_structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_7_leaves_node_num {
    /**
     * 7. getNodeNumLeafRec  把左子树和右子树的叶子节点加在一起即可
     * */
    public static int getNodeNumLeafRec(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return getNodeNumLeafRec(root.left) + getNodeNumLeafRec(root.right);
    }

    /**
     * 7. getNodeNumLeaf
     * 随便使用一种遍历方法都可以，比如，中序遍历。
     * preorderTraversal，判断是不是叶子节点。
     * */
    public static int getNodeNumLeaf(TreeNode root) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return count;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                count++;
            }


            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return count;
    }
}
