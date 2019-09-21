package data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_2_get_depth {

    /**
     * Recursion Divide and Conquer
     *
     *  一个node都没有是0层
     */
    public static int getDepthRec(TreeNode root) {
        if (root == null) {
            return -1;
        }

        return Math.max(getDepthRec(root.left), getDepthRec(root.right)) + 1;
    }


    /**
     *
     *     1
     *    / \
     *   2   3
     *        \
     *         4
     *
     *  Depth = 3
     */
    public static void main(String[] args) {

        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);


        r1.left = r2;
        r1.right = r3;
        r3.right = r4;

        int res = Tree_2_get_depth.getDepthRec(r1);

        System.out.println(res);
    }

    /**
     * 可以用 level LevelOrderTraversal 来实现，我们用一个dummyNode来分隔不同的层，这样即可计算出实际的depth.
     *      1
           / \
          2   3
         / \   \
        4   5   6
     *
     * 在队列中如此排列： 1, dummy, 2, 3, dummy, 4, 5, 5, dummy
     *
    */
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return -1;
        }

        TreeNode dummy = new TreeNode(0);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(dummy);

        int depth = -1;
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr == dummy) {
                depth++;
                if (!q.isEmpty()) {  // 使用DummyNode来区分不同的层， 如果下一层不是为空，则应该在尾部加DummyNode.
                    q.offer(dummy);
                }
            }

            if (curr.left != null) {
                q.offer(curr.left);
            }
            if (curr.right != null) {
                q.offer(curr.right);
            }
        }

        return depth;
    }
}
