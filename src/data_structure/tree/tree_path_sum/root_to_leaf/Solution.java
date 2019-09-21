package data_structure.tree.tree_path_sum.root_to_leaf;

import data_structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Definition of TreeNode:
 * public class TreeNode {
 * public int val;
 * public TreeNode left, right;
 * public TreeNode(int val) {
 * this.val = val;
 * this.left = this.right = null;
 * }
 * }
 */

public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null)
            return new ArrayList<ArrayList<Integer>>();

        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>(); // 用做存储路径
        int currentSum = 0; // 当前值，用来和target比较大小
        FindPath(root, target, arrayList, path, currentSum);

        return arrayList;
    }

    public void FindPath(TreeNode root, int target, ArrayList<ArrayList<Integer>> arrayList, ArrayList<Integer> path, int currentSum) {
        currentSum += root.val;
        path.add(root.val);

        // 如果是叶结点，并且路径和符合条件
        if ((root.left == null) && (root.right == null) && currentSum == target) {
            Iterator<Integer> iterator = path.iterator();
            ArrayList<Integer> pathTemp = new ArrayList<>();
            while (iterator.hasNext()) {
                pathTemp.add(iterator.next());
            }

            arrayList.add(pathTemp);
        }
        //如果不是叶结点，遍历它的子节点
        if (root.left != null)
            FindPath(root.left, target, arrayList, path, currentSum);
        if (root.right != null)
            FindPath(root.right, target, arrayList, path, currentSum);
        // 返回父节点前，在路径上删除当前节点
        path.remove(path.size() - 1);
    }
}

