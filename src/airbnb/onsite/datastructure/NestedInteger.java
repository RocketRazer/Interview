package airbnb.onsite.datastructure;

import java.util.List;

public class NestedInteger {
    Integer value;
    List<NestedInteger> list;


    public NestedInteger(Integer value) {
        this.value = value;
        list = null;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
        this.value = null;
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return this.value != null;
    }


    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return this.value;
    }


    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return this.list;
    }
}
