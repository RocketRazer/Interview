//package week3;
//
//public class BinaryTreeLongestConsecutiveSequenceII {
//
//    /**
//     * Definition of TreeNode:
//     * public class TreeNode {
//     *     public int val;
//     *     public TreeNode left, right;
//     *     public TreeNode(int val) {
//     *         this.val = val;
//     *         this.left = this.right = null;
//     *     }
//     * }
//     */
//
//    public class Solution {
//        /**
//         * @param root: the root of binary tree
//         * @return: the length of the longest consecutive sequence path
//         */
//        public int longestConsecutive2(TreeNode root) {
//            // write your code here
//            MyResult result = helper(root);
//            return result.max;
//        }
//
//        MyResult helper(TreeNode root) {
//            if (root == null) {
//                return new MyResult(0, 0, 0);
//            }
//
//            MyResult leftR = helper(root.left);
//            MyResult rightR = helper(root.right);
//
//            int leftUp = 0, leftDown = 0, rightUp = 0, rightDown = 0;
//
//            // caculate left max up and down
//            if (root.left != null) {
//                if (root.left.val - root.val == 1) {
//                    leftUp = leftR.upMax + 1;
//                }
//
//                if (root.val - root.left.val == 1) {
//                    leftDown = leftR.downMax + 1;
//                }
//            }
//
//            //calculate right max up and down
//            if (root.right != null) {
//                if (root.right.val - root.val == 1) {
//                    rightUp = rightR.upMax + 1;
//                }
//
//                if (root.val - root.right.val == 1) {
//                    rightDown = rightR.downMax + 1;
//                }
//            }
//
//            //caculate current root max
//            int currentMax = Math.max(leftUp + rightDown + 1, leftDown + rightUp + 1);
//
//
//            return new MyResult(Math.max(leftUp, rightUp),
//                    Math.max(leftDown, rightDown),
//                    Math.max(currentMax, Math.max(leftR.max, rightR.max)));
//
//        }
//
//        class MyResult {
//            int max, upMax, downMax;
//
//            public MyResult(int upMax, int downMax, int max) {
//                this.upMax = upMax;
//                this.downMax = downMax;
//                this.max = max;
//            }
//        }
//
//
//        /**
//         * Definition of TreeNode:
//         * public class TreeNode {
//         *     public int val;
//         *     public TreeNode left, right;
//         *     public TreeNode(int val) {
//         *         this.val = val;
//         *         this.left = this.right = null;
//         *     }
//         * }
//         */
//
//
//        public class Solution {
//            /*
//             * @param root: The root of the binary search tree.
//             * @param A: A TreeNode in a Binary.
//             * @param B: A TreeNode in a Binary.
//             * @return: Return the least common ancestor(LCA) of the two nodes.
//             */
//            public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
//                // write your code here
//                Result result = helper(root, A, B);
//                if (result.a_exits && result.b_exits) {
//                    return result.node;
//                } else {
//                    return null;
//                }
//            }
//
//            class Result {
//                boolean a_exits = false;
//                boolean b_exits = false;
//                TreeNode node = null;
//
//                public Result (boolean a_exits, boolean b_exits, TreeNode node) {
//                    this.a_exits = a_exits;
//                    this.b_exits = b_exits;
//                    this.node = node;
//                }
//            }
//
//            //definition
//            private Result helper(TreeNode root, TreeNode A, TreeNode B) {
//                // exit
//                if (root == null) {
//                    return new Result(false, false, null);
//                }
//
//
//                //split
//                Result left = helper(root.left, A, B);
//                Result right = helper(root.right, A, B);
//
//                boolean a_exits = left.a_exits || right.a_exits || root == A;
//                boolean b_exits = left.b_exits || right.b_exits || root == B;
//
//                if (root == A || root == B) {
//                    return new Result (a_exits, b_exits, root);
//                }
//
//                if (left.node != null && right.node != null) {
//                    return new Result(a_exits, b_exits, root);
//                } else if (left.node != null) {
//                    return new Result(a_exits, b_exits, left.node);
//                } else if (right.node != null) {
//                    return new Result(a_exits, b_exits, right.node);
//                } else {
//                    return new Result(a_exits, b_exits, null);
//                }
//            }
//
//        }
//}