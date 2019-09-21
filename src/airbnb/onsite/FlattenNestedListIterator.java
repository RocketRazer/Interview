package airbnb.onsite;

import airbnb.onsite.datastructure.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator implements Iterator<Integer>{
    private Stack<NestedInteger> stack = new Stack<>();

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        pushToStack(nestedList);
    }

    private void pushToStack(List<NestedInteger> nestedList) {
        Stack<NestedInteger> tmp = new Stack<>();

        for (NestedInteger nestedInteger : nestedList) {
            tmp.push(nestedInteger);
        }


        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return stack.pop().getInteger();
        } else {
            return null;
        }

    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peek().isInteger()) {
            pushToStack(stack.pop().getList());
        }

        return !stack.isEmpty();
    }
}
