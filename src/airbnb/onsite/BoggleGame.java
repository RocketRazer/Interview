package airbnb.onsite;

/**
 * 本质上是word search 2
 * 但是呢比如你现在走了一个词apple, 那么a, p, p, l, e这几个char的位置不能继续用了
 * 于是给你一个board, 一个dict让你计算最多能有多少个valid单词出现在这个Board上面
 *
 *
 *
 此题和 LeetCode #212 Word Search II 的区别在于找出一条 path 包含最多的存在于字典的 word 个 数。 总结一下，有两种解法，都是基于 word search II 的:

 • 用 trie+DFS 找到一个 word 之后，wordCounter 加 1，从这个 word 最后一个 character 的位 置再用 trie+DFS 继续查找，
   知道相邻位置都用过了，或者没有在 trie 里找到 match。

 • 用 trie+DFS 找到所有 words 可能出现的位置序列. 然后根据每个单词出现的序列在分别做 DFS，分别是不取这个单词，取第一个序列，取第二个序列，etc。


 */

import airbnb.onsite.datastructure.ReturnType;
import airbnb.onsite.datastructure.Trie;

import java.util.*;





public class BoggleGame {
    private static final int[] del_X = {0, 0, -1, 1};
    private static final int[] del_Y = {1, -1, 0, 0};

