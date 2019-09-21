package airbnb.onsite.datastructure;

import java.util.HashMap;
import java.util.Map;

public  class TrieNode {
    public char c;
    public String word;
    public boolean hasWord;
    public Map<Character, TrieNode> children;

    public TrieNode() {
        word = "";
        hasWord = false;
        children = new HashMap<>();
    }

    public TrieNode(char c) {
        this.c = c;
        word = "";
        hasWord = false;
        children = new HashMap<>();
    }
}