package data_structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiChildrenTreeNode {
    public int val;
    public List<MultiChildrenTreeNode> childrenTreeNodes;
    public MultiChildrenTreeNode(int val){
        this.val = val;

        childrenTreeNodes = new ArrayList<>();

    }

    public boolean isRed() {
        boolean[] random = new boolean[2];
        random[1] = true;

        int idx = new Random().nextInt(2);

        return random[idx];
    }
}
