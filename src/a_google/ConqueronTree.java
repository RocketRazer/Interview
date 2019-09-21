package a_google;

import data_structure.tree.TreeNode;

/**
 *
 *
 我觉得这道题是这个思路：
 一棵二叉树，如果我的对手已经选中了某个节点node，接下来如果把这个节点从树中移除，那么这棵树会裂解成三个connected components:
 A: node的left child
 B: node的right child
 C: 其他节点（如果node是root那这部分为空，否则这部分就是node的parent node及与其相连的节点们）

 接下来我的选择就是在这个三个components中选择一个component作为我的领地，阻止对手在这个component扩张。为了最大化我的优势，我当然应该选择其中节点个数最多的。那么接下来我能不能赢得游戏就很好判断了：

 如果其中存在某个component的节点数超过了原来整棵树的节点数的一半，那么我就可以选择这个component，然后我就赢了；否则我必输，因为即使我选择了节点最多的那个component，我和其他两个components之间被我的对手阻隔着，无法扩张到那里。

 所以这题的解法就是数出ABC三个部分的节点数，最后return MAX(size(A), size(B), size(C)) > size(the whole tree) / 2

 follow up也很简单，如果我决定了要选哪个component，那么下一步我就选择这个component里和node最近的那个，也就是node的parent/root of left child/root of right child.


 唔重新看了一遍题我觉得我follow up理解有偏差。。。如果是要我先选点并且要不败的话，那么基本思路应该还是数节点数：
 1. 对于树中的每一个节点，我都数出以它为根的tree的节点数，并且储存起来。这一步可以用dp + hash table.
 2. 接下来我可以通过1的结果进一步获得这样的数据：对于每一个节点，把它从树中移除、裂解生成的三个部分各自的节点数。
 3. 如果存在某个节点A，删除它之后裂解生成的三个部分中没有哪一个部分的节点数超过了总节点数的半数，那么这个节点A就是我的不败节点。


 说一下我的思路，大家指正

 先手的棋手落子后，根据落子的地点以及树的形状，有以下几种情况：
 - 当起手点是叶子节点时，原有的树还是一个连通图，而后手棋手只要落子在次叶子节点的父节点则后手棋手必胜，因次先手棋手通常不应该选择叶子节点
 - 当起手点是跟节点，或非根非叶子节点且其左右子树有一个为空时，原有的树被切割成两个联通子图，后手棋手只能选择两个联通图中节点多的一个，若两个联通图中节点数目相同，则后手棋手必败。
 - 当起手点是非根非叶子节点且其左右子树均不为空时，原有的树被切割成三个联通子图，左子树L，右子树R，以及树中其他剩余节点P，此时后手棋手必定选择其中含有节点数最多的一个子图，
 因此，当先手棋手选择的点满足：MAX(L, R, P) < SUM(!MAX(L,R,P)) 即：最多的子图节点数小于剩余两个子图节点数的和，则后手棋手必败。

 比较有意思的讨论是：在一颗树中，是否一定能找到必胜的节点？这样的节点是否有多于一个？
 */
public class ConqueronTree {

    /**
     *
     input是树root和对手选中的node,返回你是否能胜利
     followup 如果你先选择点，你该选择哪个点能胜利？
     */
    public boolean findTreeNode(TreeNode root, TreeNode opponentNode) {
        int leftNodes = countNumberOfNodes(opponentNode.left);
        int rightNodes = countNumberOfNodes(opponentNode.right);
        int totalNodes = countNumberOfNodes(root);
        int remainingNodes = totalNodes - 1 - leftNodes - rightNodes;
        return remainingNodes > totalNodes / 2 || leftNodes > totalNodes / 2 || rightNodes > totalNodes / 2;
    }

    private int countNumberOfNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + countNumberOfNodes(root.left) + countNumberOfNodes(root.right);
        }
    }


    /**
     * 两个人红蓝，在二叉树上，每个人可以从第一个选的点开始同时往相邻的点扩展占领点，已知red选了一个点，
     * （规则大概是两点之间的可以共同占领，但红的children只能红的占）问蓝第一个点选哪里最后能占领的最多。输入root和红的点，输出蓝色选的node
     *
     * follow up是怎么更efficient
     选第一个点的的时候，要选一个三部分尽量均匀的，即 任何两部分都大于第三部分的，即红色先选并选第一个点的规则， 这个是follow up

     */
    private TreeNode redParent = null;
    private TreeNode red = null;
    public TreeNode findNode(TreeNode root, TreeNode red) {
        // sanity check
        this.red = red;
        int redL = countNode(red.left);
        int redR = countNode(red.right);
        int redUp = countNode(root);
        int max = Math.max(redUp, Math.max(redL, redR));
        if(max == redL) return red.left;
        else if(max == redR) return red.right;
        else return redParent;
    }
    private int countNode(TreeNode root) {
        if(root == null) return 0;
        if(root.left == red || root.right == red) redParent = root;
        if(root == red) return 0;
        return countNode(root.left) + 1 + countNode(root.right);
    }

}
