package data_structure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree_4_level_order {

    /**
     * 分层遍历二叉树（按层次从上往下，从左往右）迭代
     * 其实就是广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点
     * ，访问，若左子节点或右子节点不为空，将其压入队列
     * */
    public static void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            System.out.print(cur.val + " ");
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }



    /**
     *  TODO 没看
     *  分层遍历二叉树（递归）
     *  很少有人会用递归去做level traversal
     *  基本思想是用一个大的ArrayList，里面包含了每一层的ArrayList。
     *  大的ArrayList的size和level有关系
     *
     *  http://discuss.leetcode.com/questions/49/binary-tree-level-order-traversal#answer-container-2543
     */
    public static void levelTraversalRec(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        levelTraversalVisit(root, 0, ret);
        System.out.println(ret);
    }


    public static void levelTraversalVisit(TreeNode root, int level, ArrayList<ArrayList<Integer>> ret) {
        if (root == null) {
            return;
        }

        // 如果ArrayList的层数不够用， 则新添加一层
        // when size = 3, level: 0, 1, 2
        if (level >= ret.size()) {
            ret.add(new ArrayList<Integer>());
        }

        // visit 当前节点
        ret.get(level).add(root.val);

        // 将左子树， 右子树添加到对应的层。
        levelTraversalVisit(root.left, level + 1, ret);
        levelTraversalVisit(root.right, level + 1, ret);
    }
}
