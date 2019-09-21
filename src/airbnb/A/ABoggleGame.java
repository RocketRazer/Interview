package airbnb.A;

import java.util.*;

class ReturnType {
    boolean hasPrefix;
    boolean hasWord;

    ReturnType(boolean hasPrefix, boolean hasWord) {
        this.hasPrefix = hasPrefix;
        this.hasWord = hasWord;
    }
}

/**
 * TrieNode
 */
class TrieNode {
    char c;
    boolean hasWord;
    Map<Character, TrieNode> children;

    public TrieNode(char c, boolean hasWord) {
        this.c = c;
        this.hasWord = hasWord;
        this.children = new HashMap<>();
    }
}

/**
 * Trie
 */
class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode(' ', false);
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode(c, false));
            }
            cur = cur.children.get(c);
        }
        cur.hasWord = true;
    }

    public ReturnType search(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                return new ReturnType(false, false);
            }
            cur = cur.children.get(c);
        }
        return new ReturnType(true, cur.hasWord);
    }
}

public class ABoggleGame {
    private static final int[] del_X = {0, 0, -1, 1};
    private static final int[] del_Y = {1, -1, 0, 0};

    public List<String> findMostStr(char[][] board, Set<String> dict) {
        List<List<int[]>> paths = new ArrayList<>();


        // add dict words to trie
        Trie trie = new Trie();
        for (String word : dict) {
            trie.insert(word);
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
        ABoggleGame bg = new ABoggleGame();

        char[][] board = {
                {'o', 'a', 't', 'h'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        Set<String> dict = new HashSet<>();
        dict.add("oath");
        dict.add("pea");
        dict.add("eat");
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