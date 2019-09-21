package a_google;

import data_structure.tree.MultiChildrenTreeNode;
import data_structure.tree.TreeNode;

import java.util.Iterator;
import java.util.List;

/**
 *
 * 17. N叉树，要求删一些node，返回list of roots 高频 8次
 More Detail : 给一个tree有红的node有蓝的node，把红的去掉后剩下一堆零零散散的tree，
 返回这些tree的node，只要node，不要children，也就是说把这个node的children设置成null然后加到list里。
 参数是这个树的root。找到所有的红点然后delete掉，
 去掉这些红点之后就会把一个tree变成散落的几个tree，然后返回这几个tree的root。直接一个recursive判断一下，如果这个node是红点的话就ignore 掉再去判断这个node的children，如果这个node是蓝点的话，要看这个蓝点的parent是不是个红点，是的话，这个蓝点就是散落的tree中其中一个tree的root。
 思路：简单BFS。。不应是dfs?
 想问一下这题返回的顺序重要吗？

 没想到怎么做，有没有大佬写了code能发一下的?感激不尽

 */
public class NTreeDeleteNodes {

    public void dfs(MultiChildrenTreeNode root, List<MultiChildrenTreeNode> res) {
        if (root == null) return;

        if (root.isRed()) {
            for (MultiChildrenTreeNode child : root.childrenTreeNodes) {
                //当red遍历到为root的时候加到res里
                if (!child.isRed()) {
                    res.add(child);
                }

                dfs(child, res);
            }
        } else {
            res.add(root);
            Iterator<MultiChildrenTreeNode> iterator = root.childrenTreeNodes.iterator();
            while(iterator.hasNext()) {
                //当red遍历到为child的时候删除
                MultiChildrenTreeNode node = iterator.next();
                if (node.isRed()) {
                    root.childrenTreeNodes.remove(node);
                }

                dfs(node, res);
            }
        }
    }
}
