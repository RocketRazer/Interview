package data_structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree_8_is_same_tree {
    /**
     * 8. 判断两棵二叉树是否相同的树。
     * 递归解法：
     * （1）如果两棵二叉树都为空，返回真
     * （2）如果两棵二叉树一棵为空，另一棵不为空，返回假
     * （3）如果两棵二叉树都不为空，如果对应的左子树和右子树都同构返回真，其他返回假
     * */
    public static boolean isSameRec(TreeNode r1, TreeNode r2) {
        // both are null.
        if (r1 == null && r2 == null) {
            return true;
        }

        // one is null.
        if (r1 == null || r2 == null) {
            return false;
        }

        // 1. the value of the root should be the same;
        // 2. the left tree should be the same.
        // 3. the right tree should be the same.
        return r1.val == r2.val &&
                isSameRec(r1.left, r2.left) && isSameRec(r1.right, r2.right);
    }

    /**
     * 8. 判断两棵二叉树是否相同的树。
     * 迭代解法
     * 我们直接用中序遍历来比较就好啦 preoder
     * */
    public static boolean isSame(TreeNode r1, TreeNode r2) {
        // both are null.
        if (r1 == null && r2 == null) {
            return true;
        }

        // one is null.
        if (r1 == null || r2 == null) {
            return false;
        }

        List<TreeNode> tree1 = preorderTraversal(r1);
        List<TreeNode> tree2 = preorderTraversal(r2);

        if (tree1.size() != tree2.size()) {
            return false;
        }

        for (int i = 0; i < tree1.size(); i++) {
            if (tree1.get(i) != tree2.get(i)) {
                return false;
            }
        }

        return true;
    }


    public static List<TreeNode> preorderTraversal(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return res;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }
}
