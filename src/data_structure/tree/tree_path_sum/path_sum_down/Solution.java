package data_structure.tree.tree_path_sum.path_sum_down;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private int target;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        this.target = sum;
        List<Integer> list = new ArrayList<>();

        list.add(root.val);
        dfs(root, list);

        System.out.println(res);
        return res.size();
    }


    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;

        int curSum = 0;
        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            curSum += list.get(i);
            tmp.addFirst(list.get(i));

            if (curSum == target) {
                res.add(new LinkedList<>(tmp));
            }
        }


        if (root.left != null) {
            list.add(root.left.val);
            dfs(root.left, list);
            list.remove(list.size() - 1);
        }


        if (root.right != null) {
            list.add(root.right.val);
            dfs(root.right, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r11 = new TreeNode(11);
        TreeNode r2 = new TreeNode(2);
        TreeNode r13 = new TreeNode(13);
        TreeNode r5 = new TreeNode(5);
        TreeNode r5_2 = new TreeNode(5);
        TreeNode r4 = new TreeNode(4);
        TreeNode r7 = new TreeNode(7);
        TreeNode r8 = new TreeNode(8);

        r5.left = r4;
        r5.right = r8;
        r4.left = r11;
        r11.left = r7;
        r11.right = r2;

        r8.left = r13;
        r13.left = r5_2;
        r13.right = r1;
        r8.right = r4;



        Solution s = new Solution();
        System.out.println(s.pathSum(r5, 22));
    }
}


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

