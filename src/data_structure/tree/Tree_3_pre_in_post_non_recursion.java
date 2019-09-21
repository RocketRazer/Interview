package data_structure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree_3_pre_in_post_non_recursion {

    /**
     * Preorder
     * 把root push到stack里， while 循环stack 不为空
     * 每次pop出一个元素，
     * check 右子，右子不为null， push到stack里
     * check 左子，左子不为null， push到stack里
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (root == null) {
            return res;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return res;
    }


    /**
     * Inorder
     * 先把最左边全都加进stack，然后开始pop，每pop一次检查：
     * 1.右边为空就一直pop；
     * 2.不为空就去右边，然后再一次将左边的一条下来全加进stack里。重复该过程。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        while(root != null) {
            stack.push(root);
            root = root.left;
        }

        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            res.add(n.val);

            if (n.right != null) {
                n = n.right;
                while (n != null) {
                    stack.push(n);
                    n = n.left;
                }
            }
        }

        return res;
    }

    /**
     * Post order
     * 用Stack和LinkedList来做， 每次往第一位加数字， 所以就变成（left，right，root)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return res;
    }


    /**
     * https://www.youtube.com/watch?v=qT65HltK2uE
     * Post order
     * two stacks version
     */
    public static void postorderTraversal_two_stack(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<TreeNode> out = new Stack<TreeNode>();

        s.push(root);
        while(!s.isEmpty()) {
            TreeNode cur = s.pop();
            out.push(cur);

            if (cur.left != null) {
                s.push(cur.left);
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
        }

        while(!out.isEmpty()) {
            System.out.print(out.pop().val + " ");
        }
    }

}





