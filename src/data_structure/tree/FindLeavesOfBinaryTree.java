package data_structure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLeavesOfBinaryTree {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        while (root != null) {
            bfs(root);
        }
        return res;
    }

    private void bfs(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        if (root.left == null && root.right == null) {
            list.add(root.val);
            res.add(list);
            root = null;
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left == null && cur.right == null) {
                list.addFirst(cur.val);
                cur = null;
                continue;
            }

            if (cur.right != null) {
                q.offer(cur.right);
            }

            if (cur.left != null) {
                q.offer(cur.left);
            }
        }

        res.add(list);
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);

        r1.left = r2;
        r1.right = r3;

        r2.left = r4;
        r2.right = r5;

        FindLeavesOfBinaryTree solution = new FindLeavesOfBinaryTree();
        solution.findLeaves(r1);
    }
}