    public List<String> findMostStr(char[][] board, Set<String> dict) {
        List<List<int[]>> paths = new ArrayList<>();


        // add dict words to trie
        Trie trie = new Trie();
        for (String word : dict) {
            trie.insertWord(word);
        }

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 找到所有从 board[i][j] 字母开头的单词 并加到paths里
                boolean[][] visited = new boolean[m][n];
                visited[i][j] = true;
                List<int[]> curPath = new ArrayList<>();
                curPath.add(new int[]{i, j});
                dfs(paths, board, i, j, trie, visited, curPath);
            }
        }


        List<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        searchWords(res, new ArrayList<>(), paths, 0, visited, board);

        return res;
    }

    private void dfs(List<List<int[]>> paths, char[][] board, int x, int y, Trie trie,
                     boolean[][] visited, List<int[]> curPath) {
        String curWord = path2Word(board, curPath);
        ReturnType searchResult = trie.search(curWord);
        if (!searchResult.hasPrefix) {
            return;
        }
        if (searchResult.hasWord) {
            paths.add(new ArrayList<>(curPath));
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < 4; i++) {
            int next_x = x + del_X[i];
            int next_y = y + del_Y[i];

            if (next_x < 0 || next_x >= m || next_y < 0 || next_y >= n || visited[next_x][next_y]) {
                continue;
            }

            visited[next_x][next_y] = true;
            curPath.add(new int[]{next_x, next_y});
            dfs(paths, board, next_x, next_y, trie, visited, curPath);
            curPath.remove(curPath.size() - 1);
            visited[next_x][next_y] = false;
        }
    }

    private void searchWords(List<String> res, List<String> curWords, List<List<int[]>> paths,
                             int startIndex, boolean[][] visited, char[][] board) {
        if (startIndex == paths.size()) {
            //遍历过所有的单词了， 如果当前wordsList比全局的长， update全局的
            if (curWords.size() > res.size()) {
                res.clear();
                res.addAll(curWords);
            }
            return;
        }

        for (int i = startIndex; i < paths.size(); i++) {

            // 先check当前单词能不能use
            boolean canUse = true;
            for (int[] coor : paths.get(i)) {
                if (visited[coor[0]][coor[1]]) {
                    canUse = false;
                    break;
                }
            }

            if (canUse) {
                //如果能用把当前单词的路径update成used
                for (int[] coor : paths.get(i)) {
                    visited[coor[0]][coor[1]] = true;
                }
                curWords.add(path2Word(board, paths.get(i)));
                searchWords(res, curWords, paths, i + 1, visited, board);
                //backtracking不仅要把当前的 单词去掉，还要把当前单词访问过的路径的visited 数组变回false
                curWords.remove(curWords.size() - 1);
                for (int[] coor : paths.get(i)) {
                    visited[coor[0]][coor[1]] = false;
                }
            }
        }
    }

    private String path2Word(char[][] board, List<int[]> curPath) {
        StringBuilder sb = new StringBuilder();
        for (int[] coor : curPath) {
            sb.append(board[coor[0]][coor[1]]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BoggleGame bg = new BoggleGame();

        char[][] board = {
                {'o', 'a', 't', 'h'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        Set<String> dict = new HashSet<>();
        dict.add("oath");
        dict.add("pea");
        dict.add("eat"); // 最后一个't' （0，3）
        dict.add("rain");
        System.out.println(bg.findMostStr(board, dict));



        char[][] board2 = new char[][]{
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'}
        };

        Set<String> dict2 = new HashSet<>();
        dict2.add("abc");
        dict2.add("cfi");
        dict2.add("defi");
        dict2.add("beh");
        dict2.add("gh");

        System.out.println(bg.findMostStr(board2, dict2));

    }
}


//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BoggleGame {
//
//    // 从每个点开始，找从这个点出发的所有单词组合
//    public List<String> getAllWords(char[][] board, String[] words) {
//        // 构建字典树加速查找
//        Trie trie = new Trie();
//        for (String word : words) {
//            trie.insert(word);
//        }
//
//        int m = board.length;
//        int n = board[0].length;
//        List<String> result = new ArrayList<>();
//        // 每个点作为起点，可能会有不一样的结果
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                boolean[][] visited = new boolean[m][n];
//                List<String> path = new ArrayList<>();
//                findWords(result, board, visited, path, i, j, trie.root);
//            }
//        }
//
//        return result;
//    }
//
//    // 从i,j开始递归找到所有单词组合
//    public void findWords(List<String> result, char[][] board, boolean[][] visited, List<String> words, int x, int y, TrieNode root) {
//
//        int m = board.length;
//        int n = board[0].length;
//        for (int i = x; i < m; i++) {
//            for (int j = y; j < n; j++) {
//                List<List<Integer>> nextWordIndexes = new ArrayList<>();
//                List<Integer> path = new ArrayList<>();
//                // 获得从当前点开始的所有可能单词的indexes
//                getNextWords(nextWordIndexes, board, visited, path, i, j, root);
//                for (List<Integer> indexes : nextWordIndexes) {
//                    // 设置visited为当前使用单词
//                    String word = "";
//                    for (int index : indexes) {
//                        int row = index / n;
//                        int col = index % n;
//                        visited[row][col] = true;
//                        word += board[row][col];
//                    }
//
//                    words.add(word);
//                    // 只要更新了words，就保存一次words
//                    if (words.size() > result.size()) {
//                        result.clear();
//                        result.addAll(words);
//                    }
//                    findWords(result, board, visited, words, i, j, root);
//
//                    // 恢复visited
//                    for (int index : indexes) {
//                        int row = index / n;
//                        int col = index % n;
//                        visited[row][col] = false;
//                    }
//                    words.remove(words.size() - 1);
//                }
//            }
//            // 只有第x行是从y开始，后面都从0开始
//            y = 0;
//        }
//    }
//
//    private void getNextWords(List<List<Integer>> words, char[][] board, boolean[][] visited, List<Integer> path, int i, int j, TrieNode root) {
//        if (i < 0 | i >= board.length || j < 0 || j >= board[0].length
//                || visited[i][j] == true || root.children[board[i][j] - 'a'] == null) {
//            return;
//        }
//
//        root = root.children[board[i][j] - 'a'];
//        if (root.isWord) {
//            List<Integer> newPath = new ArrayList<>(path);
//            newPath.add(i * board[0].length + j);
//            words.add(newPath);
//            return;
//        }
//
//        visited[i][j] = true;
//        path.add(i * board[0].length + j);
//        getNextWords(words, board, visited, path, i + 1, j, root);
//        getNextWords(words, board, visited, path, i - 1, j, root);
//        getNextWords(words, board, visited, path, i, j + 1, root);
//        getNextWords(words, board, visited, path, i, j - 1, root);
//        path.remove(path.size() - 1);
//        visited[i][j] = false;
//    }
//
//    class Trie {
//        TrieNode root;
//
//        Trie() {
//            root = new TrieNode('0');
//        }
//
//        public void insert(String word) {
//            if (word == null || word.length() == 0) {
//                return;
//            }
//            TrieNode node = root;
//            for (int i = 0; i < word.length(); i++) {
//                char ch = word.charAt(i);
//                if (node.children[ch - 'a'] == null) {
//                    node.children[ch - 'a'] = new TrieNode(ch);
//                }
//                node = node.children[ch - 'a'];
//            }
//            node.isWord = true;
//        }
//    }
//
//    class TrieNode {
//        char value;
//        boolean isWord;
//        TrieNode[] children;
//
//        TrieNode(char v) {
//            value = v;
//            isWord = false;
//            children = new TrieNode[26];
//        }
//    }
//
//    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                {'a', 'b', 'c'},
//                {'d', 'e', 'f'},
//                {'g', 'h', 'i'}
//        };
//        String[] words = new String[]{
//                "abc", "cfi", "beh", "defi", "gh"
//        };
//
//        BoggleGame s = new BoggleGame();
//        System.out.println(s.getAllWords(board, words));
//    }
//}
//
