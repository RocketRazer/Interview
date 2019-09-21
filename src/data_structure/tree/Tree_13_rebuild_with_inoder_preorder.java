package data_structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tree_13_rebuild_with_inoder_preorder {

    /**
     *  13. 由前序遍历序列和中序遍历序列重建二叉树：rebuildBinaryTreeRec
     *  We assume that there is no duplicate in the trees.
     *  For example:
     *          1
     *         / \
     *        2   3
     *       /\    \
     *      4  5    6
     *              /\
     *             7  8
     *
     *  PreOrder should be: 1   2 4 5   3 6 7 8
     *                      根   左子树    右子树
     *  InOrder should be:  4 2 5   1   3 7 6 8
     *                       左子树  根  右子树
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
        r2.left = r4;
        r2.right = r5;

        r1.right = r3;
        r3.right = r6;
        r6.left = r7;
        r6.right = r8;


        int[] pre = new int[]{1,2, 4, 5,   3, 6, 7, 8};
        int[] in = new int[]{4, 2, 5,   1,   3, 7 ,6 ,8};

        List<Integer> preorder =  Arrays.stream(pre).boxed().collect(Collectors.toList());
        List<Integer> inorder =  Arrays.stream(in).boxed().collect(Collectors.toList());

        TreeNode root = Tree_13_rebuild_with_inoder_preorder.rebuildBinaryTree(preorder, inorder);

        System.out.println(root.val);
    }

    public static TreeNode rebuildBinaryTreeRec(List<Integer> preOrder, List<Integer> inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }

        // If the traversal is empty, just return a NULL.
        if (preOrder.size() == 0 || inOrder.size() == 0) {
            return null;
        }

        // we can get the root from the preOrder.
        // Because the first one is the root.
        // So we just create the root node here.
        TreeNode root = new TreeNode(preOrder.get(0));

        List<Integer> preOrderLeft;
        List<Integer> preOrderRight;
        List<Integer> inOrderLeft;
        List<Integer> inOrderRight;

        // 获得在 inOrder中，根的位置
        int rootInIndex = inOrder.indexOf(root.val);

        //preorder 里左子树的长度 rootIndexInPreorder
        preOrderLeft = preOrder.subList(1, rootInIndex + 1);
        preOrderRight = preOrder.subList(rootInIndex + 1, preOrder.size());

        // 得到inOrder左边的左子树
        inOrderLeft = inOrder.subList(0, rootInIndex);
        inOrderRight = inOrder.subList(rootInIndex + 1, inOrder.size());

        // 通过 Rec 来调用生成左右子树。
        root.left = rebuildBinaryTreeRec(preOrderLeft, inOrderLeft);
        root.right = rebuildBinaryTreeRec(preOrderRight, inOrderRight);

        return root;
    }


    //practice
    public static TreeNode rebuildBinaryTree(List<Integer> preOrder, List<Integer> inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }


        if (preOrder.size() == 0 || inOrder.size() == 0) {
            return null;
        }


        TreeNode root = new TreeNode(preOrder.get(0));

        List<Integer> preOrderLeft = new ArrayList<>();
        List<Integer> preOrderRight = new ArrayList<>();
        List<Integer> inOrderLeft = new ArrayList<>();
        List<Integer> inOrderRight = new ArrayList<>();


        int rootIndexInInOrder = inOrder.indexOf(preOrder.get(0));

        inOrderLeft = inOrder.subList(0, rootIndexInInOrder);
        inOrderRight = inOrder.subList(rootIndexInInOrder + 1 , inOrder.size());


        preOrderLeft = preOrder.subList(1, rootIndexInInOrder + 1);
        preOrderRight = preOrder.subList(rootIndexInInOrder + 1, preOrder.size());

        root.left = rebuildBinaryTree(preOrderLeft, inOrderLeft);
        root.right = rebuildBinaryTree(preOrderRight, inOrderRight);

        return root;
    }
}
