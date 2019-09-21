package data_structure.tree;

import java.util.Stack;

public class Tree_10_mirror {
    /**
     * 10. 求二叉树的镜像 递归解法：
     *
     *   (1) 破坏原来的树
     *
     *      1               1
     *     /                 \
     *    2     ----->        2
     *     \                 /
     *      3               3
     * */
    public static TreeNode mirrorRec(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 先把左右子树分别镜像,并且交换它们
        TreeNode tmp = root.right;
        root.right = mirrorRec(root.left);
        root.left = mirrorRec(tmp);

        return root;
    }

    /**
     * 10. 求二叉树的镜像 Iterator解法：
     *
     *   (1) 破坏原来的树
     *
     *      1               1
     *     /                 \
     *    2     ----->        2
     *     \                 /
     *      3               3
     *
     *  应该可以使用任何一种Traversal 方法。
     *  我们现在可以试看看使用最简单的preorder遍历。
     * */
    public static TreeNode mirror(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();

            // 交换当前节点的左右节点
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;

            // traversal 左节点，右节点。
            if (cur.right != null) {
                s.push(cur.right);
            }

            if (cur.left != null) {
                s.push(cur.left);
            }
        }

        return root;
    }

    /**
     * 10. 求二叉树的镜像 Iterator解法：
     *
     *   (2) 创建一个新的树
     *
     *      1               1
     *     /                 \
     *    2     ----->        2
     *     \                 /
     *      3               3
     *
     *  应该可以使用任何一种Traversal 方法。
     *  我们现在可以试看看使用最简单的前序遍历。
     *  前序遍历我们可以立刻把新建好的左右节点创建出来，比较方便
     * */
    public static TreeNode mirrorCopy(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<TreeNode> sCopy = new Stack<TreeNode>();
        s.push(root);

        TreeNode rootCopy = new TreeNode(root.val);
        sCopy.push(rootCopy);

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            TreeNode curCopy = sCopy.pop();

            // traversal 左节点，右节点。
            if (cur.right != null) {

                // copy 在这里做比较好，因为我们可以容易地找到它的父节点

                // copy 右节点，并连接到copyRoot的左节点，因为是镜像
                TreeNode leftCopy = new TreeNode(cur.right.val);
                curCopy.left = leftCopy;
                s.push(cur.right);
                sCopy.push(curCopy.left);
            }

            if (cur.left != null) {
                // copy 在这里做比较好，因为我们可以容易地找到它的父节点
                // copy 左节点，并连接到copyRoot的右节点，因为是镜像
                TreeNode rightCopy = new TreeNode(cur.left.val);
                curCopy.right = rightCopy;
                s.push(cur.left);
                sCopy.push(curCopy.right);
            }
        }

        return rootCopy;
    }

    /**
     * 10. 求二叉树的镜像 递归解法：
     *
     *   (1) 不破坏原来的树，新建一个树
     *
     *      1               1
     *     /                 \
     *    2     ----->        2
     *     \                 /
     *      3               3
     * */
    public static TreeNode mirrorCopyRec(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 先把左右子树分别镜像,并且把它们连接到新建的root节点。
        TreeNode rootCopy = new TreeNode(root.val);
        rootCopy.left = mirrorCopyRec(root.right);
        rootCopy.right = mirrorCopyRec(root.left);

        return rootCopy;
    }

    /**
     * 10.1. 判断两个树是否互相镜像
     *  (1) 根必须同时为空，或是同时不为空
     *
     * 如果根不为空:
     *  (1).根的值一样
     *  (2).r1的左树是r2的右树的镜像
     *  (3).r1的右树是r2的左树的镜像
     * */
    public static boolean isMirrorRec(TreeNode r1, TreeNode r2){
        // 如果2个树都是空树
        if (r1 == null && r2 == null) {
            return true;
        }

        // 如果其中一个为空，则返回false.
        if (r1 == null || r2 == null) {
            return false;
        }

        // If both are not null, they should be:
        // 1. have same value for root.
        // 2. R1's left tree is the mirror of R2's right tree;
        // 3. R2's right tree is the mirror of R1's left tree;
        return r1.val == r2.val
                && isMirrorRec(r1.left, r2.right)
                && isMirrorRec(r1.right, r2.left);
    }

    /**
     * 10.1. 判断两个树是否互相镜像 Iterator 做法
     *  (1) 根必须同时为空，或是同时不为空
     *
     * 如果根不为空:
     * traversal 整个树，判断它们是不是镜像，每次都按照反向来traversal
     * (1). 当前节点的值相等
     * (2). 当前节点的左右节点要镜像，
     *    无论是左节点，还是右节点，对应另外一棵树的镜像位置，可以同时为空，或是同时不为空，但是不可以一个为空，一个不为空。
     * */
    public static boolean isMirror(TreeNode r1, TreeNode r2){
        // 如果2个树都是空树
        if (r1 == null && r2 == null) {
            return true;
        }

        // 如果其中一个为空，则返回false.
        if (r1 == null || r2 == null) {
            return false;
        }

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        s1.push(r1);
        s2.push(r2);

        while (!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode cur1 = s1.pop();
            TreeNode cur2 = s2.pop();

            // 弹出的节点的值必须相等
            if (cur1.val != cur2.val) {
                return false;
            }

            // tree1的左节点，tree2的右节点，可以同时不为空，也可以同时为空，否则返回false.
            TreeNode left1 = cur1.left;
            TreeNode right1 = cur1.right;
            TreeNode left2 = cur2.left;
            TreeNode right2 = cur2.right;

            if (left1 != null && right2 != null) {
                //如果两个node都不为null， 入栈时候不比较 出栈时候比较
                s1.push(left1);
                s2.push(right2);
            } else if (!(left1 == null && right2 == null)) {
                return false;
            }

            // tree1的左节点，tree2的右节点，可以同时不为空，也可以同时为空，否则返回false.
            if (right1 != null && left2 != null) {
                //如果两个node都不为null，入栈时候不比较 出栈时候比较
                s1.push(right1);
                s2.push(left2);
            } else if (!(right1 == null && left2 == null)) {
                return false;
            }
        }

        return true;
    }
}
