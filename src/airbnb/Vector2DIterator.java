package airbnb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given an array of arrays, implement an iterator class to allow the client to traverse
 * and remove elements in the array list.
 * This iterator should provide three public class member functions:
 *
 * boolean hasNext()
 * return true if there is another element in the set
 *
 * int next()
 * return the value of the next element in the array
 *
 * void remove()
 * remove the last element returned by the iterator.
 * That is, remove the element that the previous next() returned
 * This method can be called only once per call to next(), otherwise an exception will be thrown.
 *
 *
 *
 */

public class Vector2DIterator implements Iterator<Integer> {
    List<List<Integer>> vec2d;
    int currentRow;
    int currentColumn;
    int pre_x; //used for remove
    int pre_y; //used for remove
    Integer currentEle; // store next value

    public Vector2DIterator(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        currentRow = 0;
        currentColumn = 0;
        pre_x = -1;
        pre_y = -1;

        searchNext();
    }

    private void searchNext() {
        if (currentRow < vec2d.size()) {
            if (currentColumn < vec2d.get(currentRow).size()) {
                currentEle = vec2d.get(currentRow).get(currentColumn);
                // currentColumn++; 注意这里没有 currentColumn++ 是因为后面要 remove
            } else {
                currentRow++;
                currentColumn = 0;
                searchNext();
            }
        } else {
            currentEle = null;
        }
    }

    @Override
    public void remove() {
        if (pre_x == -1 && pre_y == -1) {
            throw new IllegalStateException("remove() can be called only once per call to next()");
        }

        vec2d.get(pre_x).remove(pre_y);

        if (pre_x == currentRow) {  //因为remove 的是同一行的元素，所以innerIndex要--
            currentColumn--; // 如果remove的是上一行的元素， 则不用
        }

        pre_x = -1;
        pre_y = -1;
    }

    @Override
    public boolean hasNext() {
        return currentEle != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return  null;
        } else {
            Integer returnVal = currentEle;

            pre_x = currentRow;
            pre_y = currentColumn;

            currentColumn++;
            searchNext();

            return returnVal;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        matrix.add(row1);
        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        matrix.add(row2);
        List<Integer> row3 = new ArrayList<>();
        matrix.add(row3);
        List<Integer> row4 = new ArrayList<>();
        row4.add(4);
        row4.add(5);
        row4.add(6);
        matrix.add(row4);

        Vector2DIterator iter = new Vector2DIterator(matrix);
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.next());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.hasNext());
        System.out.println(iter.next());
        iter.remove();
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        iter.remove();
    }
}









/**
 *
 * leetcode: https://leetcode.com/problems/flatten-2d-vector/submissions/
 记录 当前行 x 和当前列 y; 如果当前行的列数是0就x++到下一行；

 每次next出一个元素的时候，check y 如果y < 当前列数的最后一个index，y++。
 反之move到下一行。hasNext()就是判断当前行是不是小于总行数
 */

class Vector2D_1 {
    int[][] v;
    int x;
    int y;
    int rows;
    int cols;

    public Vector2D_1(int[][] v) {
        this.v = v;
        x = 0;
        y = 0;

        //skip empty rows;
        while (x < v.length && v[x].length == 0) {
            x++;
        }
    }

    public int next() {
        int ans = v[x][y];

        if (y < v[x].length - 1) {
            // if current y is not the last element on the row;
            y++;
        } else {
            // move to the next row;
            x++;
            y=0;
        }

        // skip empty row
        while (x < v.length && v[x].length == 0) {
            x++;
        }
        return ans;
    }

    public boolean hasNext() {
        return x < v.length;
    }
}



/**
 * leetcode: https://leetcode.com/problems/flatten-2d-vector/submissions/
 *
 * 内部用一个List<Integer> 直接用List的iterator
 */
class Vector2D_2 {
    List<Integer> internal;
    Iterator<Integer> iterator;
    public Vector2D_2(int[][] v) {
        internal = new ArrayList<>();
        for (int i = 0; i < v.length; i++) {
            for(int j = 0; j < v[i].length; j++) {
                internal.add(v[i][j]);
            }
        }

        iterator = internal.iterator();
    }

    public int next() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return -1;
        }

    }

    public boolean hasNext() {
        return iterator.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */