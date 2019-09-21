package data_structure.tree;
/**
 * 15. 找出二叉树中最长连续子串(即全部往左的连续节点，或是全部往右的连续节点）findLongest
 * 数字不需要连续 不算根节点
 *
 * 跟leetcode的题不一样
 */
public class Tree_15_longest_sequence {

    /**
     * 15. findLongest
     * 第一种解法：
     * 返回左边最长，右边最长，及左子树最长，右子树最长。
     * */
    public static int findLongest(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode l = root;
        int cntL = 0;
        while (l.left != null) {
            cntL++;
            l = l.left;
        }

        TreeNode r = root;
        int cntR = 0;
        while (r.right != null) {
            cntR++;
            r = r.right;
        }

        int lmax = findLongest(root.left);
        int rmax = findLongest(root.right);

        int max = Math.max(lmax, rmax);
        max = Math.max(max, cntR);
        max = Math.max(max, cntL);

        return max;
    }


    public static int findLongest2(TreeNode root) {
        int [] maxVal = new int[1];
        maxVal[0] = -1;
        findLongest2Help(root, maxVal);
        return maxVal[0];
    }

    // ret:
    // 0: the left side longest,
    // 1: the right side longest.
    static int maxLen = -1;
    static int[] findLongest2Help(TreeNode root, int[] maxVal) {
        int[] ret = new int[2];
        if (root == null) {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }

        ret[0] = findLongest2Help(root.left, maxVal)[0] + 1;
        ret[1] = findLongest2Help(root.right, maxVal)[1] + 1;
        //maxLen = Math.max(maxLen, ret[0]);
        //maxLen = Math.max(maxLen, ret[1]);
        maxVal[0] = Math.max(maxVal[0], ret[0]);
        maxVal[0] = Math.max(maxVal[0], ret[1]);

        return ret;
    }

    /**
     *      1
     *    2   3
     *          4
     *         5
     *        6
     *       7
     *      8
     * */
    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        TreeNode r8 = new TreeNode(8);

        r1.left = r2;
        r1.right = r3;
        r3.right = r4;
        r4.left = r5;
        r5.left = r6;
        r6.left = r7;
        r7.left = r8;

        System.out.println(Tree_15_longest_sequence.findLongest2(r1));
    }
}
