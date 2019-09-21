package data_structure.tree;

public class Tree_12_max_distance {
    /**
     * 12. 求二叉树中节点的最大距离：getMaxDistanceRec
     *
     *  首先我们来定义这个距离：
     *  距离定义为：两个节点间边的数目.
     *  如：
     *     1
     *    / \
     *   2   3
     *        \
     *         4
     *   这里最大距离定义为2，4的距离，为3.
     * 求二叉树中节点的最大距离 即二叉树中相距最远的两个节点之间的距离。 (distance / diameter)
     * 递归解法：
     * 返回值设计：
     * 返回
     * 1. 深度，
     *
     * 2. 当前树的最长距离
     * (1) 计算左子树的深度，右子树深度，左子树独立的链条长度，右子树独立的链条长度
     * (2) 最大长度为三者之最：
     *    a. 通过根节点的链，为左右深度+2
     *    b. 左子树独立链
     *    c. 右子树独立链。
     *
     * (3)递归初始条件：
     *   当root == null, depth = -1.maxDistance = -1;
     *
     */

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);


        r1.left = r2;
        r1.right = r3;
        r3.right = r4;

        int res = Tree_12_max_distance.getMaxDistanceRec(r1);

        System.out.println(res);
    }
    public static int getMaxDistanceRec(TreeNode root) {
        return getMaxDistanceRecHelp(root).maxDistance;
    }

    public static Result getMaxDistanceRecHelp(TreeNode root) {
        Result ret = new Result(-1, -1);

        if (root == null) {
            return ret;
        }

        Result left = getMaxDistanceRecHelp(root.left);
        Result right = getMaxDistanceRecHelp(root.right);

        // 深度应加1， the depth from the subtree to the root.
        ret.depth = Math.max(left.depth, right.depth)  + 1;

        // 左子树，右子树与根的距离都要加1，所以通过根节点的路径为两边深度+2
        int crossLen = left.depth + right.depth  + 2;

        // 求出cross根的路径，及左右子树的独立路径，这三者路径的最大值。
        ret.maxDistance = Math.max(left.maxDistance, right.maxDistance);
        ret.maxDistance = Math.max(ret.maxDistance, crossLen);

        return ret;
    }

}

class Result {
    int depth;
    int maxDistance;
    public Result(int depth, int maxDistance) {
        this.depth = depth;
        this.maxDistance = maxDistance;
    }
}
