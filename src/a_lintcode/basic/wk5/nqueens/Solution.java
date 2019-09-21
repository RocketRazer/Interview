package a_lintcode.basic.wk5.nqueens;


import java.util.*;

public class Solution {
    /*
     * @param n: The number of queens
     * @return: All distinct solutions
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }

        //represents the index of queue for each row
        List<Integer> queenColumns = new ArrayList<>();
        dfs(queenColumns, result, n);

        return result;
    }

    void dfs(List<Integer> queenColumns, List<List<String>> result, int n) {
        //TODO exit
        if (queenColumns.size() == n) {
            result.add(printGraph(queenColumns));
        }

        for (int column = 0; column < n; column++) {
            if (queenColumns.contains(column)) {
                continue;
            }
            if (!isValid(queenColumns, column)) {
                continue;
            }
            queenColumns.add(column);
            dfs(queenColumns, result, n);
            queenColumns.remove(queenColumns.size() - 1);
        }
    }

    boolean isValid(List<Integer> queenColumns, int currentColumn) {
        int currentRow = queenColumns.size();
        for (int row = 0; row < queenColumns.size(); row++) {
            int column = queenColumns.get(row);

            // queens are on the decrease diangno
            if (row - column == currentRow - currentColumn) {
                return false;
            }


            if (row + column == currentRow + currentColumn) {
                return false;
            }

            if (column == currentColumn) {
                return false;
            }
        }
        return true;
    }

    List<String> printGraph(List<Integer> queenColumns) {
        //TODO
        List<String> result = new ArrayList<>();
        for (int row = 0; row < queenColumns.size(); row++) {
            StringBuilder sb = new StringBuilder();
            for (int column = 0; column < queenColumns.size(); column++) {
                sb.append(column == queenColumns.get(row) ? "Q" : ".");
            }

            result.add(sb.toString());
        }

        return result;
    }

    public static void main(String[] args) {
//        Solution solu = new Solution();
//        List<List<String>>  result = solu.solveNQueens(4);
//        result.stream().forEach(System.out::println);
        System.out.println("Result is : " + "01".substring(0, 1));
    }
}
