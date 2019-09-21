package data_structure.trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char c;
    boolean hasWord; // whether there's a word ending here
    HashMap<Character, TrieNode> children = new HashMap<>();

    public TrieNode() {}

    public TrieNode(char c) {
        this.c = c;
    }
}

public class TrieImpl implements Trie{
    private TrieNode root;

    public TrieImpl() {
        root = new TrieNode();
    }


    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        TrieNode cur = root;
        Map<Character, TrieNode> curChildren = root.children;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            char c = wordArray[i];
            if (curChildren.containsKey(c)) {
                cur = curChildren.get(c);
            } else {
                TrieNode newNode = new TrieNode(c);
                curChildren.put(c, newNode);
                cur = newNode;
            }
            curChildren = cur.children;

            if (i == wordArray.length -1) {
                cur.hasWord = true;
            }
        }
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        if (searchLastCharacterNode(word) == null) {
            return false;
        } else if (searchLastCharacterNode(word).hasWord) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (searchLastCharacterNode(prefix) == null) {
            return false;
        } else {
            return true;
        }
    }

    public TrieNode searchLastCharacterNode(String s) {
        Map<Character, TrieNode> curChildren = root.children;
        TrieNode cur = null;
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            char c = sArray[i];
            if (curChildren.containsKey(c)) {
                cur = curChildren.get(c);
                curChildren = cur.children;
            } else {
                return null;
            }
        }

        return cur;
    }
}
