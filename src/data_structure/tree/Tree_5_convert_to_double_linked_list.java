package data_structure.tree;

import java.util.Stack;

public class Tree_5_convert_to_double_linked_list {

    /**
     *  题目要求：将二叉查找树转换成排序的双向链表，不能创建新节点，只调整指针。
     *  查找树的结点定义如下：
     *  既然是树，其定义本身就是递归的，自然用递归算法处理就很容易。将根结点的左子树和右子树转换为有序的双向链表，
     *  然后根节点的left指针指向左子树结果的最后一个结点，同时左子树最后一个结点的right指针指向根节点；
     *  根节点的right指针指向右子树结果的第一个结点，
     *  同时右子树第一个结点的left指针指向根节点。
     * */
    public static TreeDemo.TreeNode convertBST2DLLRec(TreeDemo.TreeNode root) {
        return convertBST2DLLRecHelp(root)[0];
    }

    /*
     * ret[0] 代表左指针
     * ret[1] 代表右指针
     * */
    public static TreeDemo.TreeNode[] convertBST2DLLRecHelp(TreeDemo.TreeNode root) {
        TreeDemo.TreeNode[] ret = new TreeDemo.TreeNode[2];
        ret[0] = null;
        ret[1] = null;

        if (root == null) {
            return ret;
        }

        if (root.left != null) {
            TreeDemo.TreeNode left[] = convertBST2DLLRecHelp(root.left);
            left[1].right = root;  // 将左子树的尾节点连接到根
            root.left = left[1];

            ret[0] = left[0];
        } else {
            ret[0] = root;   // 左节点返回root.
        }

        if (root.right != null) {
            TreeDemo.TreeNode right[] = convertBST2DLLRecHelp(root.right);
            right[0].left = root;  // 将右子树的头节点连接到根
            root.right = right[0];

            ret[1] = right[1];
        } else {
            ret[1] = root;  // 右节点返回root.
        }

        return ret;
    }

    /**
     * 将二叉查找树变为有序的双向链表 迭代解法
     * 类似inOrder traversal的做法
     */
    public static TreeDemo.TreeNode convertBST2DLL(TreeDemo.TreeNode root) {
        while (root == null) {
            return null;
        }

        TreeDemo.TreeNode pre = null;
        Stack<TreeDemo.TreeNode> s = new Stack<TreeDemo.TreeNode>();
        TreeDemo.TreeNode cur = root;
        TreeDemo.TreeNode head = null;       // 链表头

        while (true) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }

            // if stack is empty, just break;
            if (s.isEmpty()) {
                break;
            }

            cur = s.pop();
            if (head == null) {
                head = cur;
            }

            // link pre and cur.
            cur.left = pre;
            if (pre != null) {
                pre.right = cur;
            }

            // 左节点已经处理完了，处理右节点
            cur = cur.right;
            pre = cur;
        }

        return root;
    }
}
