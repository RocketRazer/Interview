package a_lintcode.dp.k_edit_distance;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int k;
    String target;
    List<String> res;
    int n; // target.length();

    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }

        this.k = k;
        this.target = target;
        this.n = target.length();


        // build trie
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode.insertNode(root, w);
        }


        int[] f = new int[n+1];

        dfs(root, f);

        return res;
    }


    private void dfs(TrieNode p, int[] f) {
        if (p.hasWord && f[n] <= k) {
            res.add(p.word);
        }


        for (int c = 0; c < 26; c++) {
            if (p.sons[c] == null) {
                continue;
            }


            int[] nf = new int[n+1];

            //f[sP][j] = min{f[sP][j-1]+1, f[sQ][j-1]+1, f[sQ][j]+1, f[sQ][j-1]|sP[last]=Target[j-1]}
            // nf is f[sP] is new, f is f[sQ] is old
            for (int j = 0; j <= n; j++) {
                nf[j] = f[j] + 1;
                if (j > 0) {
                    nf[j] = Math.min(nf[j-1] + 1, f[j-1] + 1);

                    if (c == target.charAt(j-1)) {
                        nf[j] = Math.min(nf[j], f[j-1]);
                    }
                }
            }

            dfs(p.sons[c], nf);
        }
    }
}

class TrieNode {
    TrieNode[] sons;
    boolean hasWord;
    String word;

    public TrieNode() {
        sons = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            sons[i] = null;
        }
        this.word = "";
    }

    public static void insertNode(TrieNode p, String word) {
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (p.sons[c] == null) {
                p.sons[c] = new TrieNode();
                p = p.sons[c];
            }
        }

        p.hasWord = true;
        p.word = word;
    }
}