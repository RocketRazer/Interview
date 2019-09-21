package airbnb.onsite;


import airbnb.onsite.datastructure.Trie;

import java.util.*;


class WordSearch_II {
    char[][] board;
    String[] words;
    List<String> res;

    public List<String> findWords(char[][] board, String[] words) {
        res = new ArrayList<>();

        if (board == null || board.length == 0) {
            return new ArrayList<>();
        }

        this.board = board;
        this.words = words;


        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        Trie trie = new Trie();
        for (String w : words) {
            trie.insertWord(w);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, "", trie, visited);
            }
        }

        return new ArrayList<>(res);
    }

    private void dfs(int x, int y, String curStr, Trie trie, boolean[][] visited) {
        curStr += board[x][y];

        if (trie.hasWord(curStr)) {
            res.add(curStr);
            //注意这里不能return 下面可能有prefix和当前word相同的word
        }

        if (!trie.startsWith(curStr)) {
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + del_x[i];
            int ny = y + del_y[i];

            if (isValid(nx, ny) && !visited[nx][ny]) {
                dfs(nx, ny, curStr, trie, visited);
            }
        }
        visited[x][y] = false;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    int[] del_x = {0, 0, 1, -1};
    int[] del_y = {1, -1, 0, 0};

    //main test
    public static void main(String[] args) {
        WordSearch_II s = new WordSearch_II();

        //[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words ={"oath","pea","eat","rain"};

        char[][] board2 = {{'a'}};
        String[] words2 ={"a"};
        System.out.println(s.findWords(board, words));

    }


}



